package DesignPatternModels;

import org.w3c.dom.Element;

public class SequencePattern extends BaseDesignPattern {
    
    @Override
    public void Process(Element mainNode) {
        Element child1 = (Element) mainNode.getChildNodes().item(0);
        Element child2 = (Element) mainNode.getChildNodes().item(1);

       this.Type = DesignPatternType.Sequence;
       this.Name = mainNode.getAttribute("name");
       this.Arguments.add(child1.getAttribute("name"));
       this.Arguments.add(child2.getAttribute("name"));
    }
}
