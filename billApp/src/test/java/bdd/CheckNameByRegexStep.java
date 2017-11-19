package bdd;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.mockito.internal.util.collections.Iterables;
import pl.szblewski.domain.Bill;
import pl.szblewski.service.BillDataAccess;
import pl.szblewski.service.BillOrganiser;
import pl.szblewski.service.BillService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;


public class CheckNameByRegexStep {
    private List<Bill> listOfBills;
    private BillDataAccess billDataAccess;
    private BillService billService;
    private BillOrganiser billOrganiser;
    private String pattern;

    @Given("^There are this bill in memory$")
    public void there_are_this_bill_in_memory(List<Bill> bills) {
        listOfBills = bills;
        assertNotNull(listOfBills);
        this.pattern = pattern;

        billService = new BillOrganiser();

        for (Bill bill : listOfBills) {
            billService.createBill(bill);
        }

        billDataAccess = new BillDataAccess(billService);
    }

    @Given("^Regex patern$")
    public void regex_patern(List<String> paterns) {
        pattern = paterns.get(0);//"\\w{5}";
    }

    @When("^Searching$")
    public void searching() throws Throwable {

        listOfBills = billDataAccess.findBillsByRegex(pattern);
    }

    @Then("^Bills found by pattern$")
    public void bills_found_by_pattern(List<Bill> bills) throws Throwable {
        assertEquals(bills.size(),listOfBills.size());
        assertEquals(bills.get(0).getName(),listOfBills.get(0).getName());
        assertEquals(bills.get(1).getName(),listOfBills.get(1).getName());
        assertEquals(bills.get(2).getName(),listOfBills.get(2).getName());
    }
}











    //@When("^Deleting$")

