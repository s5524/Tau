package pl.szblewski.service;


import pl.szblewski.domain.Bill;
import java.util.List;
import java.util.NoSuchElementException;


public interface BillService {

    void createBill(Bill bill);
    List<Bill> getBills();
    void updateBill(Bill bill) throws NoSuchElementException;
    void deleteBill(Bill bill) throws NoSuchElementException;


}
