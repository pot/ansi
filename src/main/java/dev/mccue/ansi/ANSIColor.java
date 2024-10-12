package dev.mccue.ansi;

import dev.mccue.color.RGB255;
import dev.mccue.color.sRGB;

/// A 4-bit ansi color code.
public enum ANSIColor implements TerminalColor {
    // Black is the ANSI black color.
    BLACK(0x000000),

    // Red is the ANSI red color.
    RED(0x800000),

    // Green is the ANSI green color.
    GREEN(0x008000),

    // Yellow is the ANSI yellow color.
    YELLOW(0x808000),

    // Blue is the ANSI blue color.
    BLUE(0x000080),

    // Magenta is the ANSI magenta color.
    MAGENTA(0x800080),

    // Cyan is the ANSI cyan color.
    CYAN(0x008080),

    // White is the ANSI white color.
    WHITE(0xc0c0c0),

    // BrightBlack is the ANSI bright black color.
    BRIGHT_BLACK(0x808080),

    // BrightRed is the ANSI bright red color.
    BRIGHT_RED(0xff0000),

    // BrightGreen is the ANSI bright green color.
    BRIGHT_GREEN(0x00ff00),

    // BrightYellow is the ANSI bright yellow color.
    BRIGHT_YELLOW(0xffff00),

    // BrightBlue is the ANSI bright blue color.
    BRIGHT_BLUE(0x0000ff),

    // BrightMagenta is the ANSI bright magenta color.
    BRIGHT_MAGENTA(0xff00ff),

    // BrightCyan is the ANSI bright cyan color.
    BRIGHT_CYAN(0x00ffff),

    // BrightWhite is the ANSI bright white color.
    BRIGHT_WHITE(0xffffff);

    final int value;

    ANSIColor(int value) {
        this.value = value;
    }

    @Override
    public sRGB sRGB() {
        return RGB255().sRGB();
    }

    @Override
    public RGB255 RGB255() {
        return new RGB255(value);
    }
}
