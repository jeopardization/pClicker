package com.xertxa.pclicker.util;

class Math {
    static double clamp(double val, double min, double max) {
        return java.lang.Math.max(min, java.lang.Math.min(max, val));
    }
}
