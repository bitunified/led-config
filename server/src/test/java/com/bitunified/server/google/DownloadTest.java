package com.bitunified.server.google;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;

public class DownloadTest {

    @Test
    public void test() throws IOException {
        Download download = new Download();
        OutputStream outputStream = download.getParserDataFile();
    }
}