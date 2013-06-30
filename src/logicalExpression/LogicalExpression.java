package logicalExpression;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

public abstract class LogicalExpression implements ILogicalExpression {
    public LogicalExpressionType logicalExpressionType;
    public String name;
    public int deepLevel;
    public List<Element> childNodes;
    
    public LogicalExpression() {
        childNodes = new ArrayList<>();
    }
}
