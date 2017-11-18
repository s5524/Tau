package pl.szblewski.service;

import pl.szblewski.domain.Bill;

import java.util.*;

public class BillOrganiser implements BillService {

    private Map<Integer,Bill> mapDb = new TreeMap<Integer, Bill>();

    public void createBill(Bill bill)
    {
        int id = mapDb.size();
        bill.setId(id);
        mapDb.put(id,bill);
    }

    public List<Bill> getBills()
    {
        ArrayList<Bill> listOfAllBills = new ArrayList<Bill>(mapDb.values());
        return listOfAllBills;
    }

    public void updateBill(Bill bill) throws NoSuchElementException
    {
        if (mapDb.containsValue(bill)){
            mapDb.put(bill.getId(),bill);
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public void deleteBill(Bill bill) throws NoSuchElementException
    {

        if (mapDb.containsKey(bill.getId())){
            mapDb.remove(bill.getId());
        }
        else {
            throw new NoSuchElementException();
        }
    }
}
