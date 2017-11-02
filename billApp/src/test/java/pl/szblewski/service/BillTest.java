package pl.szblewski.service;

import org.junit.runner.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.szblewski.domain.Bill;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@RunWith(MockitoJUnitRunner.class)
public class BillTest {

    @Mock

    private BillService billServiceMock;
    private BillDataAccess billDataAccess;

    @Before
    public void setUp(){

        billServiceMock = Mockito.mock(BillService.class);
        billDataAccess = new BillDataAccess(billServiceMock);//Mockito.mock(BillDataAccess.class);
    }

    @Test
    public void searchByPaternTest(){
        List<Bill> bills = new ArrayList<Bill>();
        Bill bill1 = new Bill("Tesco",2);
        Bill bill2 = new Bill("Gama",2);
        Bill bill3 = new Bill("Komfort",2);
        bills.add(bill1);
        bills.add(bill2);
        bills.add(bill3);

        when(billServiceMock.getBills()).thenReturn(bills);

        String pat = "\\w{5}";
        List<Bill> results = billDataAccess.findBillsByRegex(pat);
        Assert.assertNotNull(results);
        Assert.assertEquals(2,results.size());
    }


    @Test
    public void deleteBillTest(){
        Bill billToDelete1 = new Bill("Object1",1);
        Bill billToDelete2 = new Bill("Object2",1);

        List<Bill> billsToDellete = new ArrayList<Bill>();
        billsToDellete.add(billToDelete1);
        billsToDellete.add(billToDelete2);
        billDataAccess.deleteBills(billsToDellete);


        verify(billServiceMock,times(1)).deleteBill(billToDelete2);
        verify(billServiceMock,times(1)).deleteBill(billToDelete1);
    }

    @Test (expected = NoSuchElementException.class)
    public void deleteBillTestException(){
        Bill bill = new Bill("Test",22);
        List<Bill> billsToDellete = new ArrayList<Bill>();
        billsToDellete.add(bill);

        doThrow(new NoSuchElementException()).when(billServiceMock).deleteBill(bill);

        billDataAccess.deleteBills(billsToDellete);
    }
}
