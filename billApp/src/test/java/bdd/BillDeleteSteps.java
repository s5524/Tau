package bdd;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.internal.util.collections.Iterables;
import pl.szblewski.domain.Bill;
import pl.szblewski.service.BillDataAccess;
import pl.szblewski.service.BillOrganiser;
import pl.szblewski.service.BillService;

import java.util.List;

import static org.junit.Assert.*;


public class BillDeleteSteps {
    private List<Bill> listOfBills;
    private BillDataAccess billDataAccess;
    private BillService billService;
    private BillOrganiser billOrganiser;


    @Given("^There are this bills in memory$")
    public void there_are_this_bills_in_memory(List<Bill> bills) {
        listOfBills = bills;
        assertNotNull(listOfBills);


       billService = new BillOrganiser();

        for ( Bill bill : listOfBills) {
            billService.createBill(bill);
        }

        billDataAccess = new BillDataAccess(billService);
    }

    @When("^Deleting$")
    public void deleting(List<Bill> bill) throws Throwable {

        List<Bill> billsToDelete = bill;
        for ( Bill bills : billsToDelete) {
            for (Bill billsInMemory : billService.getBills()){
                if (bills.getName()==billsInMemory.getName()&&bills.getPrice()==billsInMemory.getPrice()){
                    bills.setId(billsInMemory.getId());
                }
            }
        }
        assertNotNull(billsToDelete);
        billDataAccess.deleteBills(billsToDelete);
    }

    @Then("^Bills that should left in db$")
    public void bills_that_should_left_in_db(List<Bill> bill) throws Throwable {
        List<Bill> billsThatShouldLeft = bill;
        assertEquals(bill.size(),billService.getBills().size());
        assertEquals(bill.get(0).getName(),billService.getBills().get(0).getName());

    }

    @But("^Bills that should not left in db$")
    public void bills_that_should_not_left_in_db(List<Bill> bill) throws Throwable {
        List<Bill> billsThatShouldLeft = bill;
        assertNotEquals(bill.size(),billService.getBills().size());
        assertNotEquals(bill.get(0).getName(),billService.getBills().get(0).getName());
        assertNotEquals(bill.get(1).getName(),billService.getBills().get(0).getName());
        assertNotEquals(bill.get(2).getName(),billService.getBills().get(0).getName());
    }
}


