package DesignPatternModels;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

public class SwitchPattern extends BaseDesignPattern{
    
    @Override
    public void Process(Element mainNode) {
               
        List<Element> properChildren = this.GetProperChildrenAsElements(mainNode);
        List<String> childrenNames = this.GetChildrenNames(properChildren);
        List<String> n = new ArrayList<>();
        
        for (Element element : properChildren) {
            List<Element> nestedChildren = GetProperChildrenAsElements(element);
            for(Element nested: nestedChildren){
                n.add(nested.getAttribute("name"));
            }
        }
        
        this.Type = DesignPatternType.Switch;
        this.Name = mainNode.getAttribute("name");
        this.Arguments.add(childrenNames.get(0));
        this.Arguments.add(n.get(0));
        this.Arguments.add(n.get(1));
        
    }
}
