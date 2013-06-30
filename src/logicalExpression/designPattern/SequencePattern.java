package logicalExpression.designPattern;

import org.w3c.dom.Element;

public class SequencePattern extends BaseDesignPattern {
    
    public SequencePattern(Element mainNode) {
        Element child1 = (Element) mainNode.getChildNodes().item(0);
        Element child2 = (Element) mainNode.getChildNodes().item(1);

       this.designPatternType = DesignPatternType.Sequence;
       this.name = mainNode.getAttribute("name");
       
       this.childNodes.add(child1);
       this.childNodes.add(child2);
    }
}
