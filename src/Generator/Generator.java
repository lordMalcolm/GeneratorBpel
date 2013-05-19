package Generator;
import DesignPatternScanner.DesignPatterns.BaseDesignPattern;
import DesignPatternScanner.DesignPatternScanner;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//klasa przeszukujaca cale drzewo dokumentu XML i uruchamiajaca dla kazdego "wierzcholka" logike szukajaca wzorcow, zbiera uzyskane wyniki i udostepnia
public class Generator {

    private Document _doc;
    private List<BaseDesignPattern> _designPatterns;
    private DesignPatternScanner _designPatternScanner;
    
    public Generator(DesignPatternScanner designPatternScanner, Document doc) {
        _doc = doc;
        _designPatternScanner = designPatternScanner;
        _designPatterns = new ArrayList<>();
    }
    
    //metoda uruchamiająca rekurencyjne przeszukiwanie drzewa XML, zwraca liste znalezionych wzorcow
    //docelowo powinna być zwracana struktura drzewiasta, będzie się lepiej dalej interpretowało
    public BaseDesignPattern GetTree() {
        BaseDesignPattern main = this.searchTree(_doc.getDocumentElement(), 0);
        return main;
    }
    public List<BaseDesignPattern> Scan() {
        this.searchTree(_doc.getDocumentElement(), 0);
        return _designPatterns;
    }
    
    //rekurencyjna metoda przeszukiwania podanej gałęzi
    private BaseDesignPattern searchTree(Element node, int deepLevel) {
        
        node = RemoveUnproperChildren(node);
        NodeList nodeList = node.getChildNodes();
        
        //wykonywanie operacji na kazdym wierzcholku
        BaseDesignPattern pattern = _designPatternScanner.GetPatternIfAny(node);
        if (pattern != null) {
            pattern.DeepLevel = deepLevel;
            _designPatterns.add(pattern);
        }
        
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element child = (Element) nodeList.item(i);
            BaseDesignPattern tmp = null;
            
            if (child.getNodeType() == Node.ELEMENT_NODE)
                tmp = searchTree(child, deepLevel+1);
            
            if (pattern != null && tmp != null)
                pattern.NestedPatterns.add(tmp);
        }
        return pattern;
    }
    
    //metoda usuwajaca potomne galezie, ktore nie sa typu Element (np. rozpoznane biale znaki)
    private Element RemoveUnproperChildren(Element mainNode) {
        NodeList nodes = mainNode.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++){
            Node child = nodes.item(i);
            if (child.getNodeType() != Node.ELEMENT_NODE)
                mainNode.removeChild(child);
        }
        return mainNode;
    }
}