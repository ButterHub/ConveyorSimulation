package main.uk.co.butterhub.conveyorsimulation;

import java.io.IOError;
import java.io.IOException;
import java.util.*;

public class Conveyor {
  private int length;
  private Worker[] workers;
  private String[] inputPossibilities;
  private double[] inputProbabilities;
  // TODO Improve Data structure for conveyor contents
  private LinkedList<String> conveyor = new LinkedList<String>();
  private int t = 0;
  private int conveyorCount = 0;
  double probabilitySum = 0;

  private Map<String, Integer> outputLog = new HashMap<String, Integer>();

  public Conveyor(int length, int accessPoints, String[] inputPossibilities, double[] inputProbabilities) {
    if (length <= 0) throw new Error("Conveyor length must be larger than 0.");
    this.length = length;
    this.inputPossibilities = inputPossibilities;
    this.inputProbabilities = inputProbabilities;
    workers = new Worker[accessPoints]; // declaring array
    for (int i = 0; i < accessPoints; i++) {
      workers[i] = new Worker(); // instantiating array
    }

    for (int i = 0; i < inputProbabilities.length; i++) {
      probabilitySum += inputProbabilities[i];
      outputLog.put(inputPossibilities[i], 0);
    }
    if (probabilitySum > 1.01 || probabilitySum < 0.99) {
      throw new Error("Probabilities do not add up to 1.");
    }

    // Create workers (# = length * accessPoints)
  }

  public String pickupComponent(int position) {
    String content;
    // Error check: if element empty, nothing to pick up.
    if (conveyor.get(position) == "0") {
      throw new IllegalAccessError("Slot is empty, cannot pick up component.");
    } else {
      // Success: Pick up component, leaving nothing in the slot
      content = conveyor.set(position, "0");
    }
    return content;
  }

  public void dropoffProduct(int position) {
    // if not empty, fail
    if (conveyor.get(position) != "0") {
      throw new IllegalAccessError("Slot is not empty, no space to drop product.");
    } else {
      conveyor.set(position, "P");
    }
  }

  // Move TIME steps in time, which calls generateNextItem and completeEndItem
  public void operateSteps(int time) {
    if (time <= 0) {
      throw new Error("Time step must be a positive integer.");
    }
    else {
      // Should i operate one step or accept any input
      for (int i = 0; i < time; i++) {
        completeEndItem();
        generateNextItem();
        // One step for each position (shared between all workers)
        Worker.work();
        t++;
      }
    }
  }

  private void generateNextItem() {
    conveyorCount++;
    String result = "";
    // check if inputProbabilities roughly adds to 1
    double rand = Math.random();
    double F = 0;
    // Pick one of 3 options according to inputProbabilities
    for (int i = 0; i < inputProbabilities.length; i++) {
      F += inputProbabilities[i];
      if (rand < F) {
        result = inputPossibilities[i];
        break;
      } else {
        result = inputPossibilities[inputProbabilities.length - 1];
      }
    }

    // Add to conveyor
    conveyor.addFirst(result);
  }

  private void completeEndItem() {
    // Only run if items are falling off the end
    String lastItem = null;
    if (conveyorCount == length) {
      lastItem = conveyor.removeLast();
      conveyorCount--;
      incrementLogOf(lastItem);
    }
  }

  private void incrementLogOf(String name) {
    outputLog.put(name, outputLog.get(name) + 1);
  }

  public Map<String, Integer> getOutputLog() {
    return outputLog;
  }

  public LinkedList<String> getConveyor() {
    return conveyor;
  }

  public int getT() {
    return t;
  }

}
