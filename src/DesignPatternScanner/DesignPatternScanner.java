package DesignPatternScanner;

import DesignPatternModels.DesignPatternType;
import DesignPatternModels.BaseDesignPattern;
import org.w3c.dom.Element;

//klasa przetwarzajaca podana galaz XML w poszukiwaniu wzorcow
public class DesignPatternScanner implements IDesignPatternScanner{

    @Override
    public BaseDesignPattern getDesignPatternIfAny(Element node) {
        DesignPatternType type = getPatternType(node);
        
        if (type == null)
            return null;
        
        BaseDesignPattern pattern = DesignPatternFactory.CreatePattern(type);
        pattern.Process(node);
        return pattern;
    }
    
    private DesignPatternType getPatternType(Element mainNode) {
        
        //tutaj kolejność jest ważna (niestety), może da się inaczej, narazie flow musi bys sprawdzony przed sequencem
        //a wynika to z tego że flow jest zagnieżdżony w sequence (tak jest w podanych wzorcach i powiązanych z nimi wyrażeniami logicznymi)
        if(CheckIfWhile(mainNode))
            return DesignPatternType.While;
        
        if (CheckIfFlow(mainNode))
            return DesignPatternType.Flow;
     
        if (CheckIfSwitch(mainNode))
            return DesignPatternType.Switch;
        
        if (CheckIfSequence(mainNode))
            return DesignPatternType.Sequence;
        
        return DesignPatternType.Atomic;
    }
    
    private boolean CheckIfFlow(Element node) {
        if(node.getChildNodes().getLength() != 2)
            return false;
        
        if(node.getChildNodes().item(1).getNodeName().equals("flow"))
            return true;
        
        return false;
    }
    
    private boolean CheckIfSwitch(Element node) {
        if(node.getChildNodes().getLength() != 2)
            return false;
        
        if(node.getNodeName().equals("switch"))
            return true;
        return false;
    }

    private boolean CheckIfSequence(Element node) {
        if(node.getChildNodes().getLength() != 2)
            return false;
        
        if(node.getNodeName().equals("sequence"))
            return true;
        return false;
    }    

    private boolean CheckIfWhile(Element node) {
        if(node.getChildNodes().getLength() != 1)
            return false;
        
        if(node.getNodeName().equals("switch"))
            return true;
        return false;
    }
}
