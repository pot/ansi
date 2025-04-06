package dev.mccue.ansi;

public final class Cursor {
    public static final String SAVE_CURSOR = "\\x1b7";
    public static final String RESTORE_CURSOR = "\\x1b8";
    public static final String CURSOR_HOME_POSITION = "";
    public static final String RESTORE_CURRENT_CURSOR_POSITION = "\\x1b[u";
    public static final String REVERSE_INDEX = "\\x1bM";

    public static String cursorUp(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "A";
    }

    public String cursorDown(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "B";
    }

    public String cursorForward(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "C";
    }

    public String cursorBackward(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "D";
    }

    public String cursorNextLine(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "E";
    }

    public static String cursorPrevLine(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "F";
    }

    public static String cursorHorizontalAbsolute(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "G";
    }

    public static String cursorPosition(int col, int row) {
        if (col <= 0 && row <= 0) {
            return CURSOR_HOME_POSITION;
        }

        String rowStr = row > 1 ? Integer.toString(row) : "";
        String colStr = col > 1 ? Integer.toString(col) : "";
        return "\\x1b[" + rowStr + ";" + colStr + "H";
    }

    public static String cursorHorizontalForwardTab(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "I";
    }

    public static String eraseCharacter(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "X";
    }

    public static String cursorBackwardTab(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "Z";
    }

    public static String verticalPositionAbsolute(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "d";
    }

    public static String verticalPositionRelative(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "e";
    }

    public static String setCursorStyle(int style) {
        String styleStr = style > 1 ? Integer.toString(style) : "";
        return "\\x1b[" + styleStr + "q";
    }

    public static String setPointerShape(String shape) {
        return "\\x1b]22;" + shape + "\\x07";
    }

    public static String horizontalPositionAbsolute(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "`";
    }

    public static String horizontalPositionRelative(int n) {
        String length = n > 1 ? Integer.toString(n) : "";
        return "\\x1b[" + length + "a";
    }
}
