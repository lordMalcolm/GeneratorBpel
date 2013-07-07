package logicalExpression.designPattern;

import logicalExpression.atomicAction.ConditionAction;
import logicalSpecification.patterns.PatternFormulaMap;
import org.w3c.dom.Element;

/**
 * Wzorzec While
 */
public class WhilePattern extends BaseDesignPattern{
    
    public WhilePattern(Element mainNode) {        
        designPatternType = DesignPatternType.While;
        name = mainNode.getAttribute("name");
        nestedPatterns.add(new ConditionAction(mainNode));
        temporalProperties = PatternFormulaMap.getInstance().get(designPatternType);
    }
    
    @Override
    public StringBuilder printLogicalSpecification() {
        StringBuilder result = new StringBuilder();
        
        result.append(getBasicTemporalFormula());
        return result;
    }
}
