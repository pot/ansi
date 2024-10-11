package dev.mccue.ansi.parser;

import org.intellij.lang.annotations.MagicConstant;

final class State {
    private State() {}

    static final byte GroundState = 0;
    static final byte CsiEntryState = 1;
    static final byte CsiIntermediateState = 2;
    static final byte CsiParamState = 3;
    static final byte DcsEntryState = 4;
    static final byte DcsIntermediateState = 5;
    static final byte DcsParamState = 6;
    static final byte DcsStringState = 7;
    static final byte EscapeState = 8;
    static final byte EscapeIntermediateState = 9;
    static final byte OscStringState = 10;
    static final byte SosStringState = 11;
    static final byte PmStringState = 12;
    static final byte ApcStringState = 13;
    // Utf8State is not part of the DEC ANSI standard. It is used to handle
    // UTF-8 sequences.
    static final byte Utf8State = 14;

    static final String[] names = {
            "GroundState",
            "CsiEntryState",
            "CsiIntermediateState",
            "CsiParamState",
            "DcsEntryState",
            "DcsIntermediateState",
            "DcsParamState",
            "DcsStringState",
            "EscapeState",
            "EscapeIntermediateState",
            "OscStringState",
            "SosStringState",
            "PmStringState",
            "ApcStringState",
            "Utf8State"
    };

    static String name(
            @MagicConstant(flagsFromClass = State.class)
            byte value
    ) {
        return names[value];
    }
}
