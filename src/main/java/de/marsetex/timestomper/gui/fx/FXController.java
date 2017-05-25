package de.marsetex.timestomper.gui.fx;

import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import tornadofx.control.DateTimePicker;

/**
 * 
 * @author Marcel Gruessinger
 *
 */
public class FXController {

	private ObservableList<Data> pieChartData;

	@FXML
	private Button saveTimeButton;

	@FXML
	private ListView<String> d;

	@FXML
	private DateTimePicker s;

	@FXML
	private PieChart charts;

	private ObservableList<String> items;

	@FXML
	public void initialize() {
		pieChartData = FXCollections.observableArrayList(new Data("Arbeitszeit", 152), new Data("Restzeit", 45));
		charts.setData(pieChartData);
		charts.setLegendVisible(true);

		items = FXCollections.observableArrayList("Single", "Double", "Suite", "Family App");
		d.setItems(items);
		ContextMenu contextMenu = new ContextMenu();
		MenuItem add = new MenuItem("Add");
		MenuItem delete = new MenuItem("Delete");
		contextMenu.getItems().addAll(add, delete);
		d.setContextMenu(contextMenu);
	}

	@FXML
	private void buttonPressed() {
		LocalDateTime time = s.getDateTimeValue();
		items.add(time.toString());
		updatePieChart();
	}

	private void updatePieChart() {
		for (Data data : pieChartData) {
			data.setPieValue(Math.random() * 100 + 1);
		}
	}

}
