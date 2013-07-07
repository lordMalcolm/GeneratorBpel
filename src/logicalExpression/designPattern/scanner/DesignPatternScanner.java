package logicalExpression.designPattern.scanner;
import java.util.ArrayList;
import java.util.List;
import logicalExpression.LogicalExpression;
import logicalExpression.designPattern.DesignPatternType;
import logicalExpression.designPattern.BaseDesignPattern;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Klasa przetwarząjaca podaną gałąz XML w poszukiwaniu wzorców
 */ 
public class DesignPatternScanner implements IDesignPatternScanner{

    @Override
    public BaseDesignPattern getDesignPatternIfAny(Element node) {
        DesignPatternType type = getPatternType(node);
        //możliwe jest nierozpoznanie wzorca
        if (type == null)
            return null;
        
        return DesignPatternFactory.CreatePattern(type, node);
    }
    
    private DesignPatternType getPatternType(Element mainNode) {
        
        //tutaj kolejność jest ważna (niestety), może da się inaczej
        //narazie flow musi bys sprawdzony przed sequencem
        //a wynika to z tego że flow jest zagnieżdżony w sequence
        //(tak jest w podanych wzorcach i powiązanych z nimi wyrażeniami logicznymi)
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

    @Override
    public List<Element> getNodesToExpand(Element node, LogicalExpression expression) {
        BaseDesignPattern pattern = (BaseDesignPattern) expression;
        return getNodesForPatternType(node, pattern.designPatternType);
    }

    private List<Element> getNodesForPatternType(Element node, DesignPatternType designPatternType) {
        switch (designPatternType){
            case Flow:
                return getNodesForFlow(node);
            case SeqSeq:
                return getNodesForSeqSeq(node);
            case Sequence:
                return getNodesForSequence(node);
            case Switch:
                return getNodesForSwitch(node);
            case While:
                return getNodesForWhile(node);
        }
        return null;
    }

    private List<Element> getNodesForFlow(Element node) {
        List<Element> result = new ArrayList<>();
        
        NodeList children = node.getChildNodes();
        Element first = (Element) children.item(0);
        Element flow = (Element) children.item(1);
        
        result.add(first);
        
        NodeList flowChildren = flow.getChildNodes();
        for(int i = 0; i < flowChildren.getLength(); i++){
            Element flowChild = (Element) flowChildren.item(i);
            result.add(flowChild);
        }
        return result;
    }

    private List<Element> getNodesForSeqSeq(Element node) {
        List<Element> result = new ArrayList<>();
       
        result.add((Element) node.getChildNodes().item(0));
        result.add((Element) node.getChildNodes().item(1));
        result.add((Element) node.getChildNodes().item(2));
        
        return result;
    }

    private List<Element> getNodesForSequence(Element node) {
        List<Element> result = new ArrayList<>();
       
        result.add((Element) node.getChildNodes().item(0));
        result.add((Element) node.getChildNodes().item(1));
        
        return result;
       
    }

    private List<Element> getNodesForSwitch(Element node) {
        List<Element> result = new ArrayList<>();
        
        NodeList children = node.getChildNodes();
        Element casePart = (Element) children.item(0);
        Element otherwisePart = (Element) children.item(1);
        
        Element casePartChild = (Element) casePart.getChildNodes().item(0);
        Element otherwisePartChild = (Element) otherwisePart.getChildNodes().item(0);
        
        result.add(casePart);
        result.add(casePartChild);
        result.add(otherwisePartChild);
        
        return result;
    }

    private List<Element> getNodesForWhile(Element node) {
        List<Element> result = new ArrayList<>();
        
        Element child = (Element) node.getChildNodes().item(0);
        result.add(child);
        
        return result;
    }
}
