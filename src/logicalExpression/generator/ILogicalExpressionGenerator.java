package logicalExpression.generator;

import logicalExpression.LogicalExpression;
import org.w3c.dom.Document;

public interface ILogicalExpressionGenerator {
    LogicalExpression generateLogicalExpression(Document document);
}
