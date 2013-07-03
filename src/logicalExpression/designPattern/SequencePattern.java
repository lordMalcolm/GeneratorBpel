package logicalExpression.designPattern;

import logicalSpecification.patterns.PatternFormulaMap;
import org.w3c.dom.Element;

public class SequencePattern extends BaseDesignPattern {
    
    public SequencePattern(Element mainNode) {      
       this.designPatternType = DesignPatternType.Sequence;
       this.name = mainNode.getAttribute("name");
       patternParams = PatternFormulaMap.getInstance().get(designPatternType);
    }

    @Override
    public StringBuilder printLogicalSpecification() {
        StringBuilder result = new StringBuilder();
//        
//        result.append(nestedPatterns.get(0).name).append("=><>").append(nestedPatterns.get(1).name);
//        result.append(",[]~(").append(nestedPatterns.get(0).name).append("&").append(nestedPatterns.get(1).name).append(")");
        
        return result;
    }
}
