package Generator;

import org.w3c.dom.Document;

public interface IGenerator {
    Document readFile(String filePath) throws Exception;
    boolean checkModel();
    void run() throws Exception;
}
