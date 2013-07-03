package logicalExpression.designPattern;

import org.w3c.dom.Element;

public class SeqSeqPattern extends BaseDesignPattern {
    
    public SeqSeqPattern(Element mainNode) {
       this.designPatternType = DesignPatternType.SeqSeq;
       this.name = mainNode.getAttribute("name");
    }
}
