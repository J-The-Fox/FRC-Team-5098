package I6ImportAndExportFile;
import java.util.Scanner; // this is how you import code from other files

public class I61TestImport {
  int people;

  public void testMethod() {
    Scanner scanner = new Scanner(System.in); // This creates a scanner object from the scanner class we imported
    // we can now use the scanner object to get input from the user
    System.out.println("How many people are in the programming team?");
    people = scanner.nextInt();
    System.out.println("There are " + people + " people in the programming team.");

    scanner.close(); // this closes the scanner object, not too important right now
  }
}

