package pl.szblewski.service;

import org.junit.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.szblewski.domain.Bill;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatcher.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

public class BillTest {
    private BillService billService;
    private BillDataAccess billDataAccess;

    @Before
    public void setUp(){

        billService = Mockito.mock(BillService.class);
        billDataAccess = new BillDataAccess(billService);//Mockito.mock(BillDataAccess.class);
    }

    @Test
    public void searchByPaternTest(){
        List<Bill> bills = new ArrayList<Bill>();
        BillOrganiser bo = new BillOrganiser();
        bo.createBill(new Bill("Tesco",22));
        bo.createBill(new Bill("Komfort",33));
        bo.createBill(new Bill("Gama",2));
        bills = bo.getBills();

        when(billService.getBills()).thenReturn(bills);

        String pat = "\\w{5}";
        List<Bill> results = billDataAccess.findBillsByRegex(pat);

        Assert.assertNotNull(results);
        Assert.assertEquals(2,results.size());
    }





}
