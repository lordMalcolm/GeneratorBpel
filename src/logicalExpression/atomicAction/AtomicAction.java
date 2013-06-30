package logicalExpression.atomicAction;

import logicalExpression.LogicalExpression;
import logicalExpression.LogicalExpressionType;
import org.w3c.dom.Element;

public class AtomicAction extends LogicalExpression {
    
    public ActionType actionType;
    
    public void Process(Element mainNode) {
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
    public void print(StringBuilder sb) {
        for (int i = 0; i < deepLevel; i++) {
            sb.append("   ");
        }
        sb.append(actionType.toString());
        sb.append("(");
        sb.append(name);
        sb.append(")");
    }

    @Override
    public String getSpecificType() {
        return actionType.toString();
    }
}
