package part4.digitalclock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("WeakerAccess")
public class PeriodicThread extends Thread {

    private final int period;

    private final ActionListener listener;

    public PeriodicThread(int period, ActionListener listener) {
        this.period = period;
        this.listener = listener;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(period);
            } catch (InterruptedException ex) {
                return;
            }
            if (listener != null)
                listener.actionPerformed(new ActionEvent(this, 0, "Periodic"));
        }
    }
}
