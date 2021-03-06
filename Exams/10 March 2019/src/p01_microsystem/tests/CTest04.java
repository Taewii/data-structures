package p01_microsystem.tests;

import p01_microsystem.main.Brand;
import p01_microsystem.main.Computer;
import p01_microsystem.main.Microsystem;
import p01_microsystem.main.MicrosystemImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CTest04 {
    private Microsystem microsystem;

    @Before
    public void init() {
        this.microsystem = new MicrosystemImpl();
    }

    @Test
    public void count_should_work_correctly() {

        Computer computer1 = new Computer(2, Brand.ACER, 1120, 15.6, "grey");
        Computer computer = new Computer(1, Brand.DELL, 2300, 15.6, "grey");
        Computer computer2 = new Computer(5, Brand.HP, 2400, 13.6, "red");


        this.microsystem.createComputer(computer);
        this.microsystem.createComputer(computer1);
        this.microsystem.createComputer(computer2);

        final int expectedCount = 3;
        final int actualCount = this.microsystem.count();

        Assert.assertEquals("Incorrect count", expectedCount, actualCount);
    }
}
