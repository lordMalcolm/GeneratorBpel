package LogicalExpression.AtomicAction;

import LogicalExpression.DesignPattern.BaseDesignPattern;
import org.w3c.dom.Element;

public class AtomicAction extends BaseDesignPattern {
    
    protected ActionType actionType;
    
    public void Process(Element mainNode) {
       logicalExpressionType = logicalExpressionType.AtomicAction;
       type = type.Atomic;
       name = mainNode.getAttribute("name");
    }
    
    @Override
    public void PrintLogicalExpression(StringBuilder sb) {
        sb.append(name);
    }
    
    @Override
    public void Print(StringBuilder sb) {
        for (int i = 0; i < deepLevel; i++) {
            sb.append("   ");
        }
        sb.append(actionType.toString());
        sb.append("(");
        sb.append(name);
        sb.append(")");
    }
}
