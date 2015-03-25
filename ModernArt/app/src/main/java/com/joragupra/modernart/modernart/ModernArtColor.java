package com.joragupra.modernart.modernart;

public class ModernArtColor {

    private String rgbCode;

    public ModernArtColor(String rgbCode) {
        this.rgbCode = rgbCode;
    }

    public String getRgbCode() {
        return rgbCode;
    }

    public static final ModernArtColor WHITE = new ModernArtColor("#ffffff");
    public static final ModernArtColor BLACK = new ModernArtColor("#000000");
    public static final ModernArtColor LIGHT_GRAY = new ModernArtColor("#D3D3D3");

    public static final ModernArtColor[] availableColors = new ModernArtColor[] {
            new ModernArtColor("#334433"), new ModernArtColor("#eea333"), new ModernArtColor("#6699aa"), new ModernArtColor("#e676e3"), new ModernArtColor("#88aaaa"), new ModernArtColor("#aacccc"), new ModernArtColor("#54db54"), new ModernArtColor("#447799")
    };
}
