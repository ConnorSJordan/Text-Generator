
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import to handle file excepions
import java.util.Scanner;
import java.util.ArrayList;

/**
 * generates text with an N-gram model
 */
public class TextGen {
    private StringBuilder dataString;
    private String[] results; // for results from split method 
    private MyHashTable<String, ArrayList<String>> nGrams;
    private File data;

    /**
     * Constructor for objects of class TextGen
     */
    public TextGen(String fileName) {
        loadFiles(fileName);
        System.out.println(dataString);
        results = dataString.toString().split(" +");
        /*for(int i = 0; i< results.length; i++) {
            System.out.println("'" + results[i] + "'");
        }*/
    }
    
    public void loadFiles(String fileName) {
        try {
            data = new File(fileName);
            nGrams = new MyHashTable<String, ArrayList<String>>();
            dataString = new StringBuilder();
            Scanner scanner = new Scanner(data);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                dataString.append(nextLine + " ");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void makeGrams() {
        for(int i = 0; i < results.length - 6; i++) {
            for (int j = 1; j < 6; j++) {
                String key = "";
                for( int k = 0; k < j; k++) {
                    key += results[i + k] + " ";
                }
                //System.out.println("Key: " + key + "-- Value: " + results[i+j]);
                if(nGrams.get(key) == null) {
                    ArrayList<String> wordList = new ArrayList<String>();
                    wordList.add(results[i+j]);
                    nGrams.put(key, wordList);
                } else {
                    nGrams.get(key).add(results[i+j]);
                }
            }
        }
    }
    
    public String nGramsPrint() {
        return nGrams.toString();
    }

    public String getNextWord(String prevWord) {
        ArrayList<String> temp = nGrams.get(prevWord);
        return temp.get((int)(Math.random() * temp.size()));
    }
}
