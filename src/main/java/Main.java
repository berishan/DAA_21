import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {
        if(args.length < 2){
            throw new Exception("Argument's length should be greater than 2!" );
        }

        String text = args[0];
        String pattern = args[1];

        BMHAlgorithm algorithm = new BMHAlgorithm();
        HashMap<Character, Integer> table = algorithm.badMatchTable(pattern);
        System.out.println(algorithm.searchForPattern(text,pattern,table));

    }
}
