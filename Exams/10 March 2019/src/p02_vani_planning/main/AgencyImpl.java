package p02_vani_planning.main;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class AgencyImpl implements Agency {

    private final Map<LocalDate, Map<String, Invoice>> byDate;
    private final Map<String, Invoice> byNumber;
    private final Set<String> paidInvoices;

    public AgencyImpl() {
        this.byNumber = new HashMap<>();
        this.paidInvoices = new HashSet<>();
        this.byDate = new HashMap<>();
    }

    @Override
    public void create(Invoice invoice) {
        if (this.byNumber.containsKey(invoice.getNumber())) {
            throw new IllegalArgumentException();
        }

        this.byNumber.put(invoice.getNumber(), invoice);

        this.byDate.putIfAbsent(invoice.getDueDate(), new HashMap<>());
        this.byDate.get(invoice.getDueDate()).put(invoice.getNumber(), invoice);
    }

    @Override
    public boolean contains(String number) {
        return this.byNumber.containsKey(number);
    }

    @Override
    public int count() {
        return this.byNumber.size();
    }

    @Override
    public void payInvoice(LocalDate dueDate) {
        if (!this.byDate.containsKey(dueDate)) {
            throw new IllegalArgumentException();
        }

        Collection<Invoice> invoices = this.byDate.get(dueDate).values();
        invoices.forEach(d -> {
            d.setSubtotal(0);
            this.paidInvoices.add(d.getNumber());
        });

    }

    @Override
    public void throwInvoice(String number) {
        if (!this.byNumber.containsKey(number)) {
            throw new IllegalArgumentException();
        }

        this.byDate.get(this.byNumber.remove(number).getDueDate()).remove(number);
    }

    @Override
    public void throwPayed() {
        this.paidInvoices.forEach(i -> this.byDate.get(this.byNumber.remove(i).getDueDate()).remove(i));
        this.paidInvoices.clear();
    }

    @Override
    public Iterable<Invoice> getAllInvoiceInPeriod(LocalDate startDate, LocalDate endDate) {
        return this.byNumber.values().stream()
                .filter(d -> d.getIssueDate().compareTo(startDate) >= 0 && d.getIssueDate().compareTo(endDate) <= 0)
                .sorted((a, b) -> {
                    int result = a.getIssueDate().compareTo(b.getIssueDate());

                    if (result == 0) {
                        return a.getDueDate().compareTo(b.getDueDate());
                    }

                    return result;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Invoice> searchByNumber(String number) {
        List<Invoice> invoices = this.byNumber.values().stream()
                .filter(i -> i.getNumber().contains(number))
                .collect(Collectors.toList());

        if (invoices.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return invoices;
    }

    @Override
    public Iterable<Invoice> throwInvoiceInPeriod(LocalDate startDate, LocalDate endDate) {
        List<Invoice> invoices = this.byNumber.values().stream()
                .filter(d -> d.getDueDate().compareTo(startDate) > 0 && d.getDueDate().compareTo(endDate) < 0)
                .collect(Collectors.toList());

        if (invoices.isEmpty()) {
            throw new IllegalArgumentException();
        }

        invoices.forEach(i -> {
            this.byNumber.remove(i.getNumber());
            this.byDate.get(i.getDueDate()).remove(i.getNumber());
        });
        return invoices;
    }

    @Override
    public Iterable<Invoice> getAllFromDepartment(Department department) {
        return this.byNumber.values().stream()
                .filter(i -> i.getDepartment().equals(department))
                .sorted((a, b) -> {
                    int result = Double.compare(b.getSubtotal(), a.getSubtotal());

                    if (result == 0) {
                        return a.getIssueDate().compareTo(b.getIssueDate());
                    }

                    return result;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Invoice> getAllByCompany(String companyName) {
        return this.byNumber.values().stream()
                .filter(i -> i.getCompanyName().equals(companyName))
                .sorted((a, b) -> b.getNumber().compareTo(a.getNumber()))
                .collect(Collectors.toList());
    }

    @Override
    public void extendDeadline(LocalDate endDate, int days) {
        if (!this.byDate.containsKey(endDate)) {
            throw new IllegalArgumentException();
        }

        List<Invoice> invoices = new ArrayList<>(this.byDate.get(endDate).values());
        invoices.forEach(i -> i.setDueDate(i.getDueDate().plus(Period.ofDays(days))));
    }
}
