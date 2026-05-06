package com.csc.project.ui;

import com.csc.project.data.Store;
import com.csc.project.models.Item;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SceneBuilder{

    public static Scene getLoginScene(Store store, Stage stage) {
        // This line now allows the window to be adjustable.
        final double WIDTH = 400;
        final double HEIGHT = 200;

        Label loginHeader = new Label("Haskin Bobbins POS");
        loginHeader.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 24));
        HBox titleBox = new HBox(loginHeader);
        titleBox.setAlignment(Pos.CENTER);


        Label usernameLabel = new Label("Username:");
        TextField usernameTextField = new TextField("");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label errorLabel = new Label();
        errorLabel.setFont(Font.font(10));
        errorLabel.setStyle("-fx-text-fill: red;");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(_ -> {
            boolean isValidLogin = store.login(usernameTextField.getText(), passwordField.getText());
            if (isValidLogin) {
                stage.setScene(getPosScene(store, stage));
            } else {
                errorLabel.setText("Invalid username/password");
            }
        });

        HBox buttonBox = new HBox(10, errorLabel, loginButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        GridPane loginGrid = new GridPane(10, 10);
        loginGrid.setAlignment(Pos.CENTER);
        loginGrid.setPadding(new Insets(10, 0, 10, 0));

        loginGrid.add(usernameLabel, 0, 0);
        loginGrid.add(usernameTextField, 1, 0);

        loginGrid.add(passwordLabel, 0, 1);
        loginGrid.add(passwordField, 1, 1);

        loginGrid.add(buttonBox, 0, 2, 2, 1);
        loginGrid.setStyle("-fx-background-color: #fff;" +
                "-fx-border-color: #ddd;" +
                "-fx-border-radius: 12;" +
                " -fx-background-radius: 12;"
        );
        // This line allows the window to expand horizontally
        loginGrid.setMaxWidth(Double.MAX_VALUE);

        VBox mainBox = new VBox(10, loginHeader, loginGrid);

        mainBox.setAlignment(Pos.CENTER);
        mainBox.setStyle("-fx-background-color: #92c4fd;");
        VBox.setMargin(loginGrid, new Insets(0, 20, 0, 20));

        // New! This set the maximum size of the window when adjusting the software.
        stage.setMaxWidth(WIDTH * 2);
        stage.setMaxHeight(HEIGHT * 2);
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);

        return new Scene(mainBox, WIDTH, HEIGHT);
    }

    public static Scene getPosScene(Store store, Stage stage) {
        final double WIDTH = 300;
        final double HEIGHT = 420;

        Item[] items = store.getItems();

        GridPane resultsBox = new GridPane(2, 8);
        resultsBox.setPadding(new Insets(0, 10, 0, 0));
        resultsBox.setAlignment(Pos.BASELINE_RIGHT);
        resultsBox.setHgap(10);
        resultsBox.setVgap(5);
        resultsBox.setMaxWidth(200.0);
        resultsBox.setMinWidth(200.0);

        Font font = Font.font("Arial", FontWeight.BOLD, 12);

        Label subTotalLabel = new Label("SubTotal:");
        subTotalLabel.setFont(font);
        Label subTotalValue = new Label("$0.00");

        Label taxesLabel = new Label("Taxes:");
        taxesLabel.setFont(font);
        Label taxesValue = new Label("$0.00");

        Label totalLabel = new Label("Total:");
        totalLabel.setFont(font);
        Label totalValue = new Label("$0.00");

        resultsBox.add(subTotalLabel, 0, 0);
        resultsBox.add(subTotalValue, 1, 0);
        resultsBox.add(taxesLabel, 0, 1);
        resultsBox.add(taxesValue, 1, 1);
        resultsBox.add(totalLabel, 0, 2);
        resultsBox.add(totalValue, 1, 2);

        ColumnConstraints col1Constraint = new ColumnConstraints();
        col1Constraint.setHalignment(HPos.RIGHT);
        col1Constraint.setMinWidth(80);

        ColumnConstraints col2Constraint = new ColumnConstraints();
        col1Constraint.setHalignment(HPos.RIGHT);
        col1Constraint.setMinWidth(70);

        resultsBox.getColumnConstraints().addAll(col1Constraint, col2Constraint);

        VBox headerPane = new VBox();

        Label titleLabel = new Label("Haskin Bobbins POS");
        titleLabel.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 24));
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        Hyperlink logoutLink = new Hyperlink("Logout");
        Font arial = Font.font("Arial", FontWeight.LIGHT, 11);
        logoutLink.setStyle("-fx-text-fill: black; -fx-border-color: transparent; -fx-background-color: transparent");

        logoutLink.setFont(arial);
        logoutLink.setPadding(new Insets(0, 40, 0, 0));
        logoutLink.setAlignment(Pos.BASELINE_RIGHT);
        logoutLink.setOnAction(_ -> stage.setScene(getLoginScene(store, stage)));

        HBox logoutBox = new HBox(logoutLink);
        logoutBox.setAlignment(Pos.CENTER_RIGHT);

        headerPane.getChildren().addAll(titleBox, logoutBox);

        GridPane menuGrid = new GridPane(2, 8);
        menuGrid.setHgap(20);
        menuGrid.setVgap(10);
        menuGrid.setMinWidth(200.0);
        menuGrid.setMaxWidth(Double.MAX_VALUE);
        menuGrid.setAlignment(Pos.CENTER);
        menuGrid.setPadding(new Insets(12));
        menuGrid.setStyle("-fx-background-color: #fff;" +
                "-fx-border-color: #ddd;" +
                "-fx-border-radius: 12;" +
                " -fx-background-radius: 12;"
        );

        menuGrid.add(new Text("Item"), 0, 0);
        menuGrid.add(new Text("Price"), 1, 0);
        menuGrid.add(new Text("Qty"), 2, 0);

        TextField[] quantityFields = new TextField[items.length];

        for (int i = 1; i <= items.length; i++) {
            Item item = items[i - 1];
            Text itemNameText = new Text(item.getName());
            Text itemCostText = new Text(String.format("$%.2f", item.getCost()));

            TextField qtyEntryField = new TextField("0");
            quantityFields[i - 1] = qtyEntryField;

            menuGrid.add(itemNameText, 0, i);
            menuGrid.add(itemCostText, 1, i);
            menuGrid.add(qtyEntryField, 2, i);
        }


        // Added the exception error label
        Label errorLabel = new Label();
        errorLabel.setFont(Font.font(10));
        errorLabel.setStyle("-fx-text-fill: red;");

        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(event -> {
            // This clears any previous error before re-confirming any inputs the person has to type on the box.
            errorLabel.setText("");

            int[] quantities = new int[quantityFields.length];
            for (int i = 0; i < quantityFields.length; i++) {
                String text = quantityFields[i].getText().trim();
                try {
                    int q = Integer.parseInt(text);
                    if (q < 0) {
                        errorLabel.setText("Quantity for " + items[i].getName()
                                + " can't be negative.");
                        return;
                    }
                    quantities[i] = q;
                } catch (NumberFormatException ex) {
                    // Shows up when the user types letters, symbols, a decimal, or leaves the blank empty.
                    errorLabel.setText("Quantity for " + items[i].getName()
                            + " must be a whole number.");
                    return;
                }
            }

            ArrayList<String> selectedItems = new ArrayList<>();
            for (int i = 0; i < quantities.length; i++) {
                for (int j = 0; j < quantities[i]; j++) {
                    selectedItems.add(items[i].getName());
                }
            }

            String[] itemNames = selectedItems.toArray(value -> new String[0]);
            double subCost = store.calculateCost(itemNames);

            double taxes = store.calculateTax(itemNames);
            double total = store.calculateTotal(itemNames);

            subTotalValue.setText(String.format("$%.2f", subCost));
            taxesValue.setText(String.format("$%.2f", taxes));
            totalValue.setText(String.format("$%.2f", total));
        });

        HBox calculateBox = new HBox(10, errorLabel, calculateButton);
        calculateBox.setAlignment(Pos.BASELINE_RIGHT);
        menuGrid.add(calculateBox, 0, items.length + 2, 3, 1);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10, 0, 10, 0));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #92c4fd;");

        root.getChildren().addAll(
                headerPane,
                menuGrid,
                resultsBox
        );
        VBox.setMargin(menuGrid, new Insets(0, 20, 0, 20));

        stage.setMaxWidth(WIDTH * 2);
        stage.setMaxHeight(HEIGHT * 2);
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);

        return new Scene(root, WIDTH, HEIGHT);
    }
}
