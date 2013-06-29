package DesignPatternTreeGenerator;

import DesignPatternScanner.DesignPatternScanner;
import DesignPatternModels.BaseDesignPattern;
import DesignPatternScanner.IDesignPatternScanner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
        //pobieranie rozpoznanego wzorca, a powinien zostać jakiś rozpoznany
        BaseDesignPattern pattern = designPatternScanner.getDesignPatternIfAny(node);
        pattern.deepLevel = deepLevel;
        //ponieważ wzorce są zagnieżdżane na różnych poziomach podczas tworzenia wzorca
        //dodawane są potomne gałęzie i wg nich następuje dalsze przeszukiwanie drzewa
        for (int i = 0; i < pattern.childNodes.size(); i++) {
            Element child = (Element) pattern.childNodes.get(i);
            BaseDesignPattern tmp = searchTree(child, deepLevel+1);
            
            if (pattern != null && tmp != null)
                pattern.nestedPatterns.add(tmp);
            
        }
        return pattern;
    }
}
