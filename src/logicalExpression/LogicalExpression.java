package logicalExpression;
/**
 * Abstrakcyjna klasa bazowa dla wszystkich 
 * wyrażeń logicznych(wzorców i aktywności)
 */
public abstract class LogicalExpression implements ILogicalExpression {
    public LogicalExpressionType logicalExpressionType;
    public String name;
    public int deepLevel;
}
