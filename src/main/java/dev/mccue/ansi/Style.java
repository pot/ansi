package dev.mccue.ansi;

import java.util.ArrayList;
import java.util.List;
import static dev.mccue.ansi.Constants.*;

public final class Style {
    final List<String> style;

    private Style(Builder builder) {
        this.style = List.copyOf(builder.style);
    }

    public static Builder builder() {
        return new Builder();
    }

    static final String ResetStyle = "\u001b[m";


    public String apply(String s) {
        return "\u001b[" + String.join(";", style) + "m" + s + ResetStyle;
    }


    public static final class Builder {
        final ArrayList<String> style = new ArrayList<>();

        // Reset appends the reset style attribute to the style.
        public Builder reset() {
            style.add(resetAttr);
            return this;
        }

        // Bold appends the bold style attribute to the style.
        public Builder bold() {
            style.add(boldAttr);
            return this;
        }

        // Faint appends the faint style attribute to the style.
        public Builder faint() {
            style.add(faintAttr);
            return this;
        }

        // Italic appends the italic style attribute to the style.
        public Builder italic() {
            style.add(italicAttr);
            return this;
        }

        // Underline appends the underline style attribute to the style.
        public Builder underline() {
            style.add(underlineAttr);
            return this;
        }

        public Builder noUnderline() {
            style.add(noUnderlineAttr);
            return this;
        }

        static final String doubleUnderlineStyle = "4:2";
        static final String curlyUnderlineStyle = "4:3";
        static final String dottedUnderlineStyle = "4:4";
        static final String dashedUnderlineStyle = "4:5";

        public Builder doubleUnderline() {
            style.add(doubleUnderlineStyle);
            return this;
        }

        public Builder curlyUnderline() {
            style.add(curlyUnderlineStyle);
            return this;
        }

        public Builder dottedUnderline() {
            style.add(dottedUnderlineStyle);
            return this;
        }

        public Builder dashedUnderline() {
            style.add(dashedUnderlineStyle);
            return this;
        }

        public Builder slowBlink() {
            style.add(slowBlinkAttr);
            return this;
        }

        public Builder rapidBlink() {
            style.add(rapidBlinkAttr);
            return this;
        }

        public Builder reverse() {
            style.add(reverseAttr);
            return this;
        }

        public Builder conceal() {
            style.add(concealAttr);
            return this;
        }

        public Builder strikethrough() {
            style.add(strikethroughAttr);
            return this;
        }

        public Builder noBold() {
            style.add(noBoldAttr);
            return this;
        }

        public Builder normalIntensity() {
            style.add(normalIntensityAttr);
            return this;
        }

        public Builder noItalic() {
            style.add(noItalicAttr);
            return this;
        }

        public Builder noBlink() {
            style.add(noBlinkAttr);
            return this;
        }

        public Builder noReverse() {
            style.add(noReverseAttr);
            return this;
        }

        public Builder noConceal() {
            style.add(noConcealAttr);
            return this;
        }

        public Builder noStrikethrough() {
            style.add(noStrikethroughAttr);
            return this;
        }

        public Builder defaultForegroundColor() {
            style.add(defaultForegroundColorAttr);
            return this;
        }

        public Builder defaultBackgroundColor() {
            style.add(defaultBackgroundColorAttr);
            return this;
        }

        public Builder defaultUnderlineColor() {
            style.add(defaultBackgroundColorAttr);
            return this;
        }

        public Builder foregroundColor(TerminalColor terminalColor) {
            style.add(foregroundColorString(terminalColor));
            return this;
        }

        public Builder backgroundColor(TerminalColor terminalColor) {
            style.add(backgroundColorString(terminalColor));
            return this;
        }

        public Builder underlineColor(TerminalColor terminalColor) {
            style.add(underlineColorString(terminalColor));
            return this;
        }

        private String foregroundColorString(
                TerminalColor terminalColor
        ) {
            switch (terminalColor) {
                case ANSIColor ansiColor -> {
                    return switch (ansiColor) {
                        case BLACK -> blackForegroundColorAttr;
                        case RED -> redForegroundColorAttr;
                        case GREEN -> greenForegroundColorAttr;
                        case YELLOW -> yellowForegroundColorAttr;
                        case BLUE -> blueForegroundColorAttr;
                        case MAGENTA -> magentaForegroundColorAttr;
                        case CYAN -> cyanForegroundColorAttr;
                        case WHITE -> whiteForegroundColorAttr;
                        case BRIGHT_BLACK -> brightBlackForegroundColorAttr;
                        case BRIGHT_RED -> brightRedForegroundColorAttr;
                        case BRIGHT_GREEN -> brightGreenForegroundColorAttr;
                        case BRIGHT_YELLOW -> brightYellowForegroundColorAttr;
                        case BRIGHT_BLUE -> brightBlueForegroundColorAttr;
                        case BRIGHT_MAGENTA -> brightMagentaForegroundColorAttr;
                        case BRIGHT_CYAN -> brightCyanForegroundColorAttr;
                        case BRIGHT_WHITE -> brightWhiteForegroundColorAttr;
                    };
                }
                case ANSI256Color ansi256Color -> {
                    return "38;5;" + ansi256Color.value();
                }
                case TrueColor trueColor -> {
                    var rgb = trueColor.RGB255();
                    return "38;2;" + rgb.R() + ";" + rgb.G() + ";" + rgb.B();
                }
            }
        }

        private String backgroundColorString(
                TerminalColor terminalColor
        ) {
            switch (terminalColor) {
                case ANSIColor ansiColor -> {
                    return switch (ansiColor) {
                        case BLACK -> blackBackgroundColorAttr;
                        case RED -> redBackgroundColorAttr;
                        case GREEN -> greenBackgroundColorAttr;
                        case YELLOW -> yellowBackgroundColorAttr;
                        case BLUE -> blueBackgroundColorAttr;
                        case MAGENTA -> magentaBackgroundColorAttr;
                        case CYAN -> cyanBackgroundColorAttr;
                        case WHITE -> whiteBackgroundColorAttr;
                        case BRIGHT_BLACK -> brightBlackBackgroundColorAttr;
                        case BRIGHT_RED -> brightRedBackgroundColorAttr;
                        case BRIGHT_GREEN -> brightGreenBackgroundColorAttr;
                        case BRIGHT_YELLOW -> brightYellowBackgroundColorAttr;
                        case BRIGHT_BLUE -> brightBlueBackgroundColorAttr;
                        case BRIGHT_MAGENTA -> brightMagentaBackgroundColorAttr;
                        case BRIGHT_CYAN -> brightCyanBackgroundColorAttr;
                        case BRIGHT_WHITE -> brightWhiteBackgroundColorAttr;
                    };
                }
                case ANSI256Color ansi256Color -> {
                    return "48;5;" + ansi256Color.value();
                }
                case TrueColor trueColor -> {
                    var rgb = trueColor.RGB255();
                    return "48;2;" + rgb.R() + ";" + rgb.G() + ";" + rgb.B();
                }
            }
        }

        private String underlineColorString(
                TerminalColor terminalColor
        ) {
            switch (terminalColor) {
                case ANSIColor ansiColor -> {
                    return "58;5;" + ansiColor.ordinal();
                }
                case ANSI256Color ansi256Color -> {
                    return "58;5;" + ansi256Color.value();
                }
                case TrueColor trueColor -> {
                    var rgb = trueColor.RGB255();
                    return "58;2;" + rgb.R() + ";" + rgb.G() + ";" + rgb.B();
                }
            }
        }

        public Style build() {
            return new Style(this);
        }

        public String apply(String s) {
            return build().apply(s);
        }
    }

}
