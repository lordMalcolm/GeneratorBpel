package logicalExpression.designPattern;

import logicalExpression.LogicalExpression;
import java.util.ArrayList;
import java.util.List;
import logicalExpression.LogicalExpressionType;
import logicalSpecification.generator.ArgumentSet;
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
        
        for (int i = 0; i < deepLevel; i++)
            sb.append("   ");
        sb.append(designPatternType.toString());
        sb.append("(");
        sb.append(System.getProperty("line.separator"));
        
        for(int i = 0; i < nestedPatterns.size(); i++){
            sb.append(nestedPatterns.get(i).printLogicalExpression());
            if (i < (nestedPatterns.size()-1))
                sb.append(",");
            sb.append(System.getProperty("line.separator"));
        }
        
        for (int i = 0; i < deepLevel; i++)
            sb.append("   ");
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
    
    public boolean isMostNested() {
        for (LogicalExpression logicalExpression : nestedPatterns)
            if (logicalExpression.logicalExpressionType == LogicalExpressionType.DesignPattern)
                return false;
        
        return true;
    }
    
    public String getInArgument() {
        LogicalExpression first = nestedPatterns.get(0);
        if (first.logicalExpressionType == LogicalExpressionType.AtomicAction){
            
            String output = this.temporalProperties.getIn();
            for (int i = 0; i < nestedPatterns.size(); i++)
                output = output.replaceAll(temporalProperties.getParams().get(i), nestedPatterns.get(i).name);
            
            return output;
        }
        
        BaseDesignPattern pattern = (BaseDesignPattern) first;
        return pattern.getInArgument();
    }
    
    public String getOutArgument() {
        LogicalExpression last = nestedPatterns.get(nestedPatterns.size()-1);
        if (last.logicalExpressionType == LogicalExpressionType.AtomicAction){
            
            String output = this.temporalProperties.getOut();
            for (int i = 0; i < nestedPatterns.size(); i++)
                output = output.replaceAll(temporalProperties.getParams().get(i), nestedPatterns.get(i).name);
            
            return output;
        }
        
        BaseDesignPattern pattern = (BaseDesignPattern) last;
        return pattern.getOutArgument();
    }
     
    public String getBasicTemporalFormula() {
        String output = getFormulasSet();
        for (int i = 0; i < nestedPatterns.size(); i++)
            output = output.replaceAll(temporalProperties.getParams().get(i), nestedPatterns.get(i).name);
        
        return output;
    }
    
    public String getTemporalFormulaForArgumentSet(ArgumentSet argumentSet) {
        String output = getFormulasSet();
        for (int i = 0; i < nestedPatterns.size(); i++)
            output = output.replaceAll(temporalProperties.getParams().get(i), argumentSet.arguments.get(i));
        
        return output;
    }

    private String getFormulasSet() {
        StringBuilder logicFormulasSet = new StringBuilder();
        List<String> formulas = temporalProperties.getFormulas();
        logicFormulasSet.append("{");
        for (String formula : formulas) {
            logicFormulasSet.append(formula).append(",");
        }
        logicFormulasSet.deleteCharAt(logicFormulasSet.length()-1);
        logicFormulasSet.append("}");
        logicFormulasSet.append(System.getProperty("line.separator"));
        String output = logicFormulasSet.toString();
        return output;
    }
}
