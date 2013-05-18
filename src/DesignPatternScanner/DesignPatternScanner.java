package DesignPatternScanner;

import DesignPatternScanner.DesignPatterns.BaseDesignPattern;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//klasa przetwarzajaca podana galaz XML w poszukiwaniu wzorcow
public class DesignPatternScanner {

    public BaseDesignPattern GetPatternIfAny(Element mainNode) {
        DesignPatternType type = GetPatternType(mainNode);
        
        if (type == null)
            return null;
        
        BaseDesignPattern pattern = DesignPatternFactory.CreatePattern(type);
        pattern.Process(mainNode);
        return pattern;
    }
    
    private DesignPatternType GetPatternType(Element mainNode) { 
        
        //tak narazie wynika, że każdy z obsługiwanych wzorców ma dokładnie dwa zagnieżdżenia
        if(mainNode.getChildNodes().getLength() != 2)
            return null;
        
        //tutaj kolejność jest ważna (niestety), może da się inaczej, narazie flow musi bys sprawdzony przed sequencem
        if (CheckIfFlow(mainNode))
            return DesignPatternType.Flow;
     
        if (CheckIfSwitch(mainNode))
            return DesignPatternType.Switch;
        
        if (CheckIfSequence(mainNode))
            return DesignPatternType.Sequence;
        
        return null;
    }
    
    private boolean CheckIfFlow(Element node) {
        if(node.getChildNodes().item(1).getNodeName().equals("flow"))
            return true;
        return false;
    }
    
    private boolean CheckIfSwitch(Element node) {
        if(node.getNodeName().equals("switch"))
            return true;
        return false;
    }

    private boolean CheckIfSequence(Element node) {
        if(node.getNodeName().equals("sequence"))
            return true;
        return false;
    }    
}
