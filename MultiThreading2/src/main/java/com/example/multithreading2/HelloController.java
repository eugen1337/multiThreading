package com.example.multithreading2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

class buttonRunnable implements Runnable{
    @Override
    public void run() {
        while(true) {}
    }
}
class buttonThread extends Thread{
    @Override
    public void run() {
        while(true) {}
    }
}

public class HelloController {
    // ЭТО ЧАСТЬ А)
/*
    @FXML
    public void onButtonClicked(ActionEvent e) {
        while(true) {}
    }
*/



    @FXML
    public void onButtonClicked(ActionEvent e) {
        buttonThread t1  = new buttonThread();
        t1.start();
        //Второй вариант создания потока
        //buttonRunnable t2  = new buttonRunnable();
        //new Thread(t2).start();
    }


}