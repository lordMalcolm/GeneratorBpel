package initialFileScanner;

import org.w3c.dom.Document;

public interface IInitialFileScanner {
    Document prepareAndCheckFile(Document inDocument);
}
