/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part4.deposit.waiting;

/**
 *
 * @author Eugene Pychkine
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deposit deposit = new Deposit( 300 );
        GetMoneyThread client = new GetMoneyThread( "Client", deposit, 700 );
        PutMoneyThread bank = new PutMoneyThread( "Bank", deposit, 500 );       
        
        try {
            client.start();
            Thread.sleep(1000);
            bank.start();
            bank.join();
            client.join();
            int balance = deposit.getBalance();
            System.out.println( "Balance at termination: " + balance );
        }
        catch( InterruptedException e ) {
            System.out.println( "Enexpected");
        }        
    }
}
