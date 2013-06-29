package DesignPatternModels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SwitchPattern extends BaseDesignPattern{
    
    @Override
    public void Process(Element mainNode) {       
        NodeList children = mainNode.getChildNodes();
        Element casePart = (Element) children.item(0);
        Element otherwisePart = (Element) children.item(1);
        
        Element casePartChild = (Element) casePart.getChildNodes().item(0);
        Element otherwisePartChild = (Element) otherwisePart.getChildNodes().item(0);
        childNodes.add(casePart);
        childNodes.add(casePartChild);
        childNodes.add(otherwisePartChild);
        
        type = DesignPatternType.Switch;
        name = mainNode.getAttribute("name");
        
        arguments.add(casePart.getAttribute("name"));
        arguments.add(casePartChild.getAttribute("name"));
        arguments.add(otherwisePartChild.getAttribute("name"));

        
    }
}
