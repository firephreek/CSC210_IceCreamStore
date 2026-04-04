package com.csc.project;

import com.csc.project.data.IceCreamStore;
import com.csc.project.data.Store;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.csc.project.ui.SceneBuilder.getLoginScene;

public class IceCreamStoreApp extends Application {

    private final Store store = new IceCreamStore();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Haskin Bobbins ICS POS");
        stage.setMaxHeight(420);
        stage.setMaxWidth(300);

        Scene scene = getLoginScene(store, stage);

        stage.setScene(scene);
        stage.show();
    }
}