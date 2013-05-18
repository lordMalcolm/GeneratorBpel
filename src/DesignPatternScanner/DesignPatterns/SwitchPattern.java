package DesignPatternScanner.DesignPatterns;

import DesignPatternScanner.DesignPatternType;
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
        
        System.out.print(mainNode.getAttribute("name"));
        System.out.print("-SWITCH(");
        System.out.print(childrenNames.get(0));
        System.out.print(",");
        System.out.print(n.get(0));
        System.out.print(",");
        System.out.print(n.get(1));
        System.out.print(")");
        System.out.println();
        
        this.Type = DesignPatternType.Switch;
    }
}
