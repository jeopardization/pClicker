package com.xertxa.pclicker;

import com.xertxa.pclicker.listener.Keyboard;
import com.xertxa.pclicker.listener.Mouse;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.mouse.GlobalMouseHook;

public class Main extends Application {
    static Stage primaryStage;
    private double xOffset = 0.0D;
    private double yOffset = 0.0D;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.setImplicitExit(false);
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("tabs/index.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("pClicker");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        GlobalMouseHook mouseHook = new GlobalMouseHook();
        mouseHook.addMouseListener(new Mouse());
        GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook();
        keyboardHook.addKeyListener(new Keyboard());
    }
}
