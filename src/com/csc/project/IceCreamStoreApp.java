package com.csc.project;

import com.csc.project.data.Item;
import com.csc.project.data.Store;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class IceCreamStoreApp extends Application {

    private final Store store = new IceCreamStore();

    public static void main(String[] args) {
        launch(args);
    }

    private static GridPane getMenuGridPane(Item[] items) {
        GridPane menuGridPane = new GridPane(2, 8);
        menuGridPane.setHgap(20);
        menuGridPane.setVgap(10);
        menuGridPane.setMaxWidth(200.0);
        menuGridPane.setMinWidth(200.0);
        menuGridPane.setPadding(new Insets(12));
        menuGridPane.setStyle("-fx-background-color: #fff;" +
                "-fx-border-color: #ddd;" +
                "-fx-border-radius: 12;" +
                " -fx-background-radius: 12;"
        );

        // Menu Grid Headers
        menuGridPane.add(new Text("Item"), 0, 0);
        menuGridPane.add(new Text("Price"), 1, 0);
        menuGridPane.add(new Text("Qty"), 2, 0);

        // Menu Grid Rows
        for (int i = 1; i <= items.length; i++) {
            Item item = items[i - 1];
            Text itemNameText = new Text(item.getName());
            Text itemCostText = new Text(String.format("$%.2f", item.getCost()));

            TextField qtyEntryField = new TextField("0");
            menuGridPane.add(itemNameText, 0, i);
            menuGridPane.add(itemCostText, 1, i);
            menuGridPane.add(qtyEntryField, 2, i);
        }

        Button calculateButton = new Button("Calculate");
        HBox calculateBox = new HBox();
        calculateBox.setAlignment(Pos.BASELINE_RIGHT);
        calculateBox.getChildren().add(calculateButton);
        menuGridPane.add(calculateBox, 0, items.length + 2, 3, 1);

        return menuGridPane;
    }

    private static GridPane getResultsBox(Item[] items) {
        GridPane gridPane = new GridPane(2, 8);
        gridPane.setPadding(new Insets(0, 10, 0, 0));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setMaxWidth(200.0);
        gridPane.setMinWidth(200.0);

        Font font = Font.font("Arial", FontWeight.BOLD, 12);
        Label subTotalLabel = new Label("SubTotal:");
        subTotalLabel.setFont(font);
        Label subTotalValue = new Label("1.00");
        Label taxesLabel = new Label("Taxes:");
        taxesLabel.setFont(font);
        Label taxesValue = new Label("2.00");

        Label totalLabel = new Label("Total:");
        totalLabel.setFont(font);
        Label totalValue = new Label("3.00");

        gridPane.add(subTotalLabel, 0, 0);
        gridPane.add(subTotalValue, 1, 0);

        gridPane.add(taxesLabel, 0, 1);
        gridPane.add(taxesValue, 1, 1);

        gridPane.add(totalLabel, 0, 2);
        gridPane.add(totalValue, 1, 2);

        ColumnConstraints col1Constraint = new ColumnConstraints();
        col1Constraint.setHalignment(HPos.RIGHT);
        col1Constraint.setPercentWidth(80);
        gridPane.getColumnConstraints().add(col1Constraint);

        return gridPane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Haskin Bobbins ICS POS");
        stage.setMaxHeight(420);
        stage.setMaxWidth(300);

        Scene scene = getLoginScene(stage);

        stage.setScene(scene);
        stage.show();
    }

    private Node getHeaderPane(Stage stage) {
        VBox box = new VBox();

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
        logoutLink.setOnAction(_ -> stage.setScene(getLoginScene(stage)));

        HBox logoutBox = new HBox(logoutLink);
        logoutBox.setAlignment(Pos.CENTER_RIGHT);

        box.getChildren().addAll(titleBox, logoutBox);

        return box;
    }

    private Scene getLoginScene(Stage stage) {
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
                stage.setScene(getPosScene(stage));
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

        VBox mainBox = new VBox(10, loginHeader, loginGrid);

        mainBox.setAlignment(Pos.CENTER);
        mainBox.setStyle("-fx-background-color: #92c4fd;");
        VBox.setMargin(loginGrid, new Insets(0, 20, 0, 20));
        return new Scene(mainBox, 400, 200);
    }

    private Scene getPosScene(Stage stage) {
        Item[] items = store.getItems();

        // Grid for our menu items
        Node headerPane = getHeaderPane(stage);
        Node menuGridPane = getMenuGridPane(items);
        Node resultsBox = getResultsBox(items);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10, 0, 10, 0));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #92c4fd;");

        root.getChildren().addAll(
                headerPane,
                menuGridPane,
                resultsBox
        );

        return new Scene(root, 300, 420);
    }
}