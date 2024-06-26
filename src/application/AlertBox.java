package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String message, String button){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        window.setMinHeight(150);

        Label poruka = new Label();
        poruka.setText(message);

        Button dugme = new Button();
        dugme.setText(button);
        dugme.setOnAction(e -> window.close());

        VBox layout = new VBox();
        layout.getChildren().addAll(poruka, dugme);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
