package test.uk.co.butterhub.conveyorsimulation;

import main.uk.co.butterhub.conveyorsimulation.Conveyor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConveyorTest {
  Conveyor conveyor;

  @BeforeEach
  void setUp() {
    {
      // Input possibilities and probabilities
      double[] inputProbabilities = {1.0 / 3, 1.0 / 3, 1.0 / 3};
      String[] inputPossibilities = {"A", "B", "0"};
      conveyor = new Conveyor(1, 2, inputPossibilities, inputProbabilities);
    }
  }

  @Test
  void pickupComponent() {

  }

  @Test
  void dropoffProduct() {

  }

  @Test
  void operateSteps() {
    conveyor.operateSteps(1);
    assertEquals(1, conveyor.getT());

    conveyor.operateSteps(5);
    assertEquals(6, conveyor.getT());
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
}