package test.uk.co.butterhub.conveyorsimulation;

import main.uk.co.butterhub.conveyorsimulation.Conveyor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ConveyorTest {
  Conveyor conveyor;

  @BeforeEach
  void setUp() {
    {
      // Input possibilities and probabilities
      double[] inputProbabilities = {1.0 / 3, 1.0 / 3, 1.0 / 3};
      String[] inputPossibilities = {"A", "B", ""};
      conveyor = new Conveyor(1, 2, inputPossibilities, inputProbabilities);
    }
  }

  @Test
  void operateSteps() {
    conveyor.operateSteps(1);
    assertEquals(1, conveyor.getT());

    conveyor.operateSteps(100);
    assertEquals(101, conveyor.getT());
    System.out.println("Output log: "+conveyor.getOutputLog());
  }

  @Test
  void operateStepsError() {
    Assertions.assertThrows(Error.class, ()-> {
      conveyor.operateSteps(-5);
    });

  }

  @Test
  void getOutputLog() {

  }

  @Test
  void getConveyor() {
  }

  @Test
  void getT() {
    conveyor.operateSteps(100);
    assertEquals(100, conveyor.getT());
  }

  @Test
  void experiment1() {
    String[][] workersInventory;
    int length = 5;
    int workersPerPosition = 3;
    workersInventory = new String[length][workersPerPosition]; // declaring array
    for (int j = 0; j < length; j++) {
      for (int i = 0; i < workersPerPosition; i++) {
        workersInventory[j][i] = ""; // instantiating array, first index is position on conveyor
      }
    }
//    System.out.println(Arrays.deepToString(workersInventory));
//    System.out.println(workersInventory.length);
//    System.out.println(workersInventory[0].length);
  }
}