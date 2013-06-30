package LogicalExpression;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

public abstract class LogicalExpression implements ILogicalExpression {
    protected LogicalExpressionType logicalExpressionType;
    public String name;
    public int deepLevel;
    public List<Element> childNodes;
    public List<LogicalExpression> nestedPatterns;
    
    public LogicalExpression(){
        nestedPatterns = new ArrayList<>();
        childNodes = new ArrayList<>();
    }
}
