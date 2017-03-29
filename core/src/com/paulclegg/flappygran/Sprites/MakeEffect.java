package com.paulclegg.flappygran.Sprites;

/**
 * Created by cle99 on 28/03/2017.
 */

public class MakeEffect {
//    private String whatToDo;

//    public MakeEffect () {
//        whatToDo = "NONE";
//    }
//
//    public MakeEffect(String effect) {
//        whatToDo = effect;
//        if (whatToDo == "GHOST") {
//            ghost();
//        } else if (whatToDo == "GRAVITY") {
//            gravity();
//        } else if (whatToDo == "SLIDE") {
//            slide();
//        } else {
//            normal();
//        }
//
//    }

    public static void ghost() {
        Gran.gravityInversion = false;
        Gran.isGhost = true;
        Tube.sliding = false;
    }

    public static void gravity() {
        Gran.gravityInversion = true;
        Gran.isGhost = false;
        Tube.sliding = false;
    }

    public static void slide() {
        Gran.gravityInversion = false;
        Gran.isGhost = false;
        Tube.sliding = true;
    }

    public static void normal() {
        Gran.gravityInversion = false;
        Gran.isGhost = false;
        Tube.sliding = false;
    }
}
