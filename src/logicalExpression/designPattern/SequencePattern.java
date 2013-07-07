package logicalExpression.designPattern;

import logicalSpecification.patterns.PatternFormulaMap;
import org.w3c.dom.Element;

public class SequencePattern extends BaseDesignPattern {
    
    public SequencePattern(Element mainNode) {      
       this.designPatternType = DesignPatternType.Sequence;
       this.name = mainNode.getAttribute("name");
       temporalProperties = PatternFormulaMap.getInstance().get(designPatternType);
    }

    @Override
    public StringBuilder printLogicalSpecification() {
        StringBuilder result = new StringBuilder();
        
        return result;
    }
}
