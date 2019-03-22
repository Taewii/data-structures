package p01_microsystem.tests;

import p01_microsystem.main.Brand;
import p01_microsystem.main.Computer;
import p01_microsystem.main.Microsystem;
import p01_microsystem.main.MicrosystemImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.StreamSupport;

public class CTest26 {
    private Microsystem microsystems;

    @Before
    public void init() {
        this.microsystems = new MicrosystemImpl();
    }

    @Test
    public void getInRangePrice_should_return_correct_count() {

        Computer computer = new Computer(7, Brand.DELL, 2300, 15.6, "grey");
        Computer computer2 = new Computer(3, Brand.DELL, 2200, 15.6, "blue");
        Computer computer3 = new Computer(6, Brand.DELL, 2800, 15.6, "grey");
        Computer computer4 = new Computer(5, Brand.ACER, 2300, 15.6, "grey");


        microsystems.createComputer(computer);
        microsystems.createComputer(computer2);
        microsystems.createComputer(computer3);
        microsystems.createComputer(computer4);

        int actualComputers = (int) StreamSupport.stream(microsystems.getInRangePrice(2200, 2300).spliterator(), false).
                count();

        Assert.assertEquals("Incorrect count", 3, actualComputers);
    }
}
