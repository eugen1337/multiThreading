package com.example.multithreading3;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class Controller {

    @FXML
    private ProgressBar progress;
    @FXML
    private Button pauseButton;
    @FXML
    private Button startButton;


    private progressBarThread barThread;
    private boolean running = false;
    @FXML
    protected void startClicked()
    {
        if(!running){
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
            startButton.setText("Перезапуск");
        }
        else
            barThread.setRerun(true);
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
        if (barThread.getIsPaused()) {
            barThread.setIsPaused(false);
            pauseButton.setText("Пауза");
            synchronized (System.out) {
                System.out.notify();
            }
        }
        else {
            barThread.setIsPaused(true);
            pauseButton.setText("Продолжить");
        }
    }
}