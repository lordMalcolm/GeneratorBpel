package LogicalExpression.DesignPattern;

import LogicalExpression.LogicalExpression;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

//klasa przechowujaca informacje o danym wzorcu
public abstract class BaseDesignPattern extends LogicalExpression{
    public DesignPatternType type;
    public List<String> arguments;

    public BaseDesignPattern() {
        arguments = new ArrayList<>();
    }
    
    @Override
    public void PrintLogicalExpression(StringBuilder sb) {
        sb.append(type.toString());
        sb.append("(");
        for(int i = 0; i < nestedPatterns.size(); i++){
            nestedPatterns.get(i).PrintLogicalExpression(sb);
            if (i < (nestedPatterns.size()-1))
                sb.append(",");
        }
        sb.append(")");
    }
    
    @Override
    public void Print(StringBuilder sb) {
        for (int i = 0; i < deepLevel; i++) {
            sb.append("   ");
        }
        sb.append(type.toString());
        sb.append("-").append(name);
        sb.append("(");
        sb.append(System.getProperty("line.separator"));
        for(int i = 0; i < nestedPatterns.size(); i++){
            nestedPatterns.get(i).Print(sb);
            sb.append(System.getProperty("line.separator"));
        }
        for (int i = 0; i < deepLevel; i++) {
            sb.append("   ");
        }
        sb.append(")");
    }
    
    public String ToString() {
        StringBuilder sb = new StringBuilder();
        if (name != null) {
            sb.append(" name: ");
            sb.append(name);
        }
        if (type != null) {
            sb.append(", typ: ");
            sb.append(type.toString());
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

    abstract public void Process(Element mainNode);

}