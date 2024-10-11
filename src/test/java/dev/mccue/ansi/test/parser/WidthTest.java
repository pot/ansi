package dev.mccue.ansi.test.parser;

import dev.mccue.ansi.Style;
import dev.mccue.ansi.TrueColor;
import dev.mccue.color.Color;
import org.junit.jupiter.api.Test;

import static dev.mccue.ansi.parser.Width.strip;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WidthTest {
    @Test
    public void basicStripTest() {
        assertEquals(
                "Hello",
                strip(Style.builder().backgroundColor(TrueColor.of(Color.RGB255(255, 0, 255)))
                        .apply("Hello"))
        );
    }
}
