package p01_microsystem.tests;

import p01_microsystem.main.Brand;
import p01_microsystem.main.Computer;
import p01_microsystem.main.Microsystem;
import p01_microsystem.main.MicrosystemImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CTest13 {
    private Microsystem microsystems;

    @Before
    public void init() {
        this.microsystems = new MicrosystemImpl();
    }

    @Test
    public void remove_with_brand_should_not_contain_removed_computers() {


        Computer computer = new Computer(1, Brand.DELL, 2300, 15.6, "grey");
        Computer computer2 = new Computer(3, Brand.DELL, 2300, 15.6, "grey");
        Computer computer3 = new Computer(4, Brand.DELL, 2300, 15.6, "grey");
        Computer computer4 = new Computer(5, Brand.ACER, 2300, 15.6, "grey");

        //Act
        microsystems.createComputer(computer);
        microsystems.createComputer(computer2);
        microsystems.createComputer(computer3);
        microsystems.createComputer(computer4);
        microsystems.removeWithBrand(Brand.DELL);


        //Assert

        Assert.assertFalse(microsystems.contains(1));
        Assert.assertFalse(microsystems.contains(3));
        Assert.assertFalse(microsystems.contains(4));
    }
}
