package logicalSpecification.generator;

import logicalExpression.ILogicalExpression;

public interface ILogicalSpecificationGenerator {
    String printLogicalSpecification(ILogicalExpression expression);
}