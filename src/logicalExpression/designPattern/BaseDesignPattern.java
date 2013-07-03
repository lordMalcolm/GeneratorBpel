package logicalExpression.designPattern;

import logicalExpression.LogicalExpression;
import java.util.ArrayList;
import java.util.List;
import logicalExpression.LogicalExpressionType;
import logicalSpecification.patterns.PatternFormulaMap;
import logicalSpecification.patterns.PatternParams;

public abstract class BaseDesignPattern extends LogicalExpression {
    public DesignPatternType designPatternType;
    public List<LogicalExpression> nestedPatterns;
    protected PatternParams patternParams;
    
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

    /**
     * tylko setter bo powinien być uzywany 
     * tylko w metodzie printLogicalExpression()
     * @param patternParams 
     */
    public void setPatternParams(PatternParams patternParams) {
        this.patternParams = patternParams;
    }
}