package logicalExpression.designPattern;

import logicalExpression.atomicAction.ConditionAction;
import org.w3c.dom.Element;

public class WhilePattern extends BaseDesignPattern{
    
    @Override
    public void process(Element mainNode) {
        Element child = (Element) mainNode.getChildNodes().item(0);
        designPatternType = DesignPatternType.While;
        name = mainNode.getAttribute("name");
        
        ConditionAction whileCondition = new ConditionAction();
        whileCondition.process(mainNode);
        
        nestedPatterns.add(whileCondition);
        childNodes.add(child);
    }
}
