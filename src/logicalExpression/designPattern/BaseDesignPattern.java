package logicalExpression.designPattern;

import logicalExpression.LogicalExpression;
import java.util.ArrayList;
import java.util.List;
import logicalExpression.LogicalExpressionType;

public abstract class BaseDesignPattern extends LogicalExpression {
    public DesignPatternType designPatternType;
    public List<LogicalExpression> nestedPatterns;
    
    public BaseDesignPattern() {
        nestedPatterns = new ArrayList<>();
        logicalExpressionType = LogicalExpressionType.DesignPattern;
    }
    
    @Override
    public StringBuilder printLogicalExpression() {
        StringBuilder sb = new StringBuilder();
        sb.append(designPatternType.toString());
        sb.append("(");
        for(int i = 0; i < nestedPatterns.size(); i++){
            sb.append(nestedPatterns.get(i).printLogicalExpression());
            if (i < (nestedPatterns.size()-1))
                sb.append(",");
        }
        sb.append(")");
        return sb;
    }
    
    @Override
    public String getSpecificType() {
        return designPatternType.toString();
    }
}