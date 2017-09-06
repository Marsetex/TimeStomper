package de.marsetex.timestomper.bo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorkingHoursEntry {

	private StringProperty day;
	private StringProperty date;
	private StringProperty workingHours;

	public WorkingHoursEntry(String day, String date, String workingHours) {
		this.day = new SimpleStringProperty(day);
		this.date = new SimpleStringProperty(date);
		this.workingHours = new SimpleStringProperty(workingHours);
	}

	public StringProperty getDay() {
		return day;
	}

	public void setDay(StringProperty day) {
		this.day = day;
	}

	public StringProperty getDate() {
		return date;
	}

	public void setDate(StringProperty date) {
		this.date = date;
	}

	public StringProperty getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(StringProperty workingHours) {
		this.workingHours = workingHours;
	}

}
