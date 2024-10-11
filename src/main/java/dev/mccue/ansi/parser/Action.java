package dev.mccue.ansi.parser;

import org.intellij.lang.annotations.MagicConstant;

final class Action {
    private Action() {}

    static final byte NoneAction = 0;
    static final byte ClearAction = 1;
    static final byte CollectAction = 2;
    static final byte MarkerAction = 3;
    static final byte DispatchAction = 4;
    static final byte ExecuteAction = 5;
    static final byte StartAction = 6; // Start of a data string
    static final byte PutAction = 7;   // Put into the data string
    static final byte ParamAction = 8;
    static final byte PrintAction = 9;
    static final byte IgnoreAction = NoneAction;

    static @MagicConstant(flagsFromClass = Action.class) byte validate(byte value) {
        if (value > PrintAction || value < NoneAction) {
            throw new IllegalStateException("Invalid action: " + value);
        }
        return value;
    }

    static final String[] names = {
            "NoneAction",
            "ClearAction",
            "CollectAction",
            "MarkerAction",
            "DispatchAction",
            "ExecuteAction",
            "StartAction",
            "PutAction",
            "ParamAction",
            "PrintAction",
    };

    static String name(
            @MagicConstant(flagsFromClass = Action.class) byte value
    ) {
        return names[value];
    }
}
