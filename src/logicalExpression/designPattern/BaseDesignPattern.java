package logicalExpression.designPattern;

import logicalExpression.LogicalExpression;
import java.util.ArrayList;
import java.util.List;
import logicalExpression.LogicalExpressionType;
import logicalSpecification.patterns.PatternTemporalProperties;

public abstract class BaseDesignPattern extends LogicalExpression {
    public DesignPatternType designPatternType;
    public List<LogicalExpression> nestedPatterns;
    protected PatternTemporalProperties temporalProperties;
    
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
    public void setPatternParams(PatternTemporalProperties patternParams) {
        this.temporalProperties = patternParams;
    }
    
    protected String getBasicTemporalFormula() {
        
        StringBuilder logicFormulasSet = new StringBuilder();
        List<String> formulas = temporalProperties.getFormulas();
        logicFormulasSet.append("{");
        for (String formula : formulas) {
            logicFormulasSet.append(formula).append(",");
        }
        logicFormulasSet.deleteCharAt(logicFormulasSet.length()-1);
        logicFormulasSet.append("}");
        
        String output = logicFormulasSet.toString();
        
        for (int i = 0; i < nestedPatterns.size(); i++)
            output = output.replaceAll(temporalProperties.getParams().get(i), nestedPatterns.get(i).name);
        
        return output;
    }
}