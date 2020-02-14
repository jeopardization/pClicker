package respectful.rapist.pclicker;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.awt.*;
import java.security.SecureRandom;

public class AutoClicker implements Runnable {
    public static boolean active, robotMouseEvent, requireWindow, enabled;
    public static int toggleKey = 77, hideShowKey = 34;
    static String windowList = "Minecraft 1.7.10,Minecraft 1.8.9";
    static float maxCPS = 14.0F, minCPS = 9.0F;
    private SecureRandom random = new SecureRandom();
    private long holdDelay = (long) nextFloat(1000.0F / maxCPS, 1000.0F / minCPS), releaseDelay = holdDelay / 3L;
    private Robot robot;

    public AutoClicker() {
        try {
            robot = new Robot();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK).show();
        }
    }

    public static void toggle() {
        enabled = !enabled;
        if (enabled) {
            robotMouseEvent = false;
            new Thread(new AutoClicker()).start();
            Controller.instance.toggle.setSelected(true);
        } else {
            Controller.instance.toggle.setSelected(false);
        }
    }

    @Override
    public void run() {
        try {
            robot = new Robot();
            while (enabled) {
                if (!getWindow().equals("pClicker")) {
                    if ((requireWindow) && (!inWindow())) {
                        return;
                    }
                    if (active) {
                        Thread.sleep(holdDelay - releaseDelay);
                        robotMouseEvent = true;
                        robot.mousePress(16);
                        Thread.sleep(releaseDelay);
                        robot.mouseRelease(16);
                        holdDelay = (long) nextFloat(1000.0F / maxCPS, 1000.0F / minCPS);
                        releaseDelay = holdDelay / 3L;
                    }
                }
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK).show();
        }
    }

    private String getWindow() {
        WinDef.HWND hWnd = User32.INSTANCE.GetForegroundWindow();
        char[] buffer = new char[2048];
        User32.INSTANCE.GetWindowText(hWnd, buffer, 1024);
        return Native.toString(buffer);
    }

    private boolean inWindow() {
        for (String window : windowList.split(",")) {
            if (window.equals(getWindow())) {
                return true;
            }
        }
        return false;
    }

    public float nextFloat(float min, float max) {
        return min + (max - min) * random.nextFloat();
    }
}
