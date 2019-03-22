import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p02_vani_planning.main.Agency;
import p02_vani_planning.main.AgencyImpl;
import p02_vani_planning.main.Department;
import p02_vani_planning.main.Invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class PTest05 {

    private Generator generator;
    private Agency agency;

    @Before
    public void init() {
        this.agency = new AgencyImpl();
        this.generator = new Generator();
    }

    @Test
    public void contains_100000_elements() {

        List<Invoice> invoices = this.generator.generateInvoices(100000);

        invoices.forEach(i -> this.agency.create(i));
        long start = System.currentTimeMillis();
        invoices.forEach(x -> Assert.assertTrue(this.agency.contains(x.getNumber())));
        long stop = System.currentTimeMillis();

        long elapsedTime = stop - start;
        // CHECK judge time
//   throw new IllegalArgumentException("Time: " + elapsedTime);

        Assert.assertTrue(elapsedTime <= 25);

    }

    static class Generator {
        private static final Random RANDOM = new Random();
        private static final String[] COMPANIES = {"HRS", "SoftUni", "Expedia", "SBTech", "Codexio", "VMWare", "Musala", "Chaos Group", "PaySafe", "Motion", "Locktrip"};

        List<Invoice> generateInvoices(int count) {
            List<Invoice> generated = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String uuid = UUID.randomUUID().toString();
                String company = COMPANIES[Math.abs(RANDOM.nextInt()) % COMPANIES.length];
                double subTotal = RANDOM.nextDouble() * Math.abs(RANDOM.nextInt());
                Department department = Department.values()[Math.abs(RANDOM.nextInt()) % Department.values().length];
                LocalDate firstDate = LocalDate.ofEpochDay(getRandomDay(2010, 2015));
                LocalDate secondDate = LocalDate.ofEpochDay(getRandomDay(2018, 2020));
                generated.add(new Invoice(uuid, company, subTotal, department, firstDate, secondDate));

            }
            return generated;
        }

        private long getRandomDay(int min, int max) {
            int minDay = (int) LocalDate.of(min, 1, 1).toEpochDay();
            int maxDay = (int) LocalDate.of(max, 1, 1).toEpochDay();
            return minDay + RANDOM.nextInt(maxDay - minDay);
        }
    }
}
