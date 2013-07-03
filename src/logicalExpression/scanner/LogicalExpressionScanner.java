package logicalExpression.scanner;
import java.util.ArrayList;
import java.util.List;
import logicalExpression.designPattern.scanner.IDesignPatternScanner;
import logicalExpression.designPattern.scanner.DesignPatternScanner;
import logicalExpression.atomicAction.scanner.AtomicActionScanner;
import logicalExpression.atomicAction.scanner.IAtomicActionScanner;
import logicalExpression.designPattern.BaseDesignPattern;
import logicalExpression.LogicalExpression;
import logicalExpression.LogicalExpressionType;
import org.w3c.dom.Element;

//klasa przetwarzajaca podana galaz XML w poszukiwaniu wzorcow
public class LogicalExpressionScanner implements ILogicalExpressionScanner{

    private IDesignPatternScanner designPatternScanner;
    private IAtomicActionScanner atomicActionScanner;
    
    public LogicalExpressionScanner(){
        designPatternScanner = new DesignPatternScanner();
        atomicActionScanner = new AtomicActionScanner();
    }
    
    @Override
    public LogicalExpression getLogicalExpression(Element node) {
        //próba rozpoznania wzorca
        BaseDesignPattern pattern = designPatternScanner.getDesignPatternIfAny(node);
        if (pattern != null)
            return pattern;
        //jak nie wzorzec to zwracana zostaje aktywność (może być "Unknown")
        return atomicActionScanner.getActionIfAny(node);
    }

    @Override
    public List<Element> getNodesToExpand(Element node, LogicalExpression expression) {
        if (expression.logicalExpressionType == LogicalExpressionType.AtomicAction)
            return new ArrayList<>();
        
        return designPatternScanner.getNodesToExpand(node, expression);
    }
}
