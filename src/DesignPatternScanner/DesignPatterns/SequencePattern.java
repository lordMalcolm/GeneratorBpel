package DesignPatternScanner.DesignPatterns;

import DesignPatternScanner.DesignPatternType;
import java.util.List;
import org.w3c.dom.Element;

public class SequencePattern extends BaseDesignPattern {
    
    @Override
    public void Process(Element mainNode) {    
        List<Element> properChildren = this.GetProperChildrenAsElements(mainNode);
        List<String> childrenNames = this.GetChildrenNames(properChildren);

       this.Type = DesignPatternType.Sequence;
       this.Name = mainNode.getAttribute("name");
       this.Arguments.add(childrenNames.get(0));
       this.Arguments.add(childrenNames.get(1));
    }
}
