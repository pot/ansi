package dev.mccue.ansi.parser;

import org.intellij.lang.annotations.MagicConstant;

record Transition(
        @MagicConstant(flagsFromClass = State.class) byte state,
        @MagicConstant(flagsFromClass = State.class) byte action
) {
}
