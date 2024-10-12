package dev.mccue.ansi.test.parser;

import dev.mccue.ansi.parser.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    record TestDispatcher(ArrayList<TerminalSequence> dispatched) implements ParserDispatcher {
        TestDispatcher() {
            this(new ArrayList<>());
        }

        @Override
        public void accept(TerminalSequence terminalSequence) {
            System.out.println(terminalSequence.getClass());
            dispatched.add(terminalSequence);
        }
    }

    static Parser testParser() {
        return new Parser(16, 0);
    }

    public record TestCase(String name, byte[] bytes, List<TerminalSequence> sequences) {
    }

    public static List<TestCase> testCases() {
        return List.of(
                new TestCase(
                        "just_esc",
                        "\u001b".getBytes(StandardCharsets.UTF_8),
                        List.of(ControlCode.of(0x1b))
                ),
                new TestCase(
                        "double_esc",
                        "\u001b\u001b".getBytes(StandardCharsets.UTF_8),
                        List.of(ControlCode.of(0x1b), ControlCode.of(0x1b))
                ),
                new TestCase(
                        "esc_bracket",
                        "\u001b[".getBytes(StandardCharsets.UTF_8),
                        List.of(EscSequence.of('['))
                ),
                new TestCase(
                        "csi_rune_esc_bracket",
                        "\u001b[1;2;3mabc\u001b\u001bP".getBytes(StandardCharsets.UTF_8),
                        List.of(
                                CsiSequence.of('m', new int[] { 1, 2, 3 }),
                                Rune.of('a'),
                                Rune.of('b'),
                                Rune.of('c'),
                                ControlCode.of(0x1b),
                                EscSequence.of('P')
                        )
                ),
                new TestCase(
                        "csi plus text",
                        "Hello, \u001b[31mWorld!\u001b[0m".getBytes(StandardCharsets.UTF_8),
                        List.of(
                                Rune.of('H'),
                                Rune.of('e'),
                                Rune.of('l'),
                                Rune.of('l'),
                                Rune.of('o'),
                                Rune.of(','),
                                Rune.of(' '),
                                CsiSequence.of('m', new int[] { 31 }),
                                Rune.of('W'),
                                Rune.of('o'),
                                Rune.of('r'),
                                Rune.of('l'),
                                Rune.of('d'),
                                Rune.of('!'),
                                CsiSequence.of('m', new int[] { 0 })
                        )
                )
        );
    }


    @ParameterizedTest
    @MethodSource("testCases")
    public void testParse(TestCase testCase) {
        var parser = testParser();
        var dispatcher = new TestDispatcher();
        parser.parse(dispatcher, testCase.bytes);
        assertEquals(testCase.sequences, dispatcher.dispatched(), testCase.name);
    }
}
