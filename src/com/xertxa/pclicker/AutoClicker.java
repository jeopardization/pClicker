package com.xertxa.pclicker;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.xertxa.pclicker.util.Random;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class AutoClicker implements Runnable {
    public static boolean active;
    public static boolean robotMouseEvent;
    public static int toggleKey;
    public static int hideShowKey;
    static boolean requireWindow;
    static List windowList = new ArrayList<String>();
    static double maxCPS;
    static double minCPS;
    private static boolean enabled;
    private static Robot robot;

    public static void toggle() {
        enabled = !enabled;
        if (enabled) {
            robotMouseEvent = false;
            new Thread(new AutoClicker()).start();
        }
    }

    private void click() {
        try {
            robotMouseEvent = true;
            robot.mousePress(16);
            Thread.sleep(Random.nextLong(5L, 15L));
            robot.mouseRelease(16);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            robot = new Robot();
            while (enabled) {
                if (!getWindowTitle().equals("pClicker")) {
                    if ((requireWindow) && (!inWindow())) {
                        return;
                    }
                    if (active) {
                        Thread.sleep(Random.nextAverageLong(1000.0D / maxCPS, 1000.0D / minCPS));
                        click();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getWindowTitle() {
        WinDef.HWND hWnd = User32.INSTANCE.GetForegroundWindow();
        char[] buffer = new char[2048];
        User32.INSTANCE.GetWindowText(hWnd, buffer, 1024);
        return Native.toString(buffer);
    }

    private boolean inWindow() {
        for (Object window : windowList) {
            return window.equals(getWindowTitle());
        }
        return false;
    }
}
