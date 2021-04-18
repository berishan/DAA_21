import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
     if(args.length < 2){
           throw new Exception("Argument's length should be greater than 2!" );
     }
     BMHAlgorithm algorithm = new BMHAlgorithm();
     HashMap<Character, Integer> table;
     switch (args.length){
         case 2:
             String text = args[0];
             String pattern = args[1];
             table = algorithm.badMatchTable(pattern);
             System.out.println("Pattern " + pattern + " begins on index " + algorithm.searchForPattern(text, pattern, table));
             break;
         case 3:
             if (!args[0].contentEquals("--file")) {
                 throw new Exception("The flag must be --file when reading from a file!");
             }
             String filePath = args[1];
             text = readFromFile(filePath);
             pattern = args[2];
             table = algorithm.badMatchTable(pattern);
             System.out.println("Pattern " + pattern + " begins on index " + algorithm.searchForPattern(text, pattern, table) + " of your file");
             break;
     }
    }
    public static String readFromFile(String fileName) throws Exception {
        File file = null;
        Scanner scanner = null;
        try {
            file = new File(fileName);
            scanner = new Scanner(file);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                sb.append(data).append(" ");
            }
            scanner.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            System.out.println("Given file does not exist!");
            e.printStackTrace();
        }
        return null;
    }
}
