package pl.szblewski.service;

import org.junit.runner.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.szblewski.domain.Bill;
import org.junit.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatcher.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class BillTest {

    @Mock

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
        //Mockito.verify(billService,times(1)).deleteBill(billToDelete1);

    }


    @Test
    public void deleteBillTest(){
        BillOrganiser bo = new BillOrganiser();
        Bill billToDelete1 = new Bill("Object1",1);
        Bill billToDelete2 = new Bill("Object2",1);
        bo.createBill(billToDelete1);
        bo.createBill(billToDelete2);



        Mockito.verify(billService,times(1)).deleteBill(billToDelete1);
        verify(billService,times(1)).deleteBill(billToDelete2);

    }

    @Test
    public void deleteBillTestException(){
        Bill bill = new Bill("Test",22);
        List<Bill> bills = new ArrayList<Bill>();
        bills.add(bill);
        willThrow(NoSuchElementException.class).given(billService).deleteBill(any(Bill.class));

        billDataAccess.deleteBills(bills);


    }







}
