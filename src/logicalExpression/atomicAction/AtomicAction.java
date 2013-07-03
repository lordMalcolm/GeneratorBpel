package logicalExpression.atomicAction;

import logicalExpression.LogicalExpression;
import logicalExpression.LogicalExpressionType;

public class AtomicAction extends LogicalExpression {
    public ActionType actionType;

    public AtomicAction() {
        actionType = ActionType.Unknown;
        logicalExpressionType = LogicalExpressionType.AtomicAction;
    }
    
    @Override
    public StringBuilder printLogicalExpression() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        return sb;
    }

    @Override
    public String getSpecificType() {
        return actionType.toString();
    }

    @Override
    public StringBuilder printLogicalSpecification() {
        return new StringBuilder("niewiadomo");
    }
}