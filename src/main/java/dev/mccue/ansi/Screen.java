package dev.mccue.ansi;

public final class Screen {
    public static final String ERASE_SCREEN_BELOW = "\\x1b[J";
    public static final String ERASE_SCREEN_ABOVE = "\\x1b[1J";
    public static final String ERASE_ENTIRE_SCREEN = "\\x1b[2J";
    public static final String ERASE_ENTIRE_DISPLAY = "\\x1b[3J";

    public static final String ERASE_LINE_RIGHT = "\\x1b[K";
    public static final String ERASE_LINE_LEFT = "\\x1b[1K";
    public static final String ERASE_ENTIRE_LINE = "\\x1b[2K";

    public static String setWindowTitle(String title) {
        return "\\x1b]2;" + title + "\\x07";
    }
}
