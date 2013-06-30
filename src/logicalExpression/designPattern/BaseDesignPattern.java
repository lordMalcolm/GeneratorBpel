package logicalExpression.designPattern;

import logicalExpression.LogicalExpression;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

//klasa przechowujaca informacje o danym wzorcu
public abstract class BaseDesignPattern extends LogicalExpression {
    public DesignPatternType designPatternType;
    public List<String> arguments;

    public BaseDesignPattern() {
        arguments = new ArrayList<>();
    }
    
    @Override
    public StringBuilder printLogicalExpression() {
        StringBuilder sb = new StringBuilder();
        sb.append(designPatternType.toString());
        sb.append("(");
        for(int i = 0; i < nestedPatterns.size(); i++){
            sb.append(nestedPatterns.get(i).printLogicalExpression());
            if (i < (nestedPatterns.size()-1))
                sb.append(",");
        }
        sb.append(")");
        return sb;
    }
    
    @Override
    public void print(StringBuilder sb) {
        for (int i = 0; i < deepLevel; i++) {
            sb.append("   ");
        }
        sb.append(designPatternType.toString());
        sb.append("-").append(name);
        sb.append("(");
        sb.append(System.getProperty("line.separator"));
        for(int i = 0; i < nestedPatterns.size(); i++){
            nestedPatterns.get(i).print(sb);
            sb.append(System.getProperty("line.separator"));
        }
        for (int i = 0; i < deepLevel; i++) {
            sb.append("   ");
        }
        sb.append(")");
    }
    
    @Override
    public String getSpecificType() {
        return designPatternType.toString();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null) {
            sb.append(" name: ");
            sb.append(name);
        }
        if (designPatternType != null) {
            sb.append(", typ: ");
            sb.append(designPatternType.toString());
        }
        if(arguments != null) {
            sb.append(", argumenty: ");
            for(String s: arguments){
                sb.append(s);
                sb.append(", ");
            }
        }
        
        String result =  new String(sb);
        return result;
    }

    abstract public void process(Element mainNode);

}