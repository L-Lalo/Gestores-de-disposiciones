module Codigo3 {
    requires javafx.controls;
    requires javafx.graphics;
    opens org.example to javafx.controls, javafx.graphics;
    exports org.example;
}