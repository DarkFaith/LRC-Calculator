/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lrccalculator;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
public class LRCCalculator {

    /**
     * @param args the command line arguments
     */
    static byte[] bytes;
    public static void main(String[] args) {
        try {
            bytes = args[0].getBytes("US-ASCII");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LRCCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] lrc;
        lrc = calcLRC(bytes);
        System.out.println((char)lrc[0] + " " + (char)lrc[1]);
    }
        // Hexadecimal values for ASCII numbers from 0-9 and A-F
    private static final byte[] HEX_SYMBOLS =
    {
        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37,
        0x38, 0x39, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46
    };
    
    public static byte[] calcLRC(byte[] data)
    {
        byte lrc = 0;
        for (int i = 0; i < data.length; i++)
        {
            lrc = (byte) ((lrc + data[i]) & 0xFF);
        }
        lrc = (byte) (((lrc ^ 0xFF) + 1) & 0xFF);
        return charToHex2((char) lrc);
    }
    
    
    public static byte[] charToHex2(char asciiChar)
    {
        byte[] bytes = new byte[2];
        // Assuming bytes[0] will be sent first
        bytes[0] = HEX_SYMBOLS[(asciiChar & 0xF0) >> 4];
        bytes[1] = HEX_SYMBOLS[ asciiChar & 0x0F];
        return bytes;
    }
}
