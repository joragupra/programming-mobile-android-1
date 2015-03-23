package com.joragupra.modernart.modernart;

import android.graphics.Color;

public class ModernArtColorInverter {

    public static int invert(ModernArtColor modernArtColor, int progress) {

        int originalColor = Color.parseColor(modernArtColor.getRgbCode());
        int invertedColor = (0x00FFFFFF - (originalColor | 0xFF000000)) |
                (originalColor & 0xFF000000);

        int origR = Color.red(originalColor);// (originalColor >> 16 ) & 0x000000FF;
        int origG = Color.green(originalColor);// ( originalColor >> 8 ) & 0x000000FF;
        int origB = Color.blue(originalColor);// originalColor & 0x000000FF;

        int invR = Color.red(invertedColor); // ( invertedColor >> 16 ) & 0x000000FF;
        int invG = Color.green(invertedColor); // ( invertedColor >> 8 ) & 0x000000FF;
        int invB = Color.blue(invertedColor); // invertedColor & 0x000000FF;


        return Color.rgb(
                (int) (origR + (invR - origR) * (progress / 100f)),
                (int) (origG + (invG - origG) * (progress / 100f)),
                (int) (origB + (invB - origB) * (progress / 100f)));
    }

}
