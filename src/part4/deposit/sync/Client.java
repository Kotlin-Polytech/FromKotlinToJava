/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part4.deposit.sync;

import part4.deposit.naive.Deposit;

/**
 *
 * @author Eugene Pychkine
 */
@SuppressWarnings("WeakerAccess")
public class Client extends Thread {
    private final Deposit deposit;
    private final int change;
    Client( Deposit deposit, int change ) {
        super( "Bank client" );
        this.deposit = deposit;
        this.change = change;
    }
    @Override
    public void run() {
        try {
            synchronized(deposit) {
                int balance = deposit.getBalance();
                System.out.println( "Client: balance before transaction: " +
                                    balance );
                
                balance += change;
                deposit.setBalance(balance);
                System.out.println( "Client: balance: " + balance );
            }
        }
        catch( InterruptedException e ) {
            System.out.println( "Interrupting client thread");
        }
    }
}
