package logicalExpression.designPattern;

import org.w3c.dom.Element;

public class SwitchPattern extends BaseDesignPattern{
    
    public SwitchPattern(Element mainNode) {
        designPatternType = DesignPatternType.Switch;
        name = mainNode.getAttribute("name");        
    }
    
    @Override
    public StringBuilder printLogicalSpecification() {
        StringBuilder result = new StringBuilder();
        
        return result;
    }
}
