package sample;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {
    Controller ctrl;

    @Before
    public void setUp() throws Exception {
        ctrl = new Controller();
    }

    @Test
    public void testControllerTest () {
        assertEquals(true, ctrl.ControllerTest());
    }

}