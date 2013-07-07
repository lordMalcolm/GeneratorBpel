package logicalSpecification.patterns;

import java.util.List;
/*
 * Definicja wzorcu procesowego
 */
public class PatternTemporalProperties {
    
    private final String in;
    private final String out;
    private final List<String> params;
    private final List<String> formulas;
    
    public PatternTemporalProperties(String in, String out, List<String> params, List<String> formulas){
        this.in = in;
        this.out = out;
        this.params = params;
        this.formulas = formulas;
    }

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    public List<String> getParams() {
        return params;
    }

    public List<String> getFormulas() {
        return formulas;
    }
}
