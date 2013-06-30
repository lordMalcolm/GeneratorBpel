package logicalExpression.atomicAction;

import logicalExpression.LogicalExpression;
import logicalExpression.LogicalExpressionType;
import org.w3c.dom.Element;

public class AtomicAction extends LogicalExpression {
    
    public ActionType actionType;
    
    @Override
    public void process(Element mainNode) {
       logicalExpressionType = LogicalExpressionType.AtomicAction;
       name = mainNode.getAttribute("name");
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
}
