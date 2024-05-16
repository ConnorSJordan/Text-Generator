
/**
 * Write a description of class brov here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tested extends Tester{}
class Tester {
    public static void main() {
        Tester t1 = new Tester();
        Tested t2 = new Tested();
        System.out.println(t1 instanceof Tester);
        System.out.println(t1 instanceof Tested);
        System.out.println(t2 instanceof Tester);
        System.out.println(t2 instanceof Tested);
            
    }
}