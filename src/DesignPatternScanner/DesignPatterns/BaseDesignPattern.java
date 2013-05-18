package DesignPatternScanner.DesignPatterns;

import DesignPatternScanner.DesignPatternType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//klasa przechowujaca informacje o danym wzorcu
public abstract class BaseDesignPattern {
    public DesignPatternType Type;
    public String Name;
    public List<String> Arguments;
    public List<BaseDesignPattern> NestedPatterns;
    public int DeepLevel;

    public BaseDesignPattern() {
        Arguments = new ArrayList<>();
    }
    
    public String ToString() {
        StringBuilder sb = new StringBuilder();
        if (Name != null) {
            sb.append(" name: ");
            sb.append(Name);
        }
        if (Type != null) {
            sb.append(", typ: ");
            sb.append(Type.toString());
        }
        if(Arguments != null) {
            sb.append(", argumenty: ");
            for(String s: Arguments){
                sb.append(s);
                sb.append(", ");
            }
        }
        
        String result =  new String(sb);
        return result;
    }
        
    protected Element GetNodeAsElement(NodeList nodes, int i) {
        Node child = nodes.item(i);
        if (child.getNodeType() != Node.ELEMENT_NODE)
            return null;
        return (Element) child;   
    }

    protected List<Element> GetProperChildrenAsElements(Element parentNode) {
        NodeList nodes = parentNode.getChildNodes();
        List<Element> result = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element castedChild = this.GetNodeAsElement(nodes, i);
            if (castedChild != null)
                result.add(castedChild);
        }
        return result;
    }

    protected List<String> GetChildrenNames(List<Element> properChildren) {
        List<String> result = new ArrayList<>();
        for (Iterator<Element> it = properChildren.iterator(); it.hasNext();) {
            Element e = it.next();
            result.add(e.getAttribute("name"));
        }
        return result;
    }

    public void Process(Element mainNode) {
    
    }   
}