package de.marsetex.timestomper.cache;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import de.marsetex.timestomper.bo.WorkingHoursTableEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EntityCache {

	private final ObservableList<WorkingHoursTableEntry> items;

	public EntityCache() {
		items = FXCollections.observableArrayList();
	}

	public void add(LocalDate date, String workingHours) {
		items.add(new WorkingHoursTableEntry(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMANY),
				date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), workingHours));
	}

	public ObservableList<WorkingHoursTableEntry> getIteams() {
		return items;
	}
}
