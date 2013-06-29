package DesignPatternModels;

import org.w3c.dom.Element;

public class AtomicAction extends BaseDesignPattern {
    
    @Override
    public void Process(Element mainNode) {
       this.type = DesignPatternType.Atomic;
       this.name = mainNode.getAttribute("name");
    }
    
    @Override
    public void Print(StringBuilder sb) {
        for (int i = 0; i < deepLevel; i++) {
            sb.append("   ");
        }
        sb.append(type.toString());
        sb.append("(");
        sb.append(name);
        sb.append(")");
    }
}
