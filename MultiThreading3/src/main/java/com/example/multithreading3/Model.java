package com.example.multithreading3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class Model {
    public boolean running;
    public progressBarThread barThread;

    private ProgressBar progress;

    Model(ProgressBar progress) {
        this.progress = progress;
        barThread = new progressBarThread();
        barThread.setUpdater(new Updatable() {
            public void update(double value) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        progress.setProgress(value);
                    }
                });
            }
        });
        barThread.start();
        running = true;
    }
}
interface  Updatable{
    public void update(double value);
}

class progressBarThread extends Thread {
    Updatable updater;
    public void setUpdater(Updatable updater) {this.updater = updater;}
    private boolean isPaused = false;
    public boolean getIsPaused() { return isPaused; }
    public void setIsPaused(boolean isPaused) {  this.isPaused = isPaused; }
    private boolean rerun = false;
    public boolean getRerun() { return rerun; }
    public void setRerun(boolean rerun) { this.rerun = rerun;}
    private double percents = 0;

    @Override
    public void run() {
            try {
                for (int i = 0; i < 1000; i++) {
                    if(rerun) {
                        i = 0;
                        rerun = false;
                    }
                    percents = i * 0.001;

                    updater.update(percents);
                    Thread.sleep(20);
                    if(isPaused) {
                        try {
                            synchronized (System.out) {
                                System.out.wait();
                            }
                        } catch (InterruptedException e) {
                            this.interrupt();
                        }
                    }
                }

            }
            catch (InterruptedException e) {
                this.interrupt();
            }
    }
}

