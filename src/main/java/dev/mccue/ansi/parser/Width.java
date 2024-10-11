package dev.mccue.ansi.parser;

import dev.mccue.ansi.Style;
import dev.mccue.ansi.TrueColor;
import dev.mccue.color.Color;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public final class Width {
    private Width() {}

    static int utf8ByteLen(byte by) {
        var b = Byte.toUnsignedInt(by);
        if (b <= 0b0111_1111) { // 0x00-0x7F
            return 1;
        } else if (b >= 0b1100_0000 && b <= 0b1101_1111) { // 0xC0-0xDF
            return 2;
        } else if (b >= 0b1110_0000 && b <= 0b1110_1111) { // 0xE0-0xEF
            return 3;
        } else if (b >= 0b1111_0000 && b <= 0b1111_0111) { // 0xF0-0xF7
            return 4;
        }
        return -1;
    }
    public static String strip(String str) {
        var s = str.getBytes(StandardCharsets.UTF_8);
        var buf = new ByteArrayOutputStream();
        int ri = 0;
        int rw = 0;
        var pstate = State.GroundState;

        for (int i = 0; i < s.length; i++) {
            System.out.println(State.name(pstate));
            if (pstate == State.Utf8State) {
                // During this state, collect rw bytes to form a valid rune in the
                // buffer. After getting all the rune bytes into the buffer,
                // transition to GroundState and reset the counters.
                buf.write(s[i]);
                ri++;
                if (ri < rw) {
                    continue;
                }
                pstate = State.GroundState;
                ri = 0;
                rw = 0;
                continue;
            }

            var transition = TransitionTable.table.Transition(pstate, s[i]);
            var state = transition.state();
            var action = transition.action();
            switch (action) {
                case Action.CollectAction -> {
                    if (state == State.Utf8State) {
                        // This action happens when we transition to the Utf8State.
                        rw = utf8ByteLen(s[i]);
                        buf.write(s[i]);
                        ri++;
                    }
                }

                case Action.PrintAction, Action.ExecuteAction -> {
                    // collects printable ASCII and non-printable characters
                    buf.write(s[i]);
                }

            }

            pstate = state;
        }

        return buf.toString(StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        System.out.println(

        );
    }
}
