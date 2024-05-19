
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
    private int gramMax;
    private String startingWord;
    private int textWords;

    /**
     * Constructor for objects of class TextGen
     */
    public TextGen(String fileName, String startingWord) {
        gramMax = 5;
        this.startingWord = startingWord;
        textWords = 100;

        loadFiles(fileName);
        //System.out.println(dataString);
        results = dataString.toString().split(" +");
        /*for(int i = 0; i< results.length; i++) {
            System.out.println("'" + results[i] + "'");
        }*/
    }
    
    /**
     * loads the given file and copies it to the dataString array list
     */
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
    
    /**
     * generate n-grams of lengths gramMax->2
     */
    public void makeGrams() {
        for(int i = 0; i < results.length - (gramMax + 1); i++) {
            for (int j = 1; j < gramMax + 1; j++) {
                String key = "";
                for( int k = 0; k < j; k++) {
                    key += results[i + k];
                    if(k < j - 1) {
                        key += " ";
                    }
                }
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

    /**
     * Generates and prints the next word the number of times defines by textWords
     */
    public void getNextWord() {
        String history;
        ArrayList<String> prevWords = new ArrayList<String>();
        prevWords.add(startingWord);
        ArrayList<String> nextWords;
        
        System.out.print(startingWord);
        for (int i = 0; i < textWords; i++) {
            int currentSize = gramMax;
                do {
                    history = "";
                    for (int j = gramMax - currentSize; j < 
                        prevWords.size(); j++) { // Generate history
                        history += prevWords.get(j);
                    }
                    nextWords = nGrams.get(history);
                    currentSize--;
                } while((nextWords == null || nextWords.size() == 1) && 
                        currentSize >= 1); // while size is valid for history
            int randomSpot = ((int)(Math.random() * ((double)nextWords.size())));
            // Chooses a random spot in the array from 0 - Size
            String nextWord = nextWords.get(randomSpot);
            
            if(prevWords.size() < gramMax) { // If prev words is not full
                prevWords.add(nextWord);
            } else { // makes sure  the array is always length gramMax and takes out old words
                prevWords.remove(0);
                prevWords.add(nextWord);
            }
            System.out.print(" " + nextWord);
            if (i % 12 == 0 && i != 0) { // Next line every 12 words
                System.out.println("\n");
            }
        }
    }
}
