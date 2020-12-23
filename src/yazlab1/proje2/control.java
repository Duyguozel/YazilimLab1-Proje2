package yazlab1.proje2;

import java.awt.Label;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class control implements Runnable {

    /*BlockingQueue<ArrayList<Integer>> elevQ2 = new ArrayBlockingQueue<ArrtorayList<Integer>>(500);
    elevator2 e2 = new elevator2(elevQ2);
    elevator3 e3 = new elevator3();
    elevator4 e4 = new elevator4();
    elevator5 e5 = new elevator5();

    Thread t2 = new Thread((Runnable) e2);5 
    Thread t3 = new Thread((Runnable) e3);
    Thread t4 = new Thread((Runnable) e4);
    Thread t5 = new Thread((Runnable) e5);*/
    Thread elev1;
    Thread elev2;
    Thread elev3;
    Thread elev4;
    Thread elev5;
    elevator1 e1;
    elevator2 e2;
    elevator3 e3;
    elevator4 e4;
    elevator5 e5;

    OutputFrame o = new OutputFrame();


    public control(Thread elev1, Thread elev2, Thread elev3, Thread elev4, Thread elev5, elevator1 e1, elevator2 e2, elevator3 e3, elevator4 e4, elevator5 e5) {

        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
        this.e5 = e5;
        this.elev1 = elev1;
        this.elev2 = elev2;
        this.elev3 = elev3;
        this.elev4 = elev4;
        this.elev5 = elev5;
    }

    @Override
    public void run() {

        int a = 0;
        int count2 = 0, count3 = 0, count4 = 0, count5 = 0;

        elev1.start();
        o.setVisible(true);

        while (true) {

            int sum = login.QwaitingN + exit.QwaitingN1 + exit.QwaitingN2 + exit.QwaitingN3 + exit.QwaitingN4;

            System.out.println("0. floor : queue : " + login.QwaitingN);
            System.out.println("1. floor : all : " + floorPeople.p1 + " queue :" + exit.QwaitingN1);
            System.out.println("2. floor : all : " + floorPeople.p2 + " queue :" + exit.QwaitingN2);
            System.out.println("3. floor : all : " + floorPeople.p3 + " queue :" + exit.QwaitingN3);
            System.out.println("4. floor : all : " + floorPeople.p4 + " queue :" + exit.QwaitingN4);

            try {
                Thread.sleep(200);
                System.out.println("\nWaiting Elevator 1 \n\n");
            } catch (InterruptedException ex) {
                Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (sum >= 20) {
                System.out.println("Asansör 2 çalışmalı");

                if (e2.a == false && count2 == 0) { // Asansör  ilk kez açılıyor.
                    elev2.start();
                    count2++;
                    this.e2.a = true;
                }

                if (count2 != 0 && e2.a == false) { // Daha önce açıldı , uykuya alındı ve uyanıyor.
                    elev2.resume();
                }

                try {
                    Thread.sleep(400);
                    System.out.println("\nWaiting Elevator 2 \n\n");
                } catch (InterruptedException ex) {
                    Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (sum >= 40) {
                System.out.println("Asansör 3 çalışmalı");
                if (e3.a == false && count3 == 0) { // Asansör  ilk kez açılıyor.
                    elev3.start();
                    count3++;
                    this.e3.a = true;
                }

                if (count3 != 0 && e3.a == false) { // Daha önce açıldı , uykuya alındı ve uyanıyor.
                    elev3.resume();
                }
                try {
                    Thread.sleep(600);
                    System.out.println("\nWaiting Elevator 3 \n\n");
                } catch (InterruptedException ex) {
                    Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (sum >= 60) {
                System.out.println("Asansör 4 çalışmalı");
                if (e4.a == false && count4 == 0) { // Asansör  ilk kez açılıyor.
                    elev4.start();
                    count4++;
                    this.e4.a = true;
                }

                if (count4 != 0 && e4.a == false) { // Daha önce açıldı , uykuya alındı ve uyanıyor.
                    elev4.resume();
                }
                try {
                    Thread.sleep(800);
                    System.out.println("\nWaiting Elevator 4 \n\n");
                } catch (InterruptedException ex) {
                    Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (sum >= 80) {
                System.out.println("Asansör 5 çalışmalı");
                if (e5.a == false && count5 == 0) { // Asansör  ilk kez açılıyor.
                    elev5.start();
                    count5++;
                    this.e5.a = true;
                }

                if (count5 != 0 && e5.a == false) { // Daha önce açıldı , uykuya alındı ve uyanıyor.
                    elev5.resume();
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("\nWaiting Elevator 5 \n\n");
                } catch (InterruptedException ex) {
                    Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (sum < 20 && e2.a == true) //Kuyruk sayısı 20 nin altına düşünce beklemeye alıyoruz
            {
                elev2.suspend();
                e2.a = false;
            }
            if (sum < 40 && e3.a == true) {
                elev3.suspend();
                e3.a = false;
            }
            if (sum < 60 && e4.a == true) {
                elev4.suspend();
                e4.a = false;
            }
            if (sum < 80 && e5.a == true) {
                elev5.suspend();
                e5.a = false;
            }

        }
    }

}
