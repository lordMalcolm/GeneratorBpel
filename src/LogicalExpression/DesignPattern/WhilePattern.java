package LogicalExpression.DesignPattern;

import org.w3c.dom.Element;

public class WhilePattern extends BaseDesignPattern{
    
    @Override
    public void Process(Element mainNode) {
        Element child = (Element) mainNode.getChildNodes().item(0);
        type = DesignPatternType.While;
        name = mainNode.getAttribute("name");
        arguments.add(child.getAttribute("name"));
        //childNodes.add(mainNode);
        childNodes.add(child);
        
    }
}
