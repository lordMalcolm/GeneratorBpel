package logicalExpression.designPattern;

import org.w3c.dom.Element;

public class SequencePattern extends BaseDesignPattern {
    
    public SequencePattern(Element mainNode) {      
       this.designPatternType = DesignPatternType.Sequence;
       this.name = mainNode.getAttribute("name");
    }
}
