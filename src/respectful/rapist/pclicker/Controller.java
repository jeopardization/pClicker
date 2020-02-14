package respectful.rapist.pclicker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    public static Controller instance;
    private static File file = new File("config");
    private static ObservableList<String> keyNames = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("UNDEFINED", "LBUTTON", "RBUTTON", "CANCEL", "MBUTTON", "XBUTTON1", "XBUTTON2", "BACK", "TAB", "CLEAR", "RETURN", "SHIFT", "CONTROL", "MENU", "PAUSE", "CAPITAL", "KANA", "HANGUEL", "HANGUL", "JUNJA", "FINAL", "HANJA", "KANJI", "ESCAPE", "CONVERT", "NONCONVERT", "ACCEPT", "MODECHANGE", "SPACE", "PRIOR", "NEXT", "END", "HOME", "LEFT", "UP", "RIGHT", "DOWN", "SELECT", "PRINT", "EXECUTE", "SNAPSHOT", "INSERT", "DELETE", "HELP", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "LWIN", "RWIN", "APPS", "SLEEP", "NUMPAD0", "NUMPAD1", "NUMPAD2", "NUMPAD3", "NUMPAD4", "NUMPAD5", "NUMPAD6", "NUMPAD7", "NUMPAD8", "NUMPAD9", "MULTIPLY", "ADD", "SEPARATOR", "SUBTRACT", "DECIMAL", "DIVIDE", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12", "F13", "F14", "F15", "F16", "F17", "F18", "F19", "F20", "F21", "F22", "F23", "F24", "NUMLOCK", "SCROLL", "LSHIFT", "RSHIFT", "LCONTROL", "RCONTROL", "LMENU", "RMENU")));
    private static boolean visible = true;
    @FXML
    public ToggleButton toggle;
    private ArrayList<Integer> keyCodes = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 8, 9, 12, 13, 16, 17, 18, 19, 20, 21, 21, 21, 23, 24, 25, 25, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 144, 145, 160, 161, 162, 163, 164, 165));
    @FXML
    private ChoiceBox toggleKey, hideShowKey;
    @FXML
    private TextField maxCPS, minCPS, windowList;
    @FXML
    private CheckBox requireWindow;

    public static void toggleVisibility() {
        visible = !visible;
        if (visible) {
            Main.primaryStage.show();
        } else {
            Main.primaryStage.hide();
        }
    }

    @FXML
    private void exit() {
        System.exit(0);
    }

    @FXML
    private void minimize() {
        Main.primaryStage.hide();
    }

    @FXML
    private void apply() {
        AutoClicker.maxCPS = Float.parseFloat(maxCPS.getText());
        AutoClicker.minCPS = Float.parseFloat(minCPS.getText());
        AutoClicker.toggleKey = keyCodes.get(toggleKey.getSelectionModel().getSelectedIndex());
        AutoClicker.hideShowKey = keyCodes.get(hideShowKey.getSelectionModel().getSelectedIndex());
        AutoClicker.windowList = windowList.getText();
        AutoClicker.requireWindow = requireWindow.isSelected();
        writeConfig();
    }

    @FXML
    public void toggle() {
        AutoClicker.toggle();
    }

    public void initialize() {
        instance = this;
        toggleKey.setItems(keyNames);
        toggleKey.getSelectionModel().select(AutoClicker.toggleKey);
        hideShowKey.setItems(keyNames);
        hideShowKey.getSelectionModel().select(AutoClicker.hideShowKey);
        try {
            if (file.exists()) {
                String[] config = new BufferedReader(new FileReader(file)).readLine().split(";");
                float minCPS = Float.parseFloat(config[0]);
                AutoClicker.minCPS = minCPS;
                this.minCPS.setText(minCPS + "");
                float maxCPS = Float.parseFloat(config[1]);
                AutoClicker.maxCPS = maxCPS;
                this.maxCPS.setText(maxCPS + "");
                int toggleKey = Integer.parseInt(config[2]);
                AutoClicker.toggleKey = toggleKey;
                this.toggleKey.getSelectionModel().select(keyCodes.indexOf(toggleKey));
                int hideShowKey = Integer.parseInt(config[3]);
                AutoClicker.hideShowKey = hideShowKey;
                this.hideShowKey.getSelectionModel().select(keyCodes.indexOf(hideShowKey));
                String windowList = config[4];
                AutoClicker.windowList = windowList;
                this.windowList.setText(windowList);
                boolean requireWindow = Integer.parseInt(config[5]) == 1;
                AutoClicker.requireWindow = requireWindow;
                this.requireWindow.setSelected(requireWindow);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Configuration file was not found, a new one will be created.", ButtonType.OK).show();
                apply();
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK).show();
        }
    }


    public void writeConfig() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            if (file.exists()) {
                fileWriter.write(AutoClicker.maxCPS + ";" + AutoClicker.minCPS + ";" + AutoClicker.toggleKey + ";" + AutoClicker.hideShowKey + ";" + AutoClicker.windowList + ";" + (AutoClicker.requireWindow ? "1" : "0"));
                fileWriter.flush();
            } else {
                if (file.createNewFile()) {
                    writeConfig();
                }
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK).show();
        }
    }
}
