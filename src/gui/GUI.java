package gui;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import service.Service;

public class GUI extends Application {
	Service service;

	public static void main(String[] args) {

		Application.launch(args);

	}

	@Override
	public void start(Stage stage) {
		service = new Service();
		stage.setTitle("MJ Lagerstyring");
		GridPane pane = new GridPane();
		initContent(pane);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}

	Label nameLabel;
	Label priceLabel;
	Label desc;
	RadioButton rb1;
	RadioButton rb2;
	RadioButton rb3;

	// Manuel indtastning

	Label iBarcodeLabel;
	Label iDecLabel;
	Label iAmountLabel;
	Label iPriceLabel;
	Label iNameLabel;

	TextField iBarcodeTxf;
	TextArea iDecTxf;
	TextField iAmountTxf;
	TextField iPriceTxf;
	TextField iNameTxf;

	Button iAddBtn;
	Button iClearBtn;
	Button iDeleteBtn;
	Button iSearchBtn;
	final ToggleGroup group = new ToggleGroup();

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);

		// ---------------------------------------------------------------------

		GridPane gPane = new GridPane();
		pane.add(gPane, 0, 0);
		GridPane ePane = new GridPane();
		pane.add(ePane, 0, 1);
		GridPane dPane = new GridPane();
		pane.add(dPane, 0, 2);
		GridPane fPane = new GridPane();
		pane.add(fPane, 0, 3);

		gPane.setPadding(new Insets(10));
		gPane.setHgap(10);
		gPane.setVgap(10);
		gPane.setStyle("-fx-border-color: black");

		nameLabel = new Label("PRODUCT NAME");
		desc = new Label("BESKRIVELSE");
		priceLabel = new Label("PRICE");
		nameLabel.setFont(Font.font("Verdana", 50));
		gPane.add(nameLabel, 0, 0);
		gPane.add(priceLabel, 1, 0);
		gPane.add(desc, 0, 1);

		rb1 = new RadioButton("Indkøb");
		rb2 = new RadioButton("Salg");
		rb3 = new RadioButton("Manuel");

		ePane.setPadding(new Insets(10));
		ePane.setHgap(10);
		ePane.setVgap(3);

		ePane.add(rb1, 0, 1);
		ePane.add(rb2, 0, 2);
		ePane.add(rb3, 0, 3);
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		rb2.setToggleGroup(group);
		rb3.setToggleGroup(group);

		rb1.setOnMouseClicked(event -> rBtnCheck());
		rb2.setOnMouseClicked(event -> rBtnCheck());
		rb3.setOnAction(event -> rBtnCheck());

		iBarcodeLabel = new Label("Barcode:");
		ePane.add(iBarcodeLabel, 0, 4);
		iBarcodeTxf = new TextField();
		ePane.add(iBarcodeTxf, 0, 5);
		iBarcodeTxf.setDisable(true);

		iSearchBtn = new Button("Søg");
		ePane.add(iSearchBtn, 1, 5);
		iSearchBtn.setOnAction(event -> iSearch());

		iNameLabel = new Label("Navn:");
		ePane.add(iNameLabel, 0, 6);
		iNameTxf = new TextField();
		ePane.add(iNameTxf, 0, 7);
		iNameTxf.setDisable(true);

		iPriceLabel = new Label("Pris:");
		ePane.add(iPriceLabel, 0, 8);
		iPriceTxf = new TextField();
		ePane.add(iPriceTxf, 0, 9);
		iPriceTxf.setDisable(true);

		iAmountLabel = new Label("Mængnde:");
		ePane.add(iAmountLabel, 1, 8);
		iAmountTxf = new TextField();
		ePane.add(iAmountTxf, 1, 9);
		iAmountTxf.setDisable(true);

		dPane.setPadding(new Insets(10));
		dPane.setHgap(10);
		dPane.setVgap(10);

		iDecLabel = new Label("Beskrivelse:");
		dPane.add(iDecLabel, 0, 0);
		iDecTxf = new TextArea();
		dPane.add(iDecTxf, 0, 1);
		iDecTxf.setDisable(true);

		fPane.setPadding(new Insets(10));
		fPane.setHgap(10);
		fPane.setVgap(10);

		iAddBtn = new Button("Tilføj");
		fPane.add(iAddBtn, 0, 0);
		iAddBtn.setOnAction(event -> CRU());

		iClearBtn = new Button("Ryd");
		fPane.add(iClearBtn, 1, 0);
		iClearBtn.setOnAction(event -> iClear());

		iDeleteBtn = new Button("Slet");
		fPane.add(iDeleteBtn, 2, 0);
		iDeleteBtn.setOnAction(event -> deleteProduct());

	}

	public void alert1() {
		try {
			nameLabel.setText(service.getName(123));
			desc.setText(service.getDec(123));
			priceLabel.setText(Double.toString((service.getPrice(123))));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rBtnCheck() {
		if (rb3.isSelected()) {
			iBarcodeTxf.setDisable(false);
			iNameTxf.setDisable(false);
			iDecTxf.setDisable(false);
			iAmountTxf.setDisable(false);
			iPriceTxf.setDisable(false);
		} else {
			iBarcodeTxf.setDisable(true);
			iNameTxf.setDisable(true);
			iDecTxf.setDisable(true);
			iAmountTxf.setDisable(true);
			iPriceTxf.setDisable(true);
		}
	}

	public void CRU() {
		try {
			if (service.exists(Integer.parseInt(iBarcodeTxf.getText()))) {

			} else {
				service.createProduct(Integer.parseInt(iBarcodeTxf.getText()),
						iDecTxf.getText(),
						Integer.parseInt(iAmountTxf.getText()),
						Double.parseDouble(iPriceTxf.getText()),
						iNameTxf.getText());
			}
		} catch (NumberFormatException e) {
			// INDSÆT FEJLBEDSKED - BARCODE ER KUN NUMERISK
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteProduct() {
		// Alert her
		try {
			service.deleteProduct(Integer.parseInt(iBarcodeTxf.getText()));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void iSearch() {
		int barcode = Integer.parseInt(iBarcodeTxf.getText());
		try {
			if (service.exists(barcode)) {
				iNameTxf.setText(service.getName(barcode));
				iDecTxf.setText(service.getDec(barcode));
				iPriceTxf.setText(Double.toString(service.getPrice(barcode)));
				iAmountTxf
						.setText(Integer.toString(service.getAmount(barcode)));
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void iClear() {
		iNameTxf.setText("");
		iDecTxf.setText("");
		iPriceTxf.setText("");
		iAmountTxf.setText("");
	}
}
