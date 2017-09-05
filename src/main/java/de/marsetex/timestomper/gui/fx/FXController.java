package de.marsetex.timestomper.gui.fx;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import de.marsetex.timestomper.Calculator;
import de.marsetex.timestomper.bo.WorkingHoursTableEntry;
import de.marsetex.timestomper.cache.EntityCache;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * 
 * @author Marcel Gruessinger
 *
 */
public class FXController {

	private ObservableList<Data> pieChartData;

	@FXML
	private TableView<WorkingHoursTableEntry> timeTable;

	@FXML
	private TableColumn<WorkingHoursTableEntry, String> dayColumn;

	@FXML
	private TableColumn<WorkingHoursTableEntry, String> dateColumn;

	@FXML
	private TableColumn<WorkingHoursTableEntry, String> timeColumn;

	@FXML
	private DatePicker datePicker;

	@FXML
	private TextField workingHoursTextField;

	// @FXML
	// private Button saveTimeButton;

	@FXML
	private TextField arrivalTimestampTextField;

	// @FXML
	// private Button calculateButton;

	@FXML
	private TextField remaingTimeTextField;

	@FXML
	private TextField endOfWorkDayTextField;

	@FXML
	private PieChart charts;

	private final EntityCache cache = new EntityCache();

	Calculator calc = new Calculator();

	@FXML
	public void initialize() {
		pieChartData = FXCollections.observableArrayList(new Data("Arbeitszeit", 152), new Data("Restzeit", 45));
		charts.setData(pieChartData);
		charts.setLegendVisible(true);

		dayColumn.setCellValueFactory(cellData -> cellData.getValue().getDay());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDate());

		timeColumn.setCellValueFactory(cellData -> cellData.getValue().getWorkingHours());
		timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		timeColumn.setOnEditCommit(event -> handleEdit(event));

		LocalDate now = LocalDate.now();
		TemporalField fieldISO = WeekFields.of(Locale.GERMANY).dayOfWeek();

		for (int i = 1; i <= 5; i++) {
			cache.add(now.with(fieldISO, i), "0.00");
		}
		timeTable.setItems(cache.getIteams());

		remaingTimeTextField.setText("" + calc.calc2(cache.getIteams()));
	}

	public void handleEdit(CellEditEvent<WorkingHoursTableEntry, String> event) {
		WorkingHoursTableEntry ss = event.getRowValue();
		ss.setWorkingHours(new SimpleStringProperty(event.getNewValue()));

		remaingTimeTextField.setText("" + calc.calc2(event.getTableView().getItems()));

	}

	@FXML
	private void addPressed() {
		updatePieChart();
		cache.add(datePicker.getValue(), workingHoursTextField.getText());
		timeTable.setItems(cache.getIteams());
	}

	@FXML
	private void calculatePressed() {
		Calculator calc = new Calculator();
		calc.calc();
		// remaingTimeTextField.setText(String.valueOf(restGanz) + ":" +
		// String.valueOf(sadma));

	}

	private void updatePieChart() {
		for (Data data : pieChartData) {
			data.setPieValue(Math.random() * 100 + 1);
		}
	}

}
