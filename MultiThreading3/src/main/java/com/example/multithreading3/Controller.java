package com.example.multithreading3;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class Controller {
    @FXML
    public void initialize() {
        pauseButton.setDisable(true);
        stopButton.setDisable(true);
    }

    @FXML
    private ProgressBar progress;
    @FXML
    private Button pauseButton;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    private boolean running = false;
    Model model;
    @FXML
    protected void startClicked()
    {
        if (!running) {
            startButton.setText("Перезапуск");
            model = new Model(progress);
            running = true;
            pauseButton.setDisable(false);
            stopButton.setDisable(false);
        }

        else
            model.barThread.setRerun(true);
    }
    @FXML
    protected void stopClicked()
    {
        model.barThread.interrupt();
        progress.setProgress(0);
        running = false;
        pauseButton.setDisable(true);
        stopButton.setDisable(true);
    }
    @FXML
    protected void pauseClicked()
    {
        if (model.barThread.getIsPaused()) {
            model.barThread.setIsPaused(false);
            pauseButton.setText("Пауза");
            synchronized (System.out) {
                System.out.notify();
            }
            stopButton.setDisable(false);
            startButton.setDisable(false);

        }
        else {
            model.barThread.setIsPaused(true);
            pauseButton.setText("Продолжить");
            startButton.setDisable(true);
            stopButton.setDisable(true);
        }
    }
}