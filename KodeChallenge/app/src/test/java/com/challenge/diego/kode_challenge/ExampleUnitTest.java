package com.challenge.diego.kode_challenge;

import com.challenge.diego.kode_challenge.model.Device;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void formatter_isCorrect() throws Exception {
        assertNotNull(new Device().dateFormatter("2018-09-08 00:05:29Z"));
    }
}