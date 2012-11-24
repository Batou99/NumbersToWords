package test;

import logic.SuperInteger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: lorenzo
 * Date: 24/11/12
 */
public class SuperNumberTest {
    @Test
    public void testRequestedNumbers() {
        assertEquals("one", new SuperInteger(1).toText());
        assertEquals("twenty one", new SuperInteger(21).toText());
        assertEquals("one hundred and five",new SuperInteger(105).toText());
        assertEquals("fifty six million nine hundred and forty five thousand seven hundred and eighty one",new SuperInteger(56945781).toText()); // 56,945,781
        assertEquals("nine hundred ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine",new SuperInteger(999999999).toText()); // 999,999,999
    }
}
