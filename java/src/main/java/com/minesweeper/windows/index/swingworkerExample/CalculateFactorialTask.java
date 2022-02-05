package main.windows.index.swingworkerExample;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CalculateFactorialTask extends SwingWorker<Long, Integer> {

    private final int number;
    private final JProgressBar progressBar;
    private final JLabel lResult;
    private final JButton bCalculate;


    public CalculateFactorialTask(int number, JProgressBar progressBar, JLabel lResult, JButton bCalculate) {
        this.number = number;
        this.progressBar = progressBar;
        this.lResult = lResult;
        this.bCalculate = bCalculate;

    }

    @Override
    protected Long doInBackground() {
        long factorial = 1L;

        for (short i = 1; i <= number; i++) {
            if (Long.MAX_VALUE / i < factorial) {
                //skip if overflow
                factorial = 0L;
                break;
            }
            factorial = factorial * i;

            //slow down the thread
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //publish progress update
            publish(Math.round((i * 100) / number));
        }

        return factorial;
    }

    @Override
    protected void process(List<Integer> chunks) {
        for (int progress : chunks) {
            progressBar.setValue(progress);
        }
    }

    @Override
    protected void done() {

        try {
            lResult.setText(String.valueOf(this.get()));
        } catch (InterruptedException | ExecutionException ex) {
            //cannot be interrupted in this example
        }

        //enable the button
        bCalculate.setEnabled(true);

    }
}