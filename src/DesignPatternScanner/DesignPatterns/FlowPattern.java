package DesignPatternScanner.DesignPatterns;

import DesignPatternScanner.DesignPatternType;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FlowPattern extends BaseDesignPattern {
    
    @Override
    public void Process(Element mainNode) {
        
        NodeList children = mainNode.getChildNodes();
        Element first = (Element) children.item(0);

        Element flow = (Element) children.item(1);
        List<Element> flowChildren = GetProperChildrenAsElements(flow);
        
        List<String> flowChNames = new ArrayList<>();
        
        for(int i = 0; i < flowChildren.size(); i++){
            Element flowChild = (Element) flowChildren.get(i);
            flowChNames.add(flowChild.getAttribute("name"));
        }
       
        System.out.print(mainNode.getAttribute("name"));
        System.out.print("-FLOW(");
        System.out.print(first.getAttribute("name"));
        System.out.print(",");
        System.out.print(flowChNames.get(0));
        System.out.print(",");
        System.out.print(flowChNames.get(1));
        System.out.print(")");
        System.out.println();
        
        this.Type = DesignPatternType.Flow;
    }
}
