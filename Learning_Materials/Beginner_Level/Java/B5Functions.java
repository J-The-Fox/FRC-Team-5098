public class B5Functions {

  // If you have a section of code that you need to use multiple times, you can
  // put it in a function and call that function whenever you need to use that
  // code.
  // to create the function, you need to use the following syntax:

  // public static void function_name() {
  // code goes here
  // }

  // the public/static part determines whether the function needs to be used in a
  // object or not, we will go over this in more detail later

  // void determines whether the function returns a value or not, we will also go
  // over this later in the file

  static void myFunction1() {
    System.out.println("Hello World!");
  }

  static int myFunction2() {
    return (50);
  }

  static int myFunction3(int x, int y) {
    return x + y;
  }

  public static void main(String[] args) {

    // to have the code in the function run, you must "call" it
    myFunction1(); // this will print out "Hello World!"
    // you can also pass variables into functions

    // you can also have functions return a value
    int value = myFunction2(); // this will return "Hello person I know!"

    // you can have as many variables as you want, as long as you seperate them with
    // a comma
    System.out.println(myFunction3(5, value)); // this will return 55

    // built in functions
    // there are many built in functions in java which don't require you to create
    // them, an example is the "Math.max()" function
    Math.max(5, 10); // returns the highest value of 5 and 10

  }
}
