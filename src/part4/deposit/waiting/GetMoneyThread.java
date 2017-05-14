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
public class GetMoneyThread extends Thread {
    private final Deposit deposit;
    private final int change;
    GetMoneyThread( String name, Deposit deposit, int change ) {
        super( name );
        this.deposit = deposit;
        this.change = change;
    }
    @Override
    public void run() {
        try {
            System.out.println( getName() + ": balance before transaction: " +
                                deposit.getBalance() );
            System.out.println( getName() + ": money got: " +
                    deposit.getMoney(change));
            System.out.println( getName() + ": balance after transaction: " +
                                deposit.getBalance() );
        }
        catch( InterruptedException e ) {
            System.out.println( "Interrupting " + getName());
        }
    }
}
