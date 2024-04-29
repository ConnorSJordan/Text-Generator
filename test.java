
/**
 * Write a description of class test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class test
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class test
     */
    public test()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void test() {
        String text = "be ";
        String previousWord = "be ";
        TextGen test = new TextGen("Biden_Speeches.txt");
        test.makeGrams();
        test.nGramsPrint();
        previousWord = test.getNextWord("be ");
        System.out.println(previousWord);
        System.out.println(test.getNextWord(previousWord));
        /*for (int i = 0; i < 100; i++) {
            previousWord = test.getNextWord(previousWord + " ");
            text += previousWord;
        }*/
        //System.out.println(text);
    }
}
