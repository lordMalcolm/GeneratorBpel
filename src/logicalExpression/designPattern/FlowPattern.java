package logicalExpression.designPattern;

import logicalSpecification.patterns.PatternFormulaMap;
import org.w3c.dom.Element;
/**
 * Wzorzec Flow
 */
public class FlowPattern extends BaseDesignPattern {
    
    public FlowPattern(Element mainNode) {        
        this.designPatternType = DesignPatternType.Flow;
        this.name = mainNode.getAttribute("name");
        temporalProperties = PatternFormulaMap.getInstance().get(designPatternType);
    }
    
    @Override
    public StringBuilder printLogicalSpecification() {
        StringBuilder result = new StringBuilder();
        result.append(getBasicTemporalFormula());
        return result;
    }
}
