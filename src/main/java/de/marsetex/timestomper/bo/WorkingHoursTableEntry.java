package de.marsetex.timestomper.bo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorkingHoursTableEntry {

	private StringProperty day;
	private StringProperty date;
	private StringProperty workingHours;

	public WorkingHoursTableEntry(String string, String string2, String string3) {
		day = new SimpleStringProperty(string);
		date = new SimpleStringProperty(string2);
		workingHours = new SimpleStringProperty(string3);
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
