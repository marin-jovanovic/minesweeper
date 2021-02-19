package main.windows.index.swingworkerExample;

import javax.swing.*;
import java.awt.*;

public class FactorialCalculatorFrame extends JFrame {

    /*
     * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
     *
     * Redistribution and use in source and binary forms, with or without
     * modification, are permitted provided that the following conditions
     * are met:
     *
     *   - Redistributions of source code must retain the above copyright
     *     notice, this list of conditions and the following disclaimer.
     *
     *   - Redistributions in binary form must reproduce the above copyright
     *     notice, this list of conditions and the following disclaimer in the
     *     documentation and/or other materials provided with the distribution.
     *
     *   - Neither the name of Oracle or the names of its
     *     contributors may be used to endorse or promote products derived
     *     from this software without specific prior written permission.
     *
     * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
     * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
     * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
     * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
     * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
     * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
     * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
     * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
     * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
     * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
     * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
     */

    private final JTextField tfNumber;
    private final JLabel lResult;
    private final JButton bCalculate;
    private final JProgressBar progressBar;

    public FactorialCalculatorFrame() {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        tfNumber = new JTextField();
        bCalculate = new JButton("start");
        progressBar = new JProgressBar();
        lResult = new JLabel();

        tfNumber.setColumns(10);

        bCalculate.addActionListener(actionEvent -> {
            try {
                int number = Integer.parseInt(tfNumber.getText());

                //reset GUI components
                progressBar.setValue(0);
                bCalculate.setEnabled(false);
                lResult.setText("");

                //schedule for execution on one of working threads
//                new CalculateFactorialTask(number).execute();
                new CalculateFactorialTask(number, progressBar, lResult, bCalculate).execute();
            } catch (NumberFormatException ex) {
                //do nothing
            }
        });

        panel.add(tfNumber);
        panel.add(bCalculate);
        panel.add(progressBar);
        panel.add(lResult);

        this.add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FactorialCalculatorFrame frame = new FactorialCalculatorFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("Factorial Calculator Demo");
            frame.setBounds(500, 500, 400, 140);
            frame.setVisible(true);
        });
    }

//    private class CalculateFactorialTask extends SwingWorker<Long, Integer> {
//
//        private final int number;
//
//        public CalculateFactorialTask(int number) {
//            this.number = number;
//        }
//
//        @Override
//        protected Long doInBackground() {
//            long factorial = 1L;
//
//            for (short i = 1; i <= number; i++) {
//                if (Long.MAX_VALUE / i < factorial) {
//                    //skip if overflow
//                    factorial = 0L;
//                    break;
//                }
//                factorial = factorial * i;
//
//                //slow down the thread
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                //publish progress update
//                publish(Math.round((i * 100) / number));
//            }
//
//            return factorial;
//        }
//
//        @Override
//        protected void process(List<Integer> chunks) {
//            for (int progress : chunks) {
//                progressBar.setValue(progress);
//            }
//        }
//
//        @Override
//        protected void done() {
//
//            try {
//                lResult.setText(String.valueOf(this.get()));
//            } catch (InterruptedException | ExecutionException ex) {
//                //cannot be interrupted in this example
//            }
//
//            //enable the button
//            bCalculate.setEnabled(true);
//
//        }
//    }
}
