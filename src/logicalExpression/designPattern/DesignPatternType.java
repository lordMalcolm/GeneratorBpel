package logicalExpression.designPattern;

//enum obslugiwanych wzorcow procesowych
public enum DesignPatternType {
    Sequence, Flow, Switch, While, SeqSeq;
    
    public static DesignPatternType getType(String type){
        if("Sequence".equalsIgnoreCase(type)){
            return Sequence;
        } else if("Flow".equalsIgnoreCase(type)){
            return Flow;
        } else if("Switch".equalsIgnoreCase(type)){
            return Switch;
        } else if("While".equalsIgnoreCase(type)){
            return While;
        } else if("SeqSeq".equalsIgnoreCase(type)){
            return SeqSeq;
        }
        throw new RuntimeException("niepoprawny format pliku /patterns");       
    }
}