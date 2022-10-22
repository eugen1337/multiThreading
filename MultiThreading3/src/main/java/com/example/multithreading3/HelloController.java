package com.example.multithreading3;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class HelloController {

    public double percents = 0;
    @FXML
    private ProgressBar progress;
    @FXML
    private Button pauseButton;
    @FXML
    private Button startButton;

    class progressBarThread extends Thread {
        private boolean isPaused = false;
        private boolean rerun = false;

        @Override
        public void run() {
            synchronized (System.out) {
                    try {
                        for (int i = 0; i < 1000; i++) {
                            if(rerun) {
                                i = 0;
                                rerun = false;
                            }
                            percents = i * 0.001;
                            /*Platform.runLater(new Runnable() {
                                @Override
                                public void run() {*/
                                    progress.setProgress(percents);
                               /* }
                            });*/
                            Thread.sleep(20);
                            if(isPaused) {
                                try {
                                    synchronized (System.out) {
                                        System.out.wait();
                                    }
                                } catch (InterruptedException e) {
                                   // e.printStackTrace();
                                    System.out.println("EXCEPTION");
                                    barThread.interrupt();
                                }
                            }
                        }

                    }
                    catch (InterruptedException e) {
                       // e.printStackTrace();
                        System.out.println("EXCEPTION");
                        barThread.interrupt();
                    }
            }
        }
    }
    private progressBarThread barThread;
    private boolean running = false;
    @FXML
    protected void startClicked()
    {
        if(!running){
            barThread = new progressBarThread();
            barThread.start();
            running = true;
            startButton.setText("Перезапуск");
        }
        else
            barThread.rerun = true;
    }
    @FXML
    protected void stopClicked()
    {
        barThread.interrupt();
        progress.setProgress(0);
        running = false;
    }
    @FXML
    protected void pauseClicked()
    {
        if (barThread.isPaused) {
            barThread.isPaused = false;
            pauseButton.setText("Пауза");
            synchronized (System.out) {
                System.out.notify();
            }
        }
        else {
            barThread.isPaused = true;
            pauseButton.setText("Продолжить");
        }
    }
}