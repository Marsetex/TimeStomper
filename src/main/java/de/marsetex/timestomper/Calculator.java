package de.marsetex.timestomper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.marsetex.timestomper.bo.WorkingHoursEntry;

public class Calculator {

	private int TOTAL_WORKING_HOURS;

	private double remainingWorkingHours;

	public Calculator(int hours) {
		TOTAL_WORKING_HOURS = hours;
	}

	public Date calculateEndOfWorkingDay(Calendar cal) {
		int workingHoursBeforeComma = (int) remainingWorkingHours;
		double workingHoursAfterComma = remainingWorkingHours - workingHoursBeforeComma;

		int sadma = (int) (60.0 / 100.0 * (workingHoursAfterComma * 100));

		int add = workingHoursBeforeComma * 60 + sadma;

		if (workingHoursBeforeComma > 10) {
			add = 10 * 60 + sadma;
			add = add + 45;
		} else if (workingHoursBeforeComma > 9) {
			add = add + 45;
		} else if (workingHoursBeforeComma > 6) {
			add = add + 30;
		}

		cal.add(Calendar.MINUTE, add);
		return cal.getTime();
	}

	public double calculateRemaingWorkingHours(List<WorkingHoursEntry> list) {
		double result = TOTAL_WORKING_HOURS;

		for (WorkingHoursEntry workingHoursTableEntry : list) {
			result = result - Double.parseDouble(workingHoursTableEntry.getWorkingHours().getValue());
		}
		remainingWorkingHours = result;
		return result;
	}

	public double getRemainingWorkingHours() {
		return TOTAL_WORKING_HOURS - remainingWorkingHours;
	}

	public double getWorkedWorkingHours() {
		return remainingWorkingHours;
	}

	public void setBaseWorkingHours(int hours) {
		TOTAL_WORKING_HOURS = hours;
	}
}
