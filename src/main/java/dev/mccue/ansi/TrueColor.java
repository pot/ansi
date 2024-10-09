package dev.mccue.ansi;


import dev.mccue.color.Color;
import dev.mccue.color.RGB255;
import dev.mccue.color.sRGB;

import java.util.Objects;

import static dev.mccue.ansi.ANSI256Color.ansiHex;

public final class TrueColor implements TerminalColor {
    private final RGB255 value;

    TrueColor(RGB255 value) {
        this.value = value;
    }

    public static TrueColor of(int value) {
        return new TrueColor(Color.RGB255(value));
    }

    public static TrueColor of(Color value) {
        return new TrueColor(value.RGB255());
    }

    @Override
    public sRGB sRGB() {
        return RGB255().sRGB();
    }

    @Override
    public RGB255 RGB255() {
        return this.value;
    }

    public String hex() {
        return value.hex();
    }

    ANSIColor closestAnsi() {
        int r = 0;
        double md = Double.MAX_VALUE;

        var values = ANSIColor.values();
        for (int i = 0; i <= values.length; i++) {
            var hb = ansiHex.get(i);
            var d = value.distanceHSLuv(hb);

            if (d < md) {
                md = d;
                r = i;
            }
        }

        return values[r];
    }

    ANSI256Color closestAnsi256() {
        int r = 0;
        double md = Double.MAX_VALUE;

        for (int i = 0; i <= 256; i++) {
            var hb = ansiHex.get(i);
            var d = value.distanceHSLuv(hb);

            if (d < md) {
                md = d;
                r = i;
            }
        }

        return ANSI256Color.of(r);
    }

    @Override
    public String toString() {
        return "TrueColor[" +
                "R=" + value.R() +
                ", G=" + value.G() +
                ", B=" + value.B() +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TrueColor trueColor
                && Objects.equals(value, trueColor.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
