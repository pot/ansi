package dev.mccue.ansi;

public final class Mode {
    public static final String SET_ALT_SCREEN_SAVE_CURSOR_MODE = "\\x1b[?1049h";
    public static final String RESET_ALT_SCREEN_SAVE_CURSOR_MODE = "\\x1b[?1049l";

    public static final String SET_TEXT_CURSOR_ENABLE_MODE = "\\x1b[?25h";
    public static final String RESET_TEXT_CURSOR_ENABLE_MODE = "\\x1b[?25l";

    public static final String SET_BUTTON_EVENT_MOUSE_MODE = "\\x1b[?1002h";
    public static final String RESET_BUTTON_EVENT_MOUSE_MODE = "\\x1b[?1002l";

    public static final String SET_ANY_EVENT_MOUSE_MODE = "\\x1b[?1003h";
    public static final String RESET_ANY_EVENT_MOUSE_MODE = "\\x1b[?1003l";

    public static final String SET_SGR_EXT_MOUSE_MODE = "\\x1b[?1006h";
    public static final String RESET_SGR_EXT_MOUSE_MODE = "\\x1b[?1006l";

    public static final String SET_BRACKETED_PASTE_MODE = "\\x1b[?2004h";
    public static final String RESET_BRACKETED_PASTE_MODE = "\\x1b[?2004l";

    public static final String SET_FOCUS_EVENT_MODE = "\\x1b[?1004h";
    public static final String RESET_FOCUS_EVENT_MODE = "\\x1b[?1004l";
}

