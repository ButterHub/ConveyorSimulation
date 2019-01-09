package main.uk.co.butterhub.conveyorsimulation;

import java.util.LinkedList;

// TODO Complete the worker class
public class Workers {
  private String[][] workersInventory;

  public Workers(int length, int workersPerPosition) {
    workersInventory = new String[length][workersPerPosition]; // declaring array
    for (int j = 0; j < length; j++) {
      for (int i = 0; i < workersPerPosition; i++) {
        workersInventory[j][i] = ""; // instantiating array, first index is position on conveyor
      }
    }
  }

  public LinkedList<String> operateStep(LinkedList<String> conveyor) {
    // Loop over all conveyor belt position
    for (int pos = 0; pos < workersInventory.length; pos++) {
      // Loop over workers in each position. One worker works.
      for (int worker = 0; worker < workersInventory[pos].length; worker++) {
        String conveyorItem = conveyor.get(pos);
        String workerInv = workersInventory[pos][worker];
        // Take anything (except empty) if doesn't have it. Skip to next POSITION
        if (!workerInv.contains(conveyorItem) && conveyorItem != "") {
          conveyor = pickupComponent(pos, conveyor);
          workersInventory[pos][worker] += conveyorItem;
          break;// break current for loop, to go to next position

        } else if (workerInv.contains("A") &&
                workerInv.contains("B") &&
                conveyorItem == "") {
          // Put "P" if have both.
          conveyor = dropOffProduct(pos, conveyor);
          workersInventory[pos][worker] = "";
          break;
        }
        // IF above 2 steps not done, then move to other worker on same position
      }
    }
    return conveyor;
  }

  public LinkedList<String> pickupComponent(int position, LinkedList<String> conveyor) {
    // Error check: if element empty, nothing to pick up.
    if (conveyor.get(position) == "") {
      throw new IllegalAccessError("Slot is empty, cannot pick up component.");
    } else {
      // Empty position on conveyor
      conveyor.set(position, "");
    }
    return conveyor;
  }

  public LinkedList<String> dropOffProduct(int position, LinkedList<String> conveyor) {
    // if not empty, fail
    if (conveyor.get(position) != "") {
      throw new IllegalAccessError("Slot is not empty, no space to drop product.");
    } else {
      conveyor.set(position, "P");
    }
    return conveyor;
  }

}


