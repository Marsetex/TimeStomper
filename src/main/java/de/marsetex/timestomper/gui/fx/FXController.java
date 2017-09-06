package de.marsetex.timestomper.gui.fx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.marsetex.timestomper.Calculator;
import de.marsetex.timestomper.bo.WorkingHoursEntry;
import de.marsetex.timestomper.cache.EntityCache;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
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

	@FXML
	private TableView<WorkingHoursEntry> timeTable;

	@FXML
	private TableColumn<WorkingHoursEntry, String> dayColumn;

	@FXML
	private TableColumn<WorkingHoursEntry, String> dateColumn;

	@FXML
	private TableColumn<WorkingHoursEntry, String> timeColumn;

	@FXML
	private TextField arrivalTimestampTextField;

	@FXML
	private TextField remaingTimeTextField;

	@FXML
	private TextField endOfWorkDayTextField;

	@FXML
	private PieChart charts;

	@FXML
	private MenuItem menuItemExit;

	@FXML
	private MenuItem menuItemAbout;

	private ObservableList<Data> pieChartData;

	private final EntityCache cache = new EntityCache();
	private final Calculator calc = new Calculator();

	@FXML
	public void initialize() {
		pieChartData = FXCollections.observableArrayList(new Data("Arbeitszeit", 0), new Data("Restzeit", 40));
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

		remaingTimeTextField.setText("" + calc.calculateRemaingWorkingHours(cache.getIteams()));

		menuItemExit.setOnAction(event -> Platform.exit());
		menuItemAbout.setOnAction(event -> showAboutDialog());
	}

	private void showAboutDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("TimeStomper - About");
		alert.setHeaderText("TimeStomper");
		alert.setContentText("Developed by: Marcel Gruessinger \nVersion: 1.0.0");
		alert.showAndWait();
	}

	public void handleEdit(CellEditEvent<WorkingHoursEntry, String> event) {
		WorkingHoursEntry ss = event.getRowValue();
		ss.setWorkingHours(new SimpleStringProperty(event.getNewValue()));

		remaingTimeTextField.setText("" + calc.calculateRemaingWorkingHours(event.getTableView().getItems()));
		updatePieChart();
	}

	@FXML
	private void calculatePressed() {
		calc.getRemainingWorkingHours();
		String myTime = arrivalTimestampTextField.getText();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date d = null;
		try {
			d = df.parse(myTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String newTime = df.format(calc.calculateEndOfWorkingDay(cal));
		System.out.println(newTime);
		endOfWorkDayTextField.setText(newTime);
	}

	private void updatePieChart() {
		for (Data data : pieChartData) {
			if ("Arbeitszeit".equals(data.getName())) {
				data.setPieValue(40 - calc.getRemainingWorkingHours());
			} else {
				data.setPieValue(calc.getRemainingWorkingHours());
			}
		}
	}

}
