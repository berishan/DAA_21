import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class BMHAlgorithm {

    public HashMap<Character, Integer> badMatchTable(String pattern) throws Exception{

        pattern = pattern.replaceAll("\\s+", "").toLowerCase();
        int patternLength = pattern.length();
        if(patternLength<1){
            throw new Exception("Pattern's length must be greater than 1!");
        }
        HashMap<Character, Integer> table = new HashMap<>();
        for (int i = 0; i < patternLength-1; i++) {
            table.put(pattern.charAt(i), patternLength-i-1);
        }
        table.put('*', patternLength);
        return table;

    }
    public int searchForPattern(String text, String pattern, HashMap<Character, Integer> table) throws Exception {
        text = text.replaceAll("\\s+", "").toLowerCase();
        pattern = pattern.replaceAll("\\s+", "").toLowerCase();

        int patternLength, patternPosition, textLength, textFirstPosition, textLastPosition;
        patternLength = pattern.length();
        patternPosition = patternLength - 1;
        textLength = text.length();
        textFirstPosition = 0;
        textLastPosition = textFirstPosition + patternPosition;
        if (textLength < 1) {
            throw new Exception("Text's length must be greater than 1!");
        }
        if (patternLength < 1) {
            throw new Exception("Pattern's length must be greater than 1!");
        }
        if (textLength < patternLength) {
            throw new Exception("Text's length must be greater than pattern's length!");
        }

        for (int i = 0; i < patternLength; i++) {
            if (text.charAt(textLastPosition) == pattern.charAt(patternPosition)) {
                textLastPosition--;
                patternPosition--;
                if(patternPosition==0){
                    return textFirstPosition;
                }
            } else {
              int shift = calculateShift(text.charAt(textLastPosition), table);
              textFirstPosition = textFirstPosition + shift;
              patternPosition = patternLength -1;
              textLastPosition = textFirstPosition + patternPosition;
              i = 0;
            }
        }
        return -1;
    }


    private int calculateShift(char charAt, HashMap<Character, Integer> table) {
        if(table.containsKey(charAt)){
            return table.get(charAt);
        }
        return table.get('*');
    }

}
