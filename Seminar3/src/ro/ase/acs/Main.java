package ro.ase.acs;

import ro.ase.acs.interfaces.DbController;
import ro.ase.acs.orchestrators.Orchestrator;

public class Main {
  public static void main(String[] args) {
    DbController controller = null;
    try {
      controller = (DbController) Class.forName("ro.ase.acs.controllers.SQLController")
          .getDeclaredConstructor().newInstance();
      Orchestrator orchestrator = new Orchestrator(controller);
      orchestrator.execute();
    } catch (Exception e) {
      System.exit(-1);
    }
  }
}
