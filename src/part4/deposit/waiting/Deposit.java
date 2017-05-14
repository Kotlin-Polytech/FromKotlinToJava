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
public class Deposit {
    private int balance;
    public Deposit( int startBalance ) {
        balance = startBalance;
    }
    public int getBalance() throws InterruptedException {
        // Задержка имитирует возможную трудоемкость процедуры записи
        Thread.sleep(10);
        return balance;
    }
    public synchronized int getMoney(int requested) throws InterruptedException {
        assert requested > 0:
            "Можно запросить только положительную сумму";
        // Задержка имитирует возможную трудоемкость процедуры записи
        Thread.sleep(10);
        if (requested > balance) {
            wait(5000);
            if (requested > balance) {
                int oldBalance = balance;
                balance = 0;
                return oldBalance;
            }
        }
        balance -= requested;
        return requested;
    }

    public synchronized int putMoney(int sum) throws InterruptedException {
        assert sum > 0 :
            "Можно добавить только положительную сумму";
        // Задержка имитирует возможную трудоемкость процедуры записи
        Thread.sleep(10);
        balance += sum;
        notifyAll();
        return balance;
    }
}
