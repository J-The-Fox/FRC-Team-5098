public class I5Enums {
  public static void main(String[] args) {
    // Enums aren't often as used as the other variables, but we still use them in robotics, so it's 
    // important to know what they are. They are basically a way to store a set of constants (variables that cannot be changed)

    // Here is an example of an enum
    enum Direction {
      NORTH,
      SOUTH,
      EAST,
      WEST
    }
    // We know that the direction of a robot can only be one of the four directions, so we can use an enum to store the directions
    Direction robotDirection = Direction.NORTH; // robotDirection is now NORTH
    robotDirection = Direction.SOUTH; // robotDirection is now SOUTH
    // this way, we can't accidentally set robotDirection to something that isn't a direction
  }
}
