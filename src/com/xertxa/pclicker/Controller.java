package com.xertxa.pclicker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;

import static com.xertxa.pclicker.Main.primaryStage;

public class Controller {
    private static ArrayList<String> keyNames = new ArrayList<>(Arrays.asList("UNDEFINED", "LBUTTON", "RBUTTON", "CANCEL", "MBUTTON", "XBUTTON1", "XBUTTON2", "BACK", "TAB", "CLEAR", "RETURN", "SHIFT", "CONTROL", "MENU", "PAUSE", "CAPITAL", "KANA", "HANGUEL", "HANGUL", "JUNJA", "FINAL", "HANJA", "KANJI", "ESCAPE", "CONVERT", "NONCONVERT", "ACCEPT", "MODECHANGE", "SPACE", "PRIOR", "NEXT", "END", "HOME", "LEFT", "UP", "RIGHT", "DOWN", "SELECT", "PRINT", "EXECUTE", "SNAPSHOT", "INSERT", "DELETE", "HELP", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "LWIN", "RWIN", "APPS", "SLEEP", "NUMPAD0", "NUMPAD1", "NUMPAD2", "NUMPAD3", "NUMPAD4", "NUMPAD5", "NUMPAD6", "NUMPAD7", "NUMPAD8", "NUMPAD9", "MULTIPLY", "ADD", "SEPARATOR", "SUBTRACT", "DECIMAL", "DIVIDE", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12", "F13", "F14", "F15", "F16", "F17", "F18", "F19", "F20", "F21", "F22", "F23", "F24", "NUMLOCK", "SCROLL", "LSHIFT", "RSHIFT", "LCONTROL", "RCONTROL", "LMENU", "RMENU"));
    private static ObservableList<String> keyCodesList = FXCollections.observableArrayList(keyNames);
    private static boolean visible = true;
    private ArrayList<Integer> keyCodes = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 8, 9, 12, 13, 16, 17, 18, 19, 20, 21, 21, 21, 23, 24, 25, 25, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 144, 145, 160, 161, 162, 163, 164, 165));
    @FXML
    private ChoiceBox toggleKey;
    @FXML
    private ChoiceBox hideShowKey;
    @FXML
    private TextField maxCPS;
    @FXML
    private TextField minCPS;
    @FXML
    private TextField windowList;
    @FXML
    private CheckBox requireWindow;

    public static void toggleVisibility() {
        visible = !visible;
        if (visible) {
            primaryStage.show();
        } else {
            primaryStage.hide();
        }
    }

    @FXML
    private void exit() {
        System.exit(0);
    }

    @FXML
    private void minimize() {
        primaryStage.hide();
    }

    @FXML
    private void apply() {
        AutoClicker.maxCPS = Double.parseDouble(maxCPS.getText());
        AutoClicker.minCPS = Double.parseDouble(minCPS.getText());
        AutoClicker.toggleKey = keyCodes.get(toggleKey.getSelectionModel().getSelectedIndex());
        AutoClicker.hideShowKey = keyCodes.get(hideShowKey.getSelectionModel().getSelectedIndex());
        AutoClicker.windowList = Arrays.asList(windowList.getText().split(","));
        AutoClicker.requireWindow = requireWindow.isSelected();
    }

    @FXML
    private void toggle() {
        AutoClicker.toggle();
    }

    public void initialize() {
        toggleKey.setItems(keyCodesList);
        toggleKey.getSelectionModel().select(77);
        hideShowKey.setItems(keyCodesList);
        hideShowKey.getSelectionModel().select(34);
        apply();
    }
}
