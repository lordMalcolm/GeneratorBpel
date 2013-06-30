package LogicalExpression.AtomicAction.Scanner;

import LogicalExpression.AtomicAction.ActionType;
import LogicalExpression.AtomicAction.AtomicAction;
import org.w3c.dom.Element;

//klasa przetwarzajaca podana galaz XML w poszukiwaniu wzorcow
public class AtomicActionScanner implements IAtomicActionScanner {

    @Override
    public AtomicAction getActionIfAny(Element node) {
        ActionType type = getActionType(node);
        
        if (type == null)
            return null;
        
        AtomicAction action = ActionFactory.CreateAction(type);
        action.Process(node);
        return action;
    }
    
    private ActionType getActionType(Element mainNode) {
        return ActionType.Invoke;

    }
//    
//    private boolean CheckIfFlow(Element node) {
//        if(node.getChildNodes().getLength() != 2)
//            return false;
//        
//        if(node.getChildNodes().item(1).getNodeName().equals("flow"))
//            return true;
//        
//        return false;
//    }
    
}
