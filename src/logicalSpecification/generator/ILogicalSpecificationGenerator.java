package logicalSpecification.generator;

import logicalExpression.LogicalExpression;

public interface ILogicalSpecificationGenerator {
    String getLogicalSpecification(LogicalExpression expression);    
}
