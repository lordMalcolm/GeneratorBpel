package DesignPatternTreeGenerator;

import DesignPatternScanner.DesignPatternScanner;
import DesignPatternModels.BaseDesignPattern;
import DesignPatternScanner.IDesignPatternScanner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DesignPatternTreeGenerator implements IDesignPatternTreeGenerator{
    
    private IDesignPatternScanner designPatternScanner;

    public DesignPatternTreeGenerator() {
        designPatternScanner = new DesignPatternScanner();
    }
    
    @Override
    public BaseDesignPattern generateDesignPatternTree(Document document) {
        Element root = document.getDocumentElement();
        BaseDesignPattern main = this.searchTree(root, 0);
        return main;
    }   

    //rekurencyjna metoda przeszukiwania podanej gałęzi
    private BaseDesignPattern searchTree(Element node, int deepLevel) {
        NodeList nodeList = node.getChildNodes();
        BaseDesignPattern pattern = designPatternScanner.getDesignPatternIfAny(node);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element child = (Element) nodeList.item(i);
            BaseDesignPattern tmp = searchTree(child, deepLevel+1);
            
            if (pattern != null && tmp != null)
                pattern.NestedPatterns.add(tmp);
        }
        return pattern;
    }
}
