package logicalSpecification.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import logicalExpression.LogicalExpression;
import logicalExpression.LogicalExpressionType;
import logicalExpression.designPattern.BaseDesignPattern;

public class LogicalSpecificationGenerator implements ILogicalSpecificationGenerator {

    private StringBuilder sb;

    public LogicalSpecificationGenerator() {
        sb = new StringBuilder();
    }
    
    @Override
    public String getLogicalSpecification(LogicalExpression expression) {
        searchTree(expression);
        return sb.toString();
    }

    private void searchTree(LogicalExpression expression) {
        //jeżeli trafiamy na aktywność to kończymy przeszukiwanie drzewa
        if (expression.logicalExpressionType == LogicalExpressionType.AtomicAction)
            return;
        
        //pobierany jest wzorzec
        BaseDesignPattern pattern = (BaseDesignPattern) expression;
                        
        //schodzenie do zagnieżdżonych wzorców
        for (LogicalExpression logicalExpression : pattern.nestedPatterns) {
            searchTree(logicalExpression);
        }
        
        //dodawana jest specyfikacja dla danego węzła
        addLogicalSpecificationForPattern(pattern);
    }

    private void addLogicalSpecificationForPattern(BaseDesignPattern pattern) {
        
        if (pattern.isMostNested()) {
            sb.append(pattern.getBasicTemporalFormula());
            return;
        }
            
        Map<Integer, Set<String>> map = getArgumentsMap(pattern);
        
        List<Map<Integer,String>> list = new LinkedList<>();
        combinations( map, list );
            
        List<ArgumentSet> argumentsSet = getArgumentsSet(list);
        for (ArgumentSet argumentSet : argumentsSet) {
            sb.append(pattern.getTemporalFormulaForArgumentSet(argumentSet));
        }

    }
    
    private Map<Integer, Set<String>>  getArgumentsMap(BaseDesignPattern pattern) {
        
        Map<Integer, Set<String>>  map = new HashMap<>();
        for (int i = 0; i < pattern.nestedPatterns.size(); i++) {
            Set<String> arguments = new HashSet<>();
            LogicalExpression current = pattern.nestedPatterns.get(i);
            if (current.logicalExpressionType == LogicalExpressionType.AtomicAction){
                arguments.add(current.name);
            }
            else {
                BaseDesignPattern curP = (BaseDesignPattern) current;
                arguments.add(curP.getInArgument());
                arguments.add(curP.getOutArgument());
            }
            map.put(i, arguments);
         }
         return map;
    }
    
    private static <K,V> void combinations( Map<K,Set<V>> map, List<Map<K,V>> list ) {
        recurse( map, new LinkedList<>( map.keySet() ).listIterator(), new HashMap<K,V>(), list );
    }

    // helper method to do the recursion
    private static <K,V> void recurse( Map<K,Set<V>> map, ListIterator<K> iter, Map<K,V> cur, List<Map<K,V>> list ) {
        // we're at a leaf node in the recursion tree, add solution to list
        if( !iter.hasNext() ) {
            Map<K,V> entry = new HashMap<>();

            for( K key : cur.keySet() ) {
                entry.put( key, cur.get( key ) );
            }

            list.add( entry );
        } else {
            K key = iter.next();
            Set<V> set = map.get( key );

            for( V value : set ) {
                cur.put( key, value );
                recurse( map, iter, cur, list );
                cur.remove( key );
            }

            iter.previous();
        }
    }

    private List<ArgumentSet> getArgumentsSet(List<Map<Integer, String>> list) {
        List<ArgumentSet> argumentsSet = new ArrayList<>();
        for( Map<Integer,String> combination : list ) {
            
            ArgumentSet s = new ArgumentSet();
            for (String string : combination.values())
                s.arguments.add(string);
            argumentsSet.add(s);
            
        }
        return argumentsSet;
    }
}
