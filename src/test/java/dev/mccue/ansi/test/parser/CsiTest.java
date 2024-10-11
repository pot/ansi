package dev.mccue.ansi.test.parser;

import dev.mccue.ansi.parser.CsiSequence;
import dev.mccue.ansi.parser.Seq;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsiTest {
    @Test
    public void testMarker() {
        assertEquals(CsiSequence.of(0).marker(), 0);
        assertEquals(CsiSequence.of('u' | '?'<< Seq.MarkerShift).marker(), '?');
    }
}
