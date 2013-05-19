package GeneratorUI;

import DesignPatternScanner.DesignPatterns.BaseDesignPattern;
import DesignPatternScanner.DesignPatternScanner;
import Generator.Generator;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

//klasa glowna ktora powinna zapewnic interfejs dla uzytkownika i odpowiadac za uruchomianie calej logiki generatora
public class GeneratorUI {
    
    static DocumentBuilderFactory _dbf;
    static DocumentBuilder _db;
    static DesignPatternScanner _designPatternScanner;  
    
    public static void main(String[] args) {
        Document document = null;
        try {
            Initialize();
            String fileName = "bpel02.xml";
            File file = new File(fileName);
            document = _db.parse(file);
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(GeneratorUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (document == null) {
            System.out.println("Prawdopodobnie nie ma takiego pliku.");
            return;
        }

        Generator generator = new Generator(_designPatternScanner, document);        
        List<BaseDesignPattern> designPatterns = generator.Scan();
        
        System.out.println();
        for (Iterator<BaseDesignPattern> it = designPatterns.iterator(); it.hasNext();) {
            BaseDesignPattern dp = it.next();
            System.out.println(dp.ToString());
        }
        
        BaseDesignPattern top = generator.GetTree();
        StringBuilder sb = new StringBuilder();
        top.Print(sb);
        System.out.println(sb.toString());
    }
    
    private static void Initialize() throws ParserConfigurationException, SAXException, IOException {
        _dbf = DocumentBuilderFactory.newInstance();
        _db = _dbf.newDocumentBuilder();
        _designPatternScanner = new DesignPatternScanner();
    }
}