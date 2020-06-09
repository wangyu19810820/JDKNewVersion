package jdk12;

import org.junit.Test;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatTest {

    @Test
    public void testCompactNumberFormat1() {
        var numberFormat = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.SHORT);
        System.out.println(numberFormat.format(1_0000));
        System.out.println(numberFormat.format(1_9200));
        System.out.println(numberFormat.format(1_000_000));
        System.out.println(numberFormat.format(1L << 30));
        System.out.println(numberFormat.format(1L << 40));
        System.out.println(numberFormat.format(1L << 50));
    }

    @Test
    public void testCompactNumberFormat2() {
        var numberFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        System.out.println(numberFormat.format(1_0000));
        System.out.println(numberFormat.format(1_9200));
        System.out.println(numberFormat.format(1_000_000));
        System.out.println(numberFormat.format(1L << 30));
        System.out.println(numberFormat.format(1L << 40));
        System.out.println(numberFormat.format(1L << 50));
    }
}
