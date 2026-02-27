package org.example;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application{
    private Stage secondaryStage;
    private Pane currentLayout;
    private Scene secondaryScene;
    private static final String STYLE_LABEL =
            "-fx-font-family: 'Segoe UI';" +
                    "-fx-font-size: 13px;" +
                    "-fx-text-fill: #4a4a8a;" +
                    "-fx-font-weight: bold;";

    private static final String STYLE_TEXTFIELD =
            "-fx-font-family: 'Segoe UI';" +
                    "-fx-font-size: 12px;" +
                    "-fx-border-color: #b0aee8;" +
                    "-fx-border-radius: 6;" +
                    "-fx-background-radius: 6;" +
                    "-fx-padding: 5 8 5 8;";

    private static final String STYLE_BTN_PRIMARY =
            "-fx-font-family: 'Segoe UI';" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-color: #6c63ff;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 7 18 7 18;" +
                    "-fx-cursor: hand;";

    private static final String STYLE_BTN_SECONDARY =
            "-fx-font-family: 'Segoe UI';" +
                    "-fx-font-size: 12px;" +
                    "-fx-text-fill: #6c63ff;" +
                    "-fx-background-color: transparent;" +
                    "-fx-border-color: #6c63ff;" +
                    "-fx-border-radius: 8;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 7 18 7 18;" +
                    "-fx-cursor: hand;";

    private static final String STYLE_CHECKBOX =
            "-fx-font-family: 'Segoe UI';" +
                    "-fx-font-size: 12px;" +
                    "-fx-text-fill: #5a5a9a;";

    private static final String STYLE_COMBOBOX =
            "-fx-font-family: 'Segoe UI';" +
                    "-fx-font-size: 12px;" +
                    "-fx-border-color: #b0aee8;" +
                    "-fx-border-radius: 6;" +
                    "-fx-background-radius: 6;";
    public void start(Stage PrimeryStage){
        ComboBox<String> layoutSelector = new ComboBox<>();
        layoutSelector.getItems().addAll("BorderPane", "FlowPane", "GridPane", "VBox", "HBox");
        layoutSelector.setValue("FlowPane");
        layoutSelector.setStyle(STYLE_COMBOBOX);

        Button applyButton = new Button("Aplicar layout");
        applyButton.setStyle(STYLE_BTN_PRIMARY);
        applyButton.setOnAction(e->{
                String selected = layoutSelector.getValue();
                showSecondaryWindow(selected);
        });
        VBox position = new VBox(20, new Label("Selecciona un layout"), layoutSelector, applyButton);
        position.setPadding(new Insets(20));
        position.setAlignment(Pos.TOP_LEFT);
        PrimeryStage.setScene(new Scene(position,240,170));
        PrimeryStage.setTitle("Welcome");

        PrimeryStage.show();

        secondaryStage = new Stage();
        showSecondaryWindow("FlowPane");
        secondaryStage.show();

        PrimeryStage.setOnCloseRequest(e->{
            secondaryStage.close();
        } );
    }
    private Label makeLabel(String text) {
        Label l = new Label(text);
        l.setStyle(STYLE_LABEL);
        return l;
    }

    private TextField makeTextField(String text) {
        TextField tf = new TextField(text);
        tf.setStyle(STYLE_TEXTFIELD);
        return tf;
    }

    private CheckBox makeCheckBox(String text) {
        CheckBox cb = new CheckBox(text);
        cb.setStyle(STYLE_CHECKBOX);
        return cb;
    }

    private Button makePrimaryButton(String text) {
        Button b = new Button(text);
        b.setStyle(STYLE_BTN_PRIMARY);
        return b;
    }

    private Button makeSecondaryButton(String text) {
        Button b = new Button(text);
        b.setStyle(STYLE_BTN_SECONDARY);
        return b;
    }
    private void showSecondaryWindow(String layoutName) {
        currentLayout = buildLayout(layoutName);
        secondaryScene = new Scene(currentLayout);
        secondaryStage.setTitle(layoutName);
        secondaryStage.setScene(secondaryScene);
        secondaryStage.sizeToScene();
    }
    private Pane buildLayout(String name){
        Label lblName = makeLabel("Nombre:");
        TextField tfName = makeTextField("");
        Label lblCorreo = makeLabel("Correo electronico:");
        TextField tfCorreo = makeTextField("");
        CheckBox cbRemerber = makeCheckBox("Recordarme.");
        Button btOk = makePrimaryButton("Aceptar");
        Button btCancel = makeSecondaryButton("Cancelar");

        switch (name) {
            case "BorderPane": {
                BorderPane bP = new BorderPane();
                bP.setPadding(new Insets(10));
                Label userForms = new Label("Formulario de usuario.");
                BorderPane.setAlignment(userForms, Pos.TOP_CENTER);
                bP.setTop(userForms);

                VBox center = new VBox(8,lblName,tfName,lblCorreo,tfCorreo, cbRemerber);
                center.setPadding(new Insets(10,0,10,0));
                bP.setCenter(center);

                HBox button = new HBox(10, btOk, btCancel);
                button.setAlignment(Pos.CENTER_RIGHT);
                bP.setBottom(button);
                return bP;
            }
            case "FlowPane":{
                FlowPane fP = new FlowPane();
                fP.setHgap(10);
                fP.setVgap(10);
                fP.setPadding(new Insets(15));
                fP.getChildren().addAll(lblName,tfName,lblCorreo,tfCorreo,cbRemerber,btOk,btCancel);
                return fP;
            }
            case "GridPane": {
                GridPane gP = new GridPane();
                gP.setVgap(10);
                gP.setHgap(10);
                gP.setPadding(new Insets(15));
                gP.add(lblName,0,0); gP.add(tfName,1,0);
                gP.add(lblCorreo,0,1); gP.add(tfCorreo,1,1);
                gP.add(cbRemerber,0,2);
                gP.add(btOk,0,3); gP.add(btCancel,1,3);
                return gP;
            }
            case "VBox": {
                VBox vB = new VBox(10);
                vB.setPadding(new Insets(15));
                vB.getChildren().addAll(lblName,tfName,lblCorreo,tfCorreo,cbRemerber,btOk,btCancel);
                return vB;
            }
            case "HBox": {
                HBox hB = new HBox();
                hB.setAlignment(Pos.CENTER_LEFT);
                hB.setPadding(new Insets(15));
                hB.getChildren().addAll(lblName,tfName,lblCorreo,tfCorreo,cbRemerber,btOk,btCancel);
                return hB;
            }
            default:
                return new Pane();
        }
    }
}