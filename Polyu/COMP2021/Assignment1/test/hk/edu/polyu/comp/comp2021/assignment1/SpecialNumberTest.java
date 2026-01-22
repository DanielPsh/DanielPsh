package hk.edu.polyu.comp.comp2021.assignment1;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpecialNumberTest {

    @Test
    public void test1() {
        assertTrue(SpecialNumber.isSpecial(30));
    }

    @Test
    public void test2() {
        assertFalse(SpecialNumber.isSpecial(210));
    }

}
