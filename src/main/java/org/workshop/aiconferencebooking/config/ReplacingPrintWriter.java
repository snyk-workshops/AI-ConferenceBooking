package org.workshop.aiconferencebooking.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;

public class ReplacingPrintWriter extends PrintWriter {

    public ReplacingPrintWriter(Writer out) {
        super(out);
    }

    public ReplacingPrintWriter(Writer out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public ReplacingPrintWriter(OutputStream out) {
        super(out);
    }

    public ReplacingPrintWriter(OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public ReplacingPrintWriter(OutputStream out, boolean autoFlush, Charset charset) {
        super(out, autoFlush, charset);
    }

    public ReplacingPrintWriter(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public ReplacingPrintWriter(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(fileName, csn);
    }

    public ReplacingPrintWriter(String fileName, Charset charset) throws IOException {
        super(fileName, charset);
    }

    public ReplacingPrintWriter(File file) throws FileNotFoundException {
        super(file);
    }

    public ReplacingPrintWriter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(file, csn);
    }

    public ReplacingPrintWriter(File file, Charset charset) throws IOException {
        super(file, charset);
    }

    @Override
    public void write(String s) {
        super.write(s
            .replaceAll("‛|’", "'").replaceAll("‟|”", "\"")
            .replaceAll("—", "-").replaceAll("…", "...")
        );
    }
}
