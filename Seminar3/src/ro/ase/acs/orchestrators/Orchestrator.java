package ro.ase.acs.orchestrators;

import ro.ase.acs.interfaces.DbController;

public class Orchestrator {
  private final DbController controller;

  public Orchestrator(DbController controller) {
    this.controller = controller;
  }

  public void execute() {
    controller.open();
    controller.createTable();
    controller.insertData(1, "Andrei", "Strada Frumoasa 10", 5700.0d);
    controller.insertData(2, "Maria", "Strada Piata Amzei 12", 9100.0d);
    controller.printData();
    controller.close();
  }

}
