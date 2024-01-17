package I6ImportAndExportFile;

// Important note, can only run this file from the TestPackage folder, as otherwise the build paths won't work (hard to explain, if curious ask later)

import java.util.Scanner; // this is how you import code from other files

import I6ImportAndExportFile.I61TestImport;

public class I6ImportingExporting {

  public static void main(String[] args){

    // Importing is a way you can access code created by others, or in different files
    // code created by others is often called a library, 

    // The most common library we use is the java library, which is a library that comes with java
    // an example of a library that we use is the scanner library
    // the scanner library is a file that allows you to get input from the user
    // we can use it like this:
    Scanner scanner = new Scanner(System.in); // This creates a scanner object from the scanner class we imported
    // we can now use the scanner object to get input from the user
    System.out.println("Enter your name: ");
    String name = scanner.nextLine(); // this gets the next line of input from the user and stores it in the name variable
    System.out.println("Hello " + name); // this prints out the name variable

    // We can also create our own libraries
    // To do this we need to create a new file, and put our code in it
    I61TestImport testImport = new I61TestImport(); // this creates a new object from the I61TestImport class
    testImport.testMethod(); // this calls the testMethod method from the I61TestImport class



  }
  
}
