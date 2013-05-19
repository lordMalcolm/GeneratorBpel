package DesignPatternScanner.DesignPatterns;

import DesignPatternScanner.DesignPatternType;
import org.w3c.dom.Element;

public class AtomicAction extends BaseDesignPattern {
    
    @Override
    public void Process(Element mainNode) {
       this.Type = DesignPatternType.Atomic;
       this.Name = mainNode.getAttribute("name");
    }
    
    @Override
    public void Print(StringBuilder sb) {
        sb.append(Type.toString());
        sb.append("(");
        sb.append(Name);
        sb.append(")");
    }
}
