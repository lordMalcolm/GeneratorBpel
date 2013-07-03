package logicalExpression.designPattern;

import org.w3c.dom.Element;

public class FlowPattern extends BaseDesignPattern {
    
    public FlowPattern(Element mainNode) {        
        this.designPatternType = DesignPatternType.Flow;
        this.name = mainNode.getAttribute("name");
    }
}
