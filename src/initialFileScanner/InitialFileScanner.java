package initialFileScanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * Klasa wstępnie przetwarzająca dokument w formacie DOM,
 * usuwając z niego niepotrzebne i nieistotne elementy
 */
public class InitialFileScanner implements IInitialFileScanner{

    @Override
    public Document prepareAndCheckFile(Document document) {
       
        Element baseNode = document.getDocumentElement();
        searchTree(baseNode);
        
        return document;
    }
    
    //rekurencyjna metoda przeszukiwania podanej gałęzi
    private void searchTree(Element node) {
        node = removeNullNodes(node);
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element child = (Element) nodeList.item(i);
            searchTree(child);
        }
    }
    
    //metoda usuwajaca potomne galezie, ktore nie sa typu Element (np. rozpoznane biale znaki)
    private Element removeNullNodes(Element mainNode) {
        NodeList nodes = mainNode.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++){
            Node child = nodes.item(i);
            if (child.getNodeType() != Node.ELEMENT_NODE)
                mainNode.removeChild(child);
        }
        return mainNode;
    }
}
