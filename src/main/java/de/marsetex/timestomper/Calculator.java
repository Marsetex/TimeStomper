package de.marsetex.timestomper;

import de.marsetex.timestomper.bo.WorkingHoursTableEntry;
import javafx.collections.ObservableList;

public class Calculator {

	private static final int TOTAL_WORKING_HOURS = 40;

	private final double remainingWorkingHours = 7.75;
	private double workingHoursAfterComma;

	private int workingHoursBeforeComma;

	public void calc() {
		workingHoursBeforeComma = (int) remainingWorkingHours;
		workingHoursAfterComma = remainingWorkingHours - workingHoursBeforeComma;

		int sadma = (int) (60.0 / 100.0 * (workingHoursAfterComma * 100));
		System.out.println(sadma);

	}

	public double calc2(ObservableList<WorkingHoursTableEntry> list) {
		double result = TOTAL_WORKING_HOURS;

		for (WorkingHoursTableEntry workingHoursTableEntry : list) {
			result = result - Double.parseDouble(workingHoursTableEntry.getWorkingHours().getValue());
		}

		return result;
	}

	private double calculateRemaingWorkingHours() {
		return remainingWorkingHours;
		// String myTime = arrivalTimestampTextField.getText();
		// SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		// Date d = null;
		// try {
		// d = df.parse(myTime);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(d);
		//
		// int add = restGanz * 60 + sadma;
		//
		// if (restGanz > 5) {
		// add = add + 30;
		// }
		//
		// cal.add(Calendar.MINUTE, add);
		// String newTime = df.format(cal.getTime());
		// System.out.println(newTime);
		// endOfWorkDayTextField.setText(newTime);
	}
}
