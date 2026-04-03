module com.csc.icecream.IceCreamStoreApp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.csc.project to javafx.fxml;
    exports com.csc.project;
    exports com.csc.project.data;
}