package dev.mccue.ansi;

import dev.mccue.color.Color;

/// Represents a color suitable for display
/// on a terminal.
///
/// ANSI (including ANSI256) and 24-bit "true colors" fall under this category.
public sealed interface TerminalColor
        extends Color
        permits ANSIColor, ANSI256Color, TrueColor {
}
