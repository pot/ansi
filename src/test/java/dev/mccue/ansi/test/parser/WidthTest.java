package dev.mccue.ansi.test.parser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static dev.mccue.ansi.parser.Width.strip;
import static dev.mccue.ansi.parser.Width.width;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WidthTest {
    public record TestCase(String name, String input, String stripped, int width) {}

    public static List<TestCase> testCases() {
        return List.of(
                new TestCase("empty", "", "", 0),
                new TestCase("ascii", "hello", "hello", 5),
                new TestCase("emoji", "👋", "👋", 2),
                new TestCase("wideemoji", "🫧", "🫧", 2),
                new TestCase("combining", "a\u0300", "à", 1),
                new TestCase("control", "\u001b[31mhello\u001b[0m", "hello", 5),
                new TestCase("csi8", "\u009b38;5;1mhello\u009bm", "hello", 5),
                new TestCase("osc", "\u009d2;charmbracelet: ~/Source/bubbletea\u009c", "", 0),
                new TestCase("controlemoji", "\u001b[31m👋\u001b[0m", "👋", 2),
                new TestCase("oscwideemoji", "\u001b]2;title👨‍👩‍👦\u0007", "", 0),
                new TestCase("oscwideemoji", "\u001b[31m👨‍👩‍👦\u001b[m", "👨\u200d👩\u200d👦", 2),
                new TestCase("multiemojicsi", "👨‍👩‍👦\u009b38;5;1mhello\u009bm", "👨‍👩‍👦hello", 7),
                new TestCase("osc8eastasianlink", "\u009d8;id=1;https:example.com/\u009c打豆豆\u009d8;id=1;\u0007", "打豆豆", 6),
                new TestCase("dcsarabic", "\u001bP?123$pسلام\u001b\\اهلا", "اهلا", 4),
                new TestCase("newline", "hello\nworld", "hello\nworld", 10),
                new TestCase("tab", "hello\tworld", "hello\tworld", 10),
                new TestCase("controlnewline", "\u001b[31mhello\u001b[0m\nworld", "hello\nworld", 10),
                new TestCase("style", "\u001B[38;2;249;38;114mfoo", "foo", 3),
                new TestCase("unicode", "\u001b[35m“box”\u001b[0m", "“box”", 5),
                new TestCase("just_unicode", "Claire’s Boutique", "Claire’s Boutique", 17),
                new TestCase("unclosed_ansi", "Hey, \u001b[7m\n猴", "Hey, \n猴", 7),
                new TestCase("double_asian_runes", " 你\u001b[8m好.", " 你好.", 6)
        );

    }
    @ParameterizedTest
    @MethodSource("testCases")
    public void stripTest(TestCase testCase) {
        assertEquals(
                testCase.stripped,
                strip(testCase.input),
                testCase.name
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void widthTest(TestCase testCase) {
        assertEquals(
                testCase.width,
                width(testCase.input),
                testCase.name
        );
    }
}
