/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part4.deposit.waiting;

/**
 *
 * @author Eugene Pychkine
 */
@SuppressWarnings("WeakerAccess")
public class PutMoneyThread extends Thread {
    private final Deposit deposit;
    private final int change;
    PutMoneyThread( String name, Deposit deposit, int change ) {
        super( name );
        this.deposit = deposit;
        this.change = change;
    }
    @Override
    public void run() {
        try {
            int balance = deposit.getBalance();
            System.out.println( getName() + ": balance before transaction: " +
                                balance );
            System.out.println( getName() + ": balance after transaction: " +
                    deposit.putMoney(change));
        }
        catch( InterruptedException e ) {
            System.out.println( "Interrupting " + getName());
        }
    }
}
