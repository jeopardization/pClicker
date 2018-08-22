package com.xertxa.pclicker.util;

import static com.xertxa.pclicker.util.Math.clamp;

public class Random {
    private static java.util.Random random;

    public static long nextAverageLong(double min, double max) {
        random = new java.util.Random();
        double deviation = 15.0D;
        double mean = (max + min) / 2.0D;
        double randGauss = (random.nextGaussian() * deviation);
        return (long) clamp(java.lang.Math.round(randGauss + mean), min, max);
    }

    public static long nextLong(long min, long max) {
        return min == max ? min : (long) nextDouble((double) min, (double) max);
    }

    private static double nextDouble(double min, double max) {
        random = new java.util.Random();
        return min == max ? min : min + (max - min) * random.nextDouble();
    }
}
