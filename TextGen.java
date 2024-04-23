
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import to handle file excepions
import java.util.Scanner;

/**
 * generates text with an N-gram model
 */
public class TextGen {
    StringBuilder dataString;
    String[] results; // for results from split method 
    MyHashTable<String, String> nGrams;
    private File data;

    /**
     * Constructor for objects of class TextGen
     */
    public TextGen(String fileName) {
        loadFiles(fileName);
        results = dataString.toString().split(" ");
    }
    
    public void loadFiles(String fileName) {
        try {
            data = new File(fileName);
            nGrams = new MyHashTable<String, String>();
            dataString = new StringBuilder();
            Scanner scanner = new Scanner(data);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                dataString.append(nextLine);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getNextWord(String prevWord) {
        
    }
}
