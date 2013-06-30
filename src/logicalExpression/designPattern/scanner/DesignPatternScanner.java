package logicalExpression.designPattern.scanner;

import logicalExpression.LogicalExpressionType;
import logicalExpression.designPattern.DesignPatternType;
import logicalExpression.designPattern.BaseDesignPattern;
import org.w3c.dom.Element;

//klasa przetwarzajaca podana galaz XML w poszukiwaniu wzorcow
public class DesignPatternScanner implements IDesignPatternScanner{

    @Override
    public BaseDesignPattern getDesignPatternIfAny(Element node) {
        DesignPatternType type = getPatternType(node);
        
        if (type == null)
            return null;
        
        BaseDesignPattern pattern = DesignPatternFactory.CreatePattern(type);
        pattern.logicalExpressionType = LogicalExpressionType.DesignPattern;
        pattern.designPatternType = type;
        pattern.process(node);
        return pattern;
    }
    
    private DesignPatternType getPatternType(Element mainNode) {
        
        //tutaj kolejność jest ważna (niestety), może da się inaczej, narazie flow musi bys sprawdzony przed sequencem
        //a wynika to z tego że flow jest zagnieżdżony w sequence (tak jest w podanych wzorcach i powiązanych z nimi wyrażeniami logicznymi)
        if(checkIfWhile(mainNode))
            return DesignPatternType.While;
        
        if (checkIfFlow(mainNode))
            return DesignPatternType.Flow;
     
        if (checkIfSwitch(mainNode))
            return DesignPatternType.Switch;
        
        if (checkIfSequence(mainNode))
            return DesignPatternType.Sequence;
        
        if (checkIfSeqSeq(mainNode))
            return DesignPatternType.SeqSeq;
        
        return null;
    }
    
    private boolean checkIfFlow(Element node) {
        if(node.getChildNodes().getLength() != 2)
            return false;
        
        if(node.getChildNodes().item(1).getNodeName().equals("flow"))
            return true;
        
        return false;
    }
    
    private boolean checkIfSwitch(Element node) {
        if(node.getChildNodes().getLength() != 2)
            return false;
        
        if(node.getNodeName().equals("switch"))
            return true;
        return false;
    }

    private boolean checkIfSequence(Element node) {
        if(node.getChildNodes().getLength() != 2)
            return false;
        
        if(node.getChildNodes().item(1).getNodeName().equals("flow"))
            return false;
        
        if(node.getNodeName().equals("sequence"))
            return true;
        return false;
    }    

    private boolean checkIfWhile(Element node) {
        if(node.getChildNodes().getLength() != 1)
            return false;
        
        if(node.getNodeName().equals("while"))
            return true;
        return false;
    }
    
    private boolean checkIfSeqSeq(Element node) {
        if (node.getChildNodes().getLength() != 3)
            return false;
        
        if(node.getNodeName().equals("sequence"))
            return true;
        
        return false;
    }
}
