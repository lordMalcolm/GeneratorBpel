package LogicalExpression.Generator;

import LogicalExpression.LogicalExpression;
import LogicalExpression.Scanner.ILogicalExpressionScanner;
import LogicalExpression.Scanner.LogicalExpressionScanner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class LogicalExpressionGenerator implements ILogicalExpressionGenerator{
    
    private ILogicalExpressionScanner logicalExpressionScanner;

    public LogicalExpressionGenerator() {
        logicalExpressionScanner = new LogicalExpressionScanner();
    }
    
    @Override
    public LogicalExpression generateLogicalExpression(Document document) {
        Element root = document.getDocumentElement();
        LogicalExpression main = this.searchTree(root, 0);
        return main;
    }   

    //rekurencyjna metoda przeszukiwania podanej gałęzi
    private LogicalExpression searchTree(Element node, int deepLevel) {
        //pobieranie rozpoznanego wzorca, a powinien zostać jakiś rozpoznany
        LogicalExpression expression = logicalExpressionScanner.getLogicalExpression(node);
        expression.deepLevel = deepLevel;
        //ponieważ wzorce są zagnieżdżane na różnych poziomach podczas tworzenia wzorca
        //dodawane są potomne gałęzie i wg nich następuje dalsze przeszukiwanie drzewa
        for (int i = 0; i < expression.childNodes.size(); i++) {
            Element child = (Element) expression.childNodes.get(i);
            LogicalExpression tmp = searchTree(child, deepLevel+1);
            
            if (expression != null && tmp != null)
                expression.nestedPatterns.add(tmp);
            
        }
        return expression;
    }
}
