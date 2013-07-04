package logicalSpecification.patterns;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import logicalExpression.designPattern.DesignPatternType;


public class PatternFormulaMap {
     
    private static PatternFormulaMap instance;
    
    private HashMap<DesignPatternType, PatternTemporalProperties> map;
    
    private static final String INPUT_FILE = "patterns";
    
    private PatternFormulaMap(){
        try{
            map = new HashMap<>();
            File file = new File(INPUT_FILE);
            Scanner scanner = new Scanner(file);
            String in = null;
            String out = null;
            String type = null;
            List<String> formulas = new ArrayList<String>();
            List<String> params = new ArrayList<String>();
            int i = 0;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.isEmpty()){
                    i = 0;
                    PatternTemporalProperties pp = new PatternTemporalProperties(in, out, params, formulas);
                    map.put(DesignPatternType.getType(type) , pp);
                    formulas = new ArrayList<String>();
                    params = new ArrayList<String>();
                    continue;
                }
                if(i == 0){
                    String[] parts = line.split("\\(");
                    if(parts.length != 2){
                        throw new RuntimeException("niepoprawny format pliku");
                    }
                    type = parts[0];
                    String[] arguments = parts[1].split(",");
                    for(int j=0; j < arguments.length - 1; j++){
                        params.add(arguments[j]);
                    }
                    String lastParam = 
                            arguments[arguments.length - 1];
                    params.add(lastParam.substring(0, lastParam.indexOf(")")));
                    i++;
                } else if(i == 1){
                    in = getIn(line);
                    out = getOut(line);
                    i++;
                } else if(i == 2){
                    formulas.add(line);
                }
            }
            PatternTemporalProperties pp = new PatternTemporalProperties(in, out, params, formulas);
            map.put(DesignPatternType.getType(type) , pp);
        }catch(FileNotFoundException ex){
            throw new RuntimeException(ex);
        }
    }
    
    private String getIn(String line){
        if(line.contains("in")){
            int start = line.indexOf("in") + 3;
            int end = line.indexOf("out");
            return line.substring(start, end).trim();
        } else{
            throw new RuntimeException("niepoprawna struktura pliku");
        }
    }
    
    private String getOut(String line){
        if(line.contains("out")){
            int start = line.indexOf("out") + 4;
            return line.substring(start).trim();
        } else{
            throw new RuntimeException("niepoprawna struktura pliku");
        }
    }
    
    public static PatternFormulaMap getInstance(){
        if(instance == null){
            instance = new PatternFormulaMap();
        }
        return instance;
    }
    
    public PatternTemporalProperties get(DesignPatternType type){
        PatternTemporalProperties result = map.get(type);
        return result;
    }
}
