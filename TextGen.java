
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import to handle file excepions
import java.util.Scanner;

/**
 * generates text with an N-gram model
 */
public class TextGen {
    StringBuilder();
    String[] results; // for results from split method 
    //hash table grams;
    private File data;

    /**
     * Constructor for objects of class TextGen
     */
    public TextGen(String fileName) {
        try {
            data = new File(fileName);
            Scanner scanner = new Scanner(data);
            while (scanner.hasNextLine()) {
                text += scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
    
    public String[] split(File toBeSplit) {
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getNextWord(String prevWord) {
        
    }
}
