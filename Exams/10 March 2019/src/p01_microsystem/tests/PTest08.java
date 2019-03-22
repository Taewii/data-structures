package p01_microsystem.tests;

import p01_microsystem.main.Brand;
import p01_microsystem.main.Computer;
import p01_microsystem.main.Microsystem;
import p01_microsystem.main.MicrosystemImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PTest08 {

    private Microsystem microsystem;
    private Generator generator;

    @Before
    public void init() {
        this.microsystem = new MicrosystemImpl();
        this.generator = new Generator();
    }

    @Test
    public void remove_with_100000_entries() throws IllegalArgumentException {

        List<Computer> computers = this.generator.generateComputers(100000);

        computers.forEach(comp -> this.microsystem.createComputer(comp));
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            this.microsystem.remove(i);

        }
        long stop = System.currentTimeMillis();

        long elapsedTime = stop - start;

//        throw new IllegalArgumentException("time: " + elapsedTime);
        Assert.assertTrue(elapsedTime <= 50);
    }

    static class Generator {
        private static final Random RANDOM = new Random();
        private static final String[] COLORS = {"white", "gray", "black", "red", "pink", "orange", "yellow", "green", "magenta", "cyan", "blue"};
        private double[] SCREEN_SIZE = {15.6, 14, 13.2, 16, 13.3};

        List<Computer> generateComputers(int count) {
            List<Computer> computers = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
                String color = COLORS[Math.abs(RANDOM.nextInt()) % COLORS.length];
                Brand brand = Brand.values()[Math.abs(RANDOM.nextInt()) % Brand.values().length];
                double price = 1000D + (5000D - 1000D) * RANDOM.nextDouble();
                double screenSize = SCREEN_SIZE[Math.abs(RANDOM.nextInt()) % SCREEN_SIZE.length];
                Computer computer = new Computer(i, brand, price, screenSize, color);
                computers.add(computer);
            }
            Collections.shuffle(computers);
            return computers;
        }
    }


}
