package logicalExpression.atomicAction.scanner;

import logicalExpression.atomicAction.ActionType;
import logicalExpression.atomicAction.AtomicAction;
import org.w3c.dom.Element;

//klasa przetwarzajaca podana galaz XML w poszukiwaniu wzorcow
public class AtomicActionScanner implements IAtomicActionScanner {

    @Override
    public AtomicAction getActionIfAny(Element node) {
        ActionType type = getActionType(node);
        
        if (type == null)
            return null;
        
        AtomicAction action = ActionFactory.CreateAction(type);
        if (action == null)
            return null;
        
        action.actionType = type;
        action.process(node);
        return action;
    }
    
    private ActionType getActionType(Element mainNode) {
        if (checkIfInvoke(mainNode))
            return ActionType.Invoke;
        
        if(checkIfReceive(mainNode))
            return ActionType.Receive;
        
        if(checkIfCondition(mainNode))
            return ActionType.Condition;
        
        return ActionType.Unknown;

    }
    
    private boolean checkIfReceive(Element node) {
        if(node.getNodeName().equals("receive"))
            return true;
        
        return false;
    }

    private boolean checkIfInvoke(Element node) {
        if(node.getNodeName().equals("invoke"))
            return true;
        
        return false;
    }

    private boolean checkIfCondition(Element node) {
        if(!node.getAttribute("condition").isEmpty())
            return true;
        
        return false;
    }
    
}
