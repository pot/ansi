import java.nio.charset.Charset;

module dev.mccue.ansi {
    requires dev.mccue.wcwidth;
    requires org.jetbrains.annotations;
    requires dev.mccue.color.terminal;

    exports dev.mccue.ansi;
    exports dev.mccue.ansi.parser;

}