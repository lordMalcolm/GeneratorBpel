package logicalExpression.designPattern;

import org.w3c.dom.Element;

public class SeqSeqPattern extends BaseDesignPattern {
    
    public SeqSeqPattern(Element mainNode) {
        Element child1 = (Element) mainNode.getChildNodes().item(0);
        Element child2 = (Element) mainNode.getChildNodes().item(1);
        Element child3 = (Element) mainNode.getChildNodes().item(2);

       this.designPatternType = DesignPatternType.SeqSeq;
       this.name = mainNode.getAttribute("name");
       
       this.childNodes.add(child1);
       this.childNodes.add(child2);
       this.childNodes.add(child3);
    }
}
