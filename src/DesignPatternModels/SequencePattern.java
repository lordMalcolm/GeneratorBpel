package DesignPatternModels;

import org.w3c.dom.Element;

public class SequencePattern extends BaseDesignPattern {
    
    @Override
    public void Process(Element mainNode) {
        Element child1 = (Element) mainNode.getChildNodes().item(0);
        Element child2 = (Element) mainNode.getChildNodes().item(1);

       this.type = DesignPatternType.Sequence;
       this.name = mainNode.getAttribute("name");
       this.arguments.add(child1.getAttribute("name"));
       this.arguments.add(child2.getAttribute("name"));
       
       this.childNodes.add(child1);
       this.childNodes.add(child2);
    }
}
