package logicalExpression.designPattern;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FlowPattern extends BaseDesignPattern {
    
    @Override
    public void process(Element mainNode) {
        
        NodeList children = mainNode.getChildNodes();
        Element first = (Element) children.item(0);
        Element flow = (Element) children.item(1);
        List<String> childrenNames = new ArrayList<>();
        
        childNodes.add(first);
        
        NodeList flowChildren = flow.getChildNodes();
        for(int i = 0; i < flowChildren.getLength(); i++){
            Element flowChild = (Element) flowChildren.item(i);
            childrenNames.add(flowChild.getAttribute("name"));
            childNodes.add(flowChild);
        }

        this.designPatternType = DesignPatternType.Flow;
        this.name = mainNode.getAttribute("name");
        
        this.arguments.add(first.getAttribute("name"));
        this.arguments.add(childrenNames.get(0));
        this.arguments.add(childrenNames.get(1));
    }
}
