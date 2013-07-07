package logicalSpecification.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            
        HashMap<Integer, List<String>> map = getArgumentsMap(pattern);
        List<List<String>> argumentsSets = fillArgumentsSets(map);       
                        
        return;
    }

    private int getNumberOfFormulas(HashMap<Integer, List<String>> map) {
        int result = 1;
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            List<String> list = entry.getValue();
            result = result * list.size();
        }
        return result;        
    }
    
    private HashMap<Integer, List<String>> getArgumentsMap(BaseDesignPattern pattern) {
        
        HashMap<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i < pattern.nestedPatterns.size(); i++) {
            List<String> arguments = new ArrayList<>();
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

    private List<List<String>> initializeArgumentsSet(HashMap<Integer, List<String>> map) {
        int formulasNumber = getNumberOfFormulas(map);
        int formulaLength = map.size();
        List<List<String>> argumentsSets = new ArrayList<>();
        for(int i = 0; i < formulasNumber; i++){
            List<String> n = new ArrayList<>();
            for(int j = 0; j < formulaLength; j++){
                n.add("-");
            }
            argumentsSets.add(n);
        }
        return argumentsSets;
    }

    private List<List<String>> fillArgumentsSets(HashMap<Integer, List<String>> map) {
        List<List<String>> argumentsSets = initializeArgumentsSet(map);
        
        //wstępne wypełnienie
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            Integer integer = entry.getKey();
            List<String> list = entry.getValue();
            for(int i = 0; i < list.size(); i++){
                argumentsSets.get(i+integer).set(integer, list.get(i));
            }
        }
        
        //uzupełnienie pozostałych pól
        for(int i = 0; i < argumentsSets.get(0).size(); i++){
            List<String> args = new ArrayList<>();
            for (int j = 0; j < argumentsSets.size(); j++){
                if (!argumentsSets.get(j).get(i).equals("-"))
                    args.add(argumentsSets.get(j).get(i));
            }
            
            if (args.size() == 1) {
                for (int j = 0; j < argumentsSets.size(); j++){
                    if (argumentsSets.get(j).get(i).equals("-"))
                        argumentsSets.get(j).set(i, args.get(0));
                }
            }
            else {
                if (i == 0) {
                    for (int j = 0; j < argumentsSets.get(0).size()/2+1; j++)
                        argumentsSets.get(j).set(i, args.get(0));
                    for (int j = argumentsSets.size()/2; j < argumentsSets.size(); j++)
                        argumentsSets.get(j).set(i, args.get(1));
                }
            }
        }
        
        return argumentsSets;
    }
}
