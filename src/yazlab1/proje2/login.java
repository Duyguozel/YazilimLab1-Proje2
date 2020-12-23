package yazlab1.proje2;

import java.awt.Window;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.swing.JLabel;

public class login implements Runnable {

    static BlockingQueue<ArrayList<Integer>> Q1;
    int randomF;
    static int QwaitingN, randomP; 
 
    public login(BlockingQueue Q1) {
        this.Q1 = Q1;
    }

    @Override
    public void run() {
        OutputFrame o = new OutputFrame();
       
        int a = 0;
        try {
            while (true) {
                ArrayList<Integer> randomPF = new ArrayList<Integer>();
                randomP = (1 + (int) (Math.random() * 10));
                System.out.println("Random Kişi sayısı (login) : " + randomP);
                randomPF.add(randomP);
                QwaitingN = QwaitingN + randomP;
                randomF = (1 + (int) (Math.random() * 4));
                System.out.println("Random kat sayısı (login) : " + randomF);
                randomPF.add(randomF);
                this.Q1.add(randomPF);// giriş kuyruğu
                System.out.println("KEEP ArrayList (login) : " + Q1);
                
                Thread.sleep(500);
                a++;
               
            }

        } catch (Exception e) {
            System.out.println("The error was caught (login ) " + e);
        }
    }
}
