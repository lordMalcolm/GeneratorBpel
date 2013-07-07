package logicalExpression.designPattern;

import logicalSpecification.patterns.PatternFormulaMap;
import org.w3c.dom.Element;
/**
 * Wzorzec Switch
 */
public class SwitchPattern extends BaseDesignPattern{
    
    public SwitchPattern(Element mainNode) {
        designPatternType = DesignPatternType.Switch;
        name = mainNode.getAttribute("name");     
        temporalProperties = PatternFormulaMap.getInstance().get(designPatternType);
    }
    
    @Override
    public StringBuilder printLogicalSpecification() {
        StringBuilder result = new StringBuilder();
        result.append(getBasicTemporalFormula());
        return result;
    }
}
