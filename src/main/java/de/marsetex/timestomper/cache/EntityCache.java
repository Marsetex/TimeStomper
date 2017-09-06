package de.marsetex.timestomper.cache;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import de.marsetex.timestomper.bo.WorkingHoursEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EntityCache {

	private final ObservableList<WorkingHoursEntry> items;

	public EntityCache() {
		items = FXCollections.observableArrayList();
	}

	public void add(LocalDate date, String workingHours) {
		items.add(new WorkingHoursEntry(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMANY),
				date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), workingHours));
	}

	public ObservableList<WorkingHoursEntry> getIteams() {
		return items;
	}
}
