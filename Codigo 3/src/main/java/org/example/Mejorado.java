package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Mejorado extends Application {

    private Stage secondaryStage;
    private Pane currentLayout;
    private Scene secondaryScene;

    // ── Estilos reutilizables ─────────────────────────────────────────────
    private static final String STYLE_LABEL =
            "-fx-font-family: 'Segoe UI';" +
                    "-fx-font-size: 13px;" +
                    "-fx-text-fill: #4a4a8a;" +
                    "-fx-font-weight: bold;";

    private static final String STYLE_TITLE =
            "-fx-font-family: 'Segoe UI';" +
                    "-fx-font-size: 15px;" +
                    "-fx-text-fill: #6c63ff;" +
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

    @Override
    public void start(Stage primaryStage) {

        // ── ComboBox principal ────────────────────────────────────────────
        Label selectorLabel = new Label("Selecciona un layout:");
        selectorLabel.setStyle(STYLE_LABEL);

        ComboBox<String> layoutSelector = new ComboBox<>();
        layoutSelector.getItems().addAll("BorderPane", "FlowPane", "GridPane", "VBox", "HBox", "StackPane", "TilePane", "AnchorPane");
        layoutSelector.setValue("BorderPane");
        layoutSelector.setStyle(STYLE_COMBOBOX);
        layoutSelector.setMaxWidth(Double.MAX_VALUE);

        Button applyButton = new Button("Aplicar layout");
        applyButton.setStyle(STYLE_BTN_PRIMARY);
        applyButton.setOnAction(e -> showSecondaryWindow(layoutSelector.getValue()));

        layoutSelector.setOnAction(e -> showSecondaryWindow(layoutSelector.getValue()));

        VBox root = new VBox(12, selectorLabel, layoutSelector, applyButton);
        root.setPadding(new Insets(22));
        root.setAlignment(Pos.TOP_LEFT);

        primaryStage.setTitle("Selector de Layout");
        primaryStage.setScene(new Scene(root, 240, 140));
        primaryStage.setResizable(false);
        primaryStage.setX(80);
        primaryStage.setY(150);
        primaryStage.show();

        secondaryStage = new Stage();
        showSecondaryWindow("BorderPane");
        secondaryStage.setX(360);
        secondaryStage.setY(150);
        secondaryStage.show();

        primaryStage.setOnCloseRequest(e -> secondaryStage.close());
    }

    private void showSecondaryWindow(String layoutName) {
        currentLayout = buildLayout(layoutName);
        secondaryScene = new Scene(currentLayout);
        secondaryStage.setTitle("Componentes — " + layoutName);
        secondaryStage.setScene(secondaryScene);
        secondaryStage.sizeToScene();
    }

    // ── Crea los componentes ya con estilo ────────────────────────────────
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

    private Pane buildLayout(String name) {

        Label lbNombre   = makeLabel("Nombre:");
        TextField tfNombre = makeTextField("Alice");
        Label lbCorreo   = makeLabel("Correo:");
        TextField tfCorreo = makeTextField("alice@mail.com");
        CheckBox cb      = makeCheckBox("Recordarme");
        Button btnOk     = makePrimaryButton("Aceptar");
        Button btnCancel = makeSecondaryButton("Cancelar");

        switch (name) {

            case "BorderPane": {
                BorderPane p = new BorderPane();
                p.setPadding(new Insets(14));

                Label top = new Label("Formulario de usuario");
                top.setStyle(STYLE_TITLE);
                BorderPane.setAlignment(top, Pos.CENTER);
                BorderPane.setMargin(top, new Insets(0, 0, 10, 0));
                p.setTop(top);

                VBox center = new VBox(10, lbNombre, tfNombre, lbCorreo, tfCorreo, cb);
                center.setPadding(new Insets(8, 0, 8, 0));
                p.setCenter(center);

                HBox bottom = new HBox(10, btnOk, btnCancel);
                bottom.setAlignment(Pos.CENTER_RIGHT);
                bottom.setPadding(new Insets(10, 0, 0, 0));
                p.setBottom(bottom);

                return p;
            }

            case "FlowPane": {
                FlowPane p = new FlowPane();
                p.setHgap(12);
                p.setVgap(12);
                p.setPadding(new Insets(18));
                p.getChildren().addAll(lbNombre, tfNombre, lbCorreo, tfCorreo, cb, btnOk, btnCancel);
                return p;
            }

            case "GridPane": {
                GridPane p = new GridPane();
                p.setHgap(12);
                p.setVgap(12);
                p.setPadding(new Insets(18));
                p.add(lbNombre,  0, 0); p.add(tfNombre,  1, 0);
                p.add(lbCorreo,  0, 1); p.add(tfCorreo,  1, 1);
                p.add(cb,        0, 2);
                p.add(btnOk,     0, 3); p.add(btnCancel, 1, 3);
                return p;
            }

            case "VBox": {
                VBox p = new VBox(12);
                p.setPadding(new Insets(18));
                p.getChildren().addAll(lbNombre, tfNombre, lbCorreo, tfCorreo, cb, btnOk, btnCancel);
                return p;
            }

            case "HBox": {
                HBox p = new HBox(12);
                p.setPadding(new Insets(18));
                p.setAlignment(Pos.CENTER_LEFT);
                p.getChildren().addAll(lbNombre, tfNombre, lbCorreo, tfCorreo, cb, btnOk, btnCancel);
                return p;
            }

            // ── StackPane: componentes apilados al centro ─────────────────
            case "StackPane": {
                StackPane p = new StackPane();
                p.setPadding(new Insets(18));

                HBox botones = new HBox(10, btnOk, btnCancel);
                botones.setAlignment(Pos.CENTER);

                VBox card = new VBox(12, lbNombre, tfNombre, lbCorreo, tfCorreo, cb, botones);
                card.setAlignment(Pos.CENTER);
                card.setPadding(new Insets(20));
                card.setMaxWidth(260);

                p.getChildren().add(card);
                return p;
            }

            // ── TilePane: celdas del mismo tamaño en cuadrícula ───────────
            case "TilePane": {
                TilePane p = new TilePane();
                p.setHgap(12);
                p.setVgap(12);
                p.setPadding(new Insets(18));
                p.setPrefColumns(2);
                p.getChildren().addAll(lbNombre, tfNombre, lbCorreo, tfCorreo, cb, btnOk, btnCancel);
                return p;
            }

            // ── AnchorPane: posición fija con coordenadas exactas ─────────
            case "AnchorPane": {
                AnchorPane p = new AnchorPane();
                p.setPrefSize(450, 320);

                AnchorPane.setTopAnchor(lbNombre,   30.0);  AnchorPane.setLeftAnchor(lbNombre,   30.0);
                AnchorPane.setTopAnchor(tfNombre,   30.0);  AnchorPane.setLeftAnchor(tfNombre,  110.0);
                AnchorPane.setTopAnchor(lbCorreo,   80.0);  AnchorPane.setLeftAnchor(lbCorreo,   30.0);
                AnchorPane.setTopAnchor(tfCorreo,   80.0);  AnchorPane.setLeftAnchor(tfCorreo,  110.0);
                AnchorPane.setTopAnchor(cb,         130.0); AnchorPane.setLeftAnchor(cb,         30.0);
                AnchorPane.setTopAnchor(btnOk,      180.0); AnchorPane.setLeftAnchor(btnOk,      30.0);
                AnchorPane.setTopAnchor(btnCancel,  180.0); AnchorPane.setLeftAnchor(btnCancel, 120.0);

                p.getChildren().addAll(lbNombre, tfNombre, lbCorreo, tfCorreo, cb, btnOk, btnCancel);
                return p;
            }

            default:
                return new Pane();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}