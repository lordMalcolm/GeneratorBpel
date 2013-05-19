package DesignPatternScanner.DesignPatterns;

import DesignPatternScanner.DesignPatternType;
import org.w3c.dom.Element;

public class WhilePattern extends BaseDesignPattern{
    
    @Override
    public void Process(Element mainNode) {
        Element child = (Element) mainNode.getChildNodes().item(0);
        this.Type = DesignPatternType.While;
        this.Name = mainNode.getAttribute("name");
        this.Arguments.add(child.getAttribute("name"));
    }
}
