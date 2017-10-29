package pl.szblewski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.NoSuchElementException;

import pl.szblewski.domain.Bill;

public class BillDataAccess {

    private BillService service;

    public BillDataAccess(BillService service){
        this.service = service;
    }


    public List<Bill> findBillsByRegex (String regex){
        Pattern pat = Pattern.compile(regex);

        List<Bill> mathes = new ArrayList<Bill>();
        List<Bill> listOfBills = service.getBills();

        for (Bill bill: listOfBills){
            if (pat.matcher(bill.getName()).find()){
                mathes.add(bill);
            }
        }

        return mathes;
    }

    public void deleteBills(List<Bill> bills)throws NoSuchElementException{

            for (Bill bill: bills){
                service.deleteBill(bill);
            }



    }


}
