package logicalSpecification.generator;
import logicalExpression.ILogicalExpression;
/**
 * Klasa przetwarzająca zbiór wyrażeń logicznych do Stringa
 */
public class LogicalSpecificationGenerator implements ILogicalSpecificationGenerator {

    @Override
    public String printLogicalSpecification(ILogicalExpression expression) {
        return expression.printLogicalSpecification().toString();
    }
    
}
