package test;

import org.junit.Test;
import logic.NumberConverter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.* ;

/**
 * Created with IntelliJ IDEA.
 * User: lorenzo
 * Date: 24/11/12
 * Time: 09:56
 * To change this template use File | Settings | File Templates.
 */
public class NumberConverterTest {

    public List<Integer> list(int...elems) {
        List <Integer> out = new ArrayList<Integer>();
        for (int elem:elems)
            out.add(elem);
        return out;
    }

    @Test
    public void testList() {
        assertArrayEquals(list(1,2,3).toArray(),new Integer[]{1,2,3});
        assertArrayEquals(list(0,0,3).toArray(),new Integer[]{0,0,3});
        assertArrayEquals(list(1).toArray(),new Integer[]{1});
    }

    @Test
    public void testClean() {
        assertArrayEquals(NumberConverter.clean(new ArrayList<Integer>()).toArray(),new Integer[]{0});
        assertArrayEquals(NumberConverter.clean(list(0)).toArray(),new Integer[]{0});
        assertArrayEquals(NumberConverter.clean(list(0,0,0,1,2,3)).toArray(),new Integer[]{1,2,3});
        assertArrayEquals(NumberConverter.clean(list(0,1,0,1,2,3)).toArray(),new Integer[]{1,0,1,2,3});
    }

    @Test
    public void testDecompose() {
        assertArrayEquals(list(66).toArray(),NumberConverter.decompose(66).toArray());
        assertArrayEquals(list(1,0).toArray(),NumberConverter.decompose(100).toArray());
        assertArrayEquals(list(7,89).toArray(),NumberConverter.decompose(789).toArray());
        assertArrayEquals(list(56,7,89).toArray(),NumberConverter.decompose(56789).toArray());
        assertArrayEquals(list(34,56,7,89).toArray(),NumberConverter.decompose(3456789).toArray());
        assertArrayEquals(list(2,34,00,7,89).toArray(),NumberConverter.decompose(23400789).toArray());
        assertArrayEquals(list(1,0,34,00,7,89).toArray(),NumberConverter.decompose(103400789).toArray());
        assertArrayEquals(list(0).toArray(),NumberConverter.decompose(000000).toArray());
    }

    @Test
    public void testSingleToText() {
        assertEquals(NumberConverter.singleToText(0),"zero");
        assertEquals(NumberConverter.singleToText(5),"five");
        assertEquals(NumberConverter.singleToText(9),"nine");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidTestSingleToText() {
        NumberConverter.singleToText(10);
        NumberConverter.singleToText(-1);
    }

    @Test
    public void testDoubleToText() {
        assertEquals("zero",NumberConverter.doubleToText(00));
        assertEquals("five",NumberConverter.doubleToText(05));
        assertEquals("ten",NumberConverter.doubleToText(10));
        assertEquals("eleven",NumberConverter.doubleToText(11));
        assertEquals("fifteen",NumberConverter.doubleToText(15));
        assertEquals("twenty",NumberConverter.doubleToText(20));
        assertEquals("twenty five",NumberConverter.doubleToText(25));
        assertEquals("sixty",NumberConverter.doubleToText(60));
        assertEquals("eighty nine",NumberConverter.doubleToText(89));
        assertEquals("ninety three",NumberConverter.doubleToText(93));
        assertEquals("ninety nine",NumberConverter.doubleToText(99));
    }

    @Test
    public void testMultipleDigits() {
        assertEquals("zero",NumberConverter.multipleToText(0));
        assertEquals("one",NumberConverter.multipleToText(1));
        assertEquals("five",NumberConverter.multipleToText(5));
        assertEquals("eleven",NumberConverter.multipleToText(11));
        assertEquals("eighty nine",NumberConverter.multipleToText(89));
        assertEquals("ninety three",NumberConverter.multipleToText(93));
        assertEquals("ninety nine",NumberConverter.multipleToText(99));
        assertEquals("one hundred",NumberConverter.multipleToText(100));
        assertEquals("one hundred and five",NumberConverter.multipleToText(105));
        assertEquals("one hundred and eleven",NumberConverter.multipleToText(111));
        assertEquals("nine hundred and sixty five",NumberConverter.multipleToText(965));
        assertEquals("nine hundred and ninety",NumberConverter.multipleToText(990));
        assertEquals("six thousand three hundred and ninety four",NumberConverter.multipleToText(6394)); // 6,394
        assertEquals("twenty three thousand four hundred and eleven",NumberConverter.multipleToText(23411)); // 23,411
        assertEquals("ninety thousand",NumberConverter.multipleToText(90000)); // 90,000
        assertEquals("six hundred and ninety thousand and twenty four",NumberConverter.multipleToText(690024)); // 690,024
        assertEquals("six million ninety thousand and three",NumberConverter.multipleToText(6090003)); // 6,090,003
        assertEquals("twenty million ninety thousand and three",NumberConverter.multipleToText(20090003)); // 20,090,003
        assertEquals("three hundred twenty four million ninety thousand and three",NumberConverter.multipleToText(324090003)); // 324,090,003
        assertEquals("nine hundred two million ninety thousand and three",NumberConverter.multipleToText(902090003)); // 902,090,003
        assertEquals("nine hundred ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine",NumberConverter.multipleToText(999999999)); // 999,999,999
    }

    @Test
    public void testRequestedNumbers() {
        assertEquals("one",NumberConverter.multipleToText(1));
        assertEquals("twenty one",NumberConverter.multipleToText(21));
        assertEquals("one hundred and five",NumberConverter.multipleToText(105));
        assertEquals("fifty six million nine hundred and forty five thousand seven hundred and eighty one",NumberConverter.multipleToText(56945781)); // 56,945,781
        assertEquals("nine hundred ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine",NumberConverter.multipleToText(999999999)); // 999,999,999
    }


}
