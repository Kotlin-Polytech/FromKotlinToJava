/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part4.deposit.naive;

/**
 *
 * @author Eugene Pychkine
 */
@SuppressWarnings("Duplicates")
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deposit deposit = new Deposit( 300 );
        Client client = new Client( deposit, -100 );
        Client bank = new Client( deposit, 1000 );
        
        bank.start();
        client.start();

        try {
            bank.join();
            client.join();
            int balance = deposit.getBalance();
            System.out.println( "Balance at termination: " + balance );
        }
        catch( InterruptedException e ) {
            System.out.println( "Unexpected thread interrupt!");
        }        
    }
}
