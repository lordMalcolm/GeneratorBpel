package logicalExpression.atomicAction;

import logicalExpression.LogicalExpressionType;
import org.w3c.dom.Element;

public class ConditionAction extends AtomicAction {
    @Override
    public void process(Element mainNode) {
       logicalExpressionType = LogicalExpressionType.AtomicAction;
       name = mainNode.getAttribute("condition");
    }
}
