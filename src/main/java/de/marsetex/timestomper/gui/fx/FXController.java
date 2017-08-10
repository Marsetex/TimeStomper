package de.marsetex.timestomper.gui.fx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

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
	private PieChart charts;

	@FXML
	private TextField restarbeitszeit;

	@FXML
	private TextField ankunft;

	@FXML
	private TextField verbeleibend;

	@FXML
	private TextField feierabend;

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
	private void buttonPressed() {
		updatePieChart();
		System.out.println("hi");
		double rest = Double.parseDouble(restarbeitszeit.getText());
		int restGanz = (int) rest;
		double sss = rest - restGanz;
		int sadma = (int) (60.0 / 100.0 * (sss * 100));

		verbeleibend.setText(String.valueOf(restGanz) + ":" + String.valueOf(sadma));

		String myTime = ankunft.getText();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date d = null;
		try {
			d = df.parse(myTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
		feierabend.setText(newTime);
	}

	private void updatePieChart() {
		for (Data data : pieChartData) {
			data.setPieValue(Math.random() * 100 + 1);
		}
	}

}
