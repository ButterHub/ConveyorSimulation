package main.uk.co.butterhub.conveyorsimulation;

public class Simulation {
  public static void main(String[] args) {
    // Check if command line arguments correct (Time Steps)
    if (args.length != 1) {
      System.out.println("Usage: java Simulation timeSteps");
    } else {
      int time = Integer.parseInt(args[0]);
      // Input possibilities and probabilities
      double[] inputProbabilities = {1.0 / 3, 1.0 / 3, 1.0 / 3};
      String[] inputPossibilities = {"A",   "B",   ""  };
      Conveyor firstConveyor = new Conveyor(1, 2, inputPossibilities, inputProbabilities);
      firstConveyor.operateSteps(time);
    }
  }
}
