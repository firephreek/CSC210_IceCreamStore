package com.csc.project;

import com.csc.project.data.Item;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class IceCreamStoreApp extends Application {

    private final IceCreamStore store = new IceCreamStore();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Haskin Bobbins ICS POS");
        stage.setMaxHeight(400);
        stage.setMaxWidth(800);

        Scene scene = getLoginScene(stage);

        stage.setScene(scene);
        stage.show();
    }

    private Scene getLoginScene(Stage stage) {
        TextField usernameTextField = new TextField("Your username ");
        PasswordField passwordField = new PasswordField();
        Label usernameLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");

        VBox box = new VBox(6);
        box.setPadding(new Insets(25, 5, 5, 50));
        Button loginButton = new Button("Login");
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setFont(Font.font(10));
        loginButton.setOnAction(event -> {
            if (store.login(usernameTextField.getText(), passwordTextField.getText())) {
                // How to initiate a scene change from here?
                stage.setScene(getPosScene(stage));
            } else {
                errorLabel.setText("Invalid username/password");
            }
        });

        box.getChildren().addAll(
                usernameLabel,
                usernameTextField,
                passwordLabel,
                passwordField,
                loginButton,
                errorLabel
        );

        return new Scene(box, 400, 200);
    }

    private Scene getPosScene(Stage stage) {
        Group root = new Group();
        ObservableList<Node> list = root.getChildren();

        Item[] items = store.getItems();
        for (int i = 0; i < items.length; i++) {
            Text text = new Text(items[i].getName());
            text.setX(20);
            text.setY(20 * (i + 1));
            list.add(text);
        }
        /* Maybe we have something like
            <item> ....... $<price>  [+][-]      <qty>
         in a list box...the plus/minus changes the quantity, that can be a value on the row or something maybe?
         Figure out where/how I keep track, but maybe we just do it on the fly?
         Could get weird when you try to subtract...but we can just do a check for when/if it's less than zero at some point it just goes to zero.

         How hard would it be to have

         <MENU> [+|-]                     <ORDER>
                                          ------
                                          <COST>
                                          <TAX>
                                          <TOTAL>

        Almost like a receipt or something?
        */

        return new Scene(root, 400, 200);
    }
}