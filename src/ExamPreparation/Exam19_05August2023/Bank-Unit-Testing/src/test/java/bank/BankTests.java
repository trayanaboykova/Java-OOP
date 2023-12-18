package bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BankTests {
    private Bank bank;
    private static final Client client = new Client("Pesho");

    @Before
    public void prepareBank() {
        bank = new Bank("DSK", 2);
        bank.addClient(client);
    }

    @Test
    public void testConstructorCreateCorrectObject() {
        Assert.assertEquals("DSK", bank.getName());
        Assert.assertEquals(2, bank.getCapacity());
        Assert.assertEquals(1, bank.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorThrowExceptionNullParam() {
        bank = new Bank(null, 2);

    }

    @Test(expected = NullPointerException.class)
    public void testSetNameOnWhitespaces() {
        new Bank("      ", 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowExceptionNegativeCapacity() {
        bank = new Bank("DSK", -5);
    }

    @Test
    public void testConstructorWithCapacityZero() {
        bank = new Bank("DSK", 0);
    }

    @Test
    public void testCorrectAddClient() {
        bank.addClient(new Client("Asen"));
        Assert.assertEquals(bank.getCount(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientThrowException() {
        bank.addClient(new Client("Asen"));
        bank.addClient(new Client("Error"));
    }

    @Test
    public void testClientSizeReturnCorrect() {
        bank.addClient(new Client("Asen"));
        Assert.assertEquals(2, bank.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveClientFromBankThrowExceptionNullParam() {
        bank.removeClient(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveUnknownClient() {
        bank.removeClient("Gosho");
    }

    @Test
    public void testIfSuccessRemoveClient() {
        bank.removeClient(client.getName());
    }

    @Test
    public void testGetCountAfterRemoving() {
        bank.removeClient(client.getName());
        assertEquals(0, bank.getCount());
    }

    @Test
    public void testIfSuccessLoanWithdrawClient() {
        bank.loanWithdrawal("Pesho");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoanWithdrawThrowExceptionNoClient() {
        bank.loanWithdrawal("Gosho");
    }

    @Test
    public void testStatistics() {
        bank.statistics().equals(String.format("The client %s is at the %s bank!", "DSK", "Pesho"));
    }

    @Test
    public void testIfApprovedForLoanReturnTrue() {
        assertFalse(client.isApprovedForLoan());
    }

    @Test
    public void testGetClientReturnCorrectName() {
        Assert.assertEquals(client.getName(), "Pesho");
    }

    @Test
    public void testIfApprovedForLoan() {
        client.setApprovedForLoan(false);
    }

    @Test
    public void testClientConstructor() {
        Assert.assertEquals("Pesho", client.getName());
    }
}
