package de.marsetex.timestomper;

import de.marsetex.timestomper.gui.fx.FXStageInitializer;
import javafx.application.Platform;

/**
 * 
 * @author Marcel Gruessinger
 *
 */
public class AppStarter {

	public static void main(String[] args) {

		// Employee emp = new Employee();
		// emp.setName("Pankaj");
		// emp.setRole("CEO");
		// emp.setInsertTime(new Date());
		//
		// // creating configuration object
		// Configuration cfg = new Configuration();
		// cfg.configure("hibernate.cfg.xml");// populates the data of the
		// // configuration file
		//
		// // creating seession factory object
		// SessionFactory factory = cfg.buildSessionFactory();
		// Session session = factory.openSession();
		// // start transaction
		// Transaction sss = session.beginTransaction();
		//
		// // Save the Model object
		// // session.save(emp);
		// // Commit transaction
		// sss.commit();
		//
		// Employee s = session.find(Employee.class, 1);
		// // System.out.println(s.getName());
		// session.close();

		System.out.println("successfully saved");

		new FXStageInitializer().initUserInterface(args);
		Platform.exit();
		System.exit(0);
	}

}
