package logicalExpression.designPattern;

import logicalExpression.atomicAction.ConditionAction;
import org.w3c.dom.Element;

public class WhilePattern extends BaseDesignPattern{
    
    public WhilePattern(Element mainNode) {        
        designPatternType = DesignPatternType.While;
        name = mainNode.getAttribute("name");
        ConditionAction whileCondition = new ConditionAction(mainNode);
        nestedPatterns.add(whileCondition);
    }
    
    @Override
    public StringBuilder printLogicalSpecification() {
        StringBuilder result = new StringBuilder();
        
        return result;
    }
}
