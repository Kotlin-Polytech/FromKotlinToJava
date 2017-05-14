/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part4.deposit.changing;

/**
 *
 * @author Eugene Pychkine
 */
@SuppressWarnings("WeakerAccess")
public class Deposit {
    private int balance;
    public Deposit( int startBalance ) {
        balance = startBalance;
    }
    synchronized public int getBalance() throws InterruptedException {
        // Задержка имитирует возможную трудоемкость процедуры записи
        Thread.sleep(10);
        return balance;
    }
    synchronized public int changeBalance(int change) throws InterruptedException {
        // Задержка имитирует возможную трудоемкость процедуры записи
        Thread.sleep(10);
        return balance += change;
    }
}
