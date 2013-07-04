package logicalSpecification.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        
        //dodawana jest specyfikacja dla danego węzła
        addLogicalSpecificationForPattern(pattern);
                
        //schodzenie do zagnieżdżonych wzorców
        for (LogicalExpression logicalExpression : pattern.nestedPatterns) {
            searchTree(logicalExpression);
        }
    }

    private void addLogicalSpecificationForPattern(BaseDesignPattern pattern) {
        
        if (pattern.isMostNested()) {
            sb.append(pattern.getBasicTemporalFormula());
            return;
        }
        
        HashMap<Integer, List<String>> map = getArgumentsMap(pattern);
        int argumentsCount = map.size();
        List<ArgumentSet> argumentsSet = new ArrayList<>();
        
        List<String> first = map.get(0);
        for (String string : first) {
            ArgumentSet as = new ArgumentSet();
            as.arguments.add(string);
            argumentsSet.add(as);
        }
        
      
        
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
}
