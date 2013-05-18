package DesignPatternScanner.DesignPatterns;

import DesignPatternScanner.DesignPatternType;
import java.util.List;
import org.w3c.dom.Element;

public class SequencePattern extends BaseDesignPattern {
    
    @Override
    public void Process(Element mainNode) {    
        List<Element> properChildren = this.GetProperChildrenAsElements(mainNode);
        List<String> childrenNames = this.GetChildrenNames(properChildren);
        System.out.print(mainNode.getAttribute("name"));
        System.out.print("-SEQUENCE(");
        System.out.print(childrenNames.get(0));
        System.out.print(",");
        System.out.print(childrenNames.get(1));
        System.out.print(")");
        System.out.println();
        
       this.Type = DesignPatternType.Sequence;
    }
}
