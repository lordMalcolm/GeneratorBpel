package logicalExpression.designPattern;

import logicalSpecification.patterns.PatternFormulaMap;
import org.w3c.dom.Element;

public class SeqSeqPattern extends BaseDesignPattern {
    
    public SeqSeqPattern(Element mainNode) {
       this.designPatternType = DesignPatternType.SeqSeq;
       this.name = mainNode.getAttribute("name");
       temporalProperties = PatternFormulaMap.getInstance().get(designPatternType);
    }
    
    @Override
    public StringBuilder printLogicalSpecification() {
        StringBuilder result = new StringBuilder();
        
        return result;
    }
}
