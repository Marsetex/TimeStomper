package de.marsetex.timestomper.gui.fx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.marsetex.timestomper.bo.WorkingHoursTableEntry;
import de.marsetex.timestomper.cache.EntityCache;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

	@FXML
	public void initialize() {
		pieChartData = FXCollections.observableArrayList(new Data("Arbeitszeit", 152), new Data("Restzeit", 45));
		charts.setData(pieChartData);
		charts.setLegendVisible(true);

		dayColumn.setCellValueFactory(cellData -> cellData.getValue().getDay());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDate());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().getWorkingHours());

		// ListView<String> listView = new ListView<>();
		// listView.getItems().addAll("One", "Two", "Three");
		//
		// listView.setCellFactory(lv -> {
		//
		// ListCell<String> cell = new ListCell<>();
		//
		// ContextMenu contextMenu = new ContextMenu();
		//
		//
		// MenuItem editItem = new MenuItem();
		// editItem.textProperty().bind(Bindings.format("Edit \"%s\"",
		// cell.itemProperty()));
		// editItem.setOnAction(event -> {
		// String item = cell.getItem();
		// // code to edit item...
		// });
		// MenuItem deleteItem = new MenuItem();
		// deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"",
		// cell.itemProperty()));
		// deleteItem.setOnAction(event ->
		// listView.getItems().remove(cell.getItem()));
		// contextMenu.getItems().addAll(editItem, deleteItem);
		//
		// cell.textProperty().bind(cell.itemProperty());
		//
		// cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
		// if (isNowEmpty) {
		// cell.setContextMenu(null);
		// } else {
		// cell.setContextMenu(contextMenu);
		// }
		// });
		// return cell ;
		// });
	}

	@FXML
	private void addPressed() {
		updatePieChart();
		cache.add(datePicker.getValue(), workingHoursTextField.getText());
		timeTable.setItems(cache.getIteams());
	}

	@FXML
	private void calculatePressed() {
		double rest = calculateRemaingWorkingHours();
		int restGanz = (int) rest;
		double sss = rest - restGanz;
		int sadma = (int) (60.0 / 100.0 * (sss * 100));

		remaingTimeTextField.setText(String.valueOf(restGanz) + ":" + String.valueOf(sadma));

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

		int add = restGanz * 60 + sadma;

		if (restGanz > 5) {
			add = add + 30;
		}

		cal.add(Calendar.MINUTE, add);
		String newTime = df.format(cal.getTime());
		System.out.println(newTime);
		endOfWorkDayTextField.setText(newTime);
	}

	private double calculateRemaingWorkingHours() {
		return 7.75;
	}

	private void updatePieChart() {
		for (Data data : pieChartData) {
			data.setPieValue(Math.random() * 100 + 1);
		}
	}

}
