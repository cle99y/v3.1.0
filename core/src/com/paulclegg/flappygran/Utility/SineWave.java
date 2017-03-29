package com.paulclegg.flappygran.Utility;

import java.util.Random;

/**
 * Created by cle99 on 29/03/2017.
 */

public class SineWave {

    private Random random = new Random();
    private float delta;
    public double seed;
    private double sinVal;
    private static final double dT = 2.0E-03;
    private double gradient;

    public SineWave() {

        seed = random.nextInt(1000) / 1000f * 2f * Math.PI;
        gradient = Math.cos(seed);


    }

    public double getSeed() {
        return seed;
    }

    public double getGradient() {
        return gradient;
    }

    public double getSin() {

        seed += dT;
        return Math.sin(seed);

    }

}
