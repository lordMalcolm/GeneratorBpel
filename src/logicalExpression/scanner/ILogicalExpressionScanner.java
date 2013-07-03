package logicalExpression.scanner;

import java.util.List;
import logicalExpression.LogicalExpression;
import org.w3c.dom.Element;

public interface ILogicalExpressionScanner {
    LogicalExpression getLogicalExpression(Element node);
    List<Element> getNodesToExpand(Element node, LogicalExpression expression);
}
