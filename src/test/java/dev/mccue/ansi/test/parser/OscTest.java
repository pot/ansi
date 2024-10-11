package dev.mccue.ansi.test.parser;

import dev.mccue.ansi.ControlCharacters;
import dev.mccue.ansi.parser.OscSequence;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OscTest {
    @Test
    public void oscTest() {
        assertEquals(OscSequence.of("0;".getBytes(StandardCharsets.UTF_8), 0).toString(),
                ControlCharacters.ESC + "]0;" + ControlCharacters.BEL);
        assertEquals(OscSequence.of("1;hello".getBytes(StandardCharsets.UTF_8), 1).toString(),
                ControlCharacters.ESC + "]1;hello" + ControlCharacters.BEL);
    }
}
