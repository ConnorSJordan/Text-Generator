
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

    public void getNextWord() {
        ArrayList<String> history = new ArrayList<String>();
        String resultWord = startingWord;
        for (int i = 0; i < textWords; i++) {
            history.add(resultWord);
            for(int j = gramMax; j >= 0; j--) {
                if (i - j >= 0) {    
                    String past5 = "";
                    for(int k = i - j; k <= i; k++) {
                        past5 += history.get(k);
                        if(k < i) {
                            past5 += " ";
                        }
                    }
                    if(nGrams.get(past5) != null) {
                        ArrayList<String> temp = nGrams.get(past5);
                        resultWord = temp.get((int)(Math.random() * temp.size()));
                    }
                }
            }
        }
        for(int i = 0; i < history.size(); i++) {
            System.out.print(" " + history.get(i));
            if(i % 10 == 0 && i != 0) {
                System.out.println("");
            }
        }
    }
}
