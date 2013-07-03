package logicalSpecification.generator;
import logicalExpression.ILogicalExpression;

public class LogicalSpecificationGenerator implements ILogicalSpecificationGenerator {

    @Override
    public String printLogicalSpecification(ILogicalExpression expression) {
        return expression.printLogicalSpecification().toString();
    }
    
}
