package dev.mccue.ansi.parser;

import org.intellij.lang.annotations.MagicConstant;

public final class Parser {
    // Params contains the raw parameters of the sequence.
    // These parameters used when constructing CSI and DCS sequences.
    int[] Params;

    // Data contains the raw data of the sequence.
    // These data used when constructing OSC, DCS, SOS, PM, and APC sequences.
    byte[] Data;

    // DataLen keeps track of the length of the data buffer.
    // If DataLen is -1, the data buffer is unlimited and will grow as needed.
    // Otherwise, DataLen is limited by the size of the Data buffer.
    int DataLen;

    // ParamsLen keeps track of the number of parameters.
    // This is limited by the size of the Params buffer.
    //
    // This is also used when collecting UTF-8 runes to keep track of the
    // number of rune bytes collected.
    int ParamsLen;

    // Cmd contains the raw command along with the private marker and
    // intermediate bytes of the sequence.
    // The first lower byte contains the command byte, the next byte contains
    // the private marker, and the next byte contains the intermediate byte.
    //
    // This is also used when collecting UTF-8 runes treating it as a slice of
    // 4 bytes.
    int Cmd;

    // State is the current state of the parser.
    @MagicConstant(flagsFromClass = State.class) byte State;

    Parser(int paramsSize, int dataSize) {
        if (dataSize <= 0) {
            dataSize = 0;
            this.DataLen = -1;
        }
        this.Params = new int[paramsSize];
        this.Data = new byte[dataSize];
    }
}
