package yazlab1.proje2;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.swing.JLabel;

public class exit implements Runnable {

    static BlockingQueue<ArrayList<Integer>> Qe1; //= new ArrayBlockingQueue<>(500);
    static BlockingQueue<ArrayList<Integer>> Qe2; //= new ArrayBlockingQueue<>(500);
    static BlockingQueue<ArrayList<Integer>> Qe3; //= new ArrayBlockingQueue<>(500);
    static BlockingQueue<ArrayList<Integer>> Qe4; //= new ArrayBlockingQueue<>(500);
    int randomP, randomF;
    static int QwaitingN1; // Kuyruktaki insan sayısı
    static int QwaitingN2;
    static int QwaitingN3;
    static int QwaitingN4;

    public exit(BlockingQueue Qe1, BlockingQueue Qe2, BlockingQueue Qe3, BlockingQueue Qe4) {
        this.Qe1 = Qe1;
        this.Qe2 = Qe2;
        this.Qe3 = Qe3;
        this.Qe4 = Qe4;
    }

    @Override
    public void run() {
        int a = 0;
        try {
            while (true) {
                ArrayList<Integer> randomPF1 = new ArrayList<Integer>();
                ArrayList<Integer> randomPF2 = new ArrayList<Integer>();
                ArrayList<Integer> randomPF3 = new ArrayList<Integer>();
                ArrayList<Integer> randomPF4 = new ArrayList<Integer>();
                randomP = (1 + (int) (Math.random() * 5));
                System.out.println("Random exit person: " + randomP);
                randomF = (1 + (int) (Math.random() * 4));
                System.out.println("Random exit floor: " + randomF);
                if (randomF == 1 && floorPeople.p1 >= 5) {
                    randomPF1.add(randomP);
                    randomPF1.add(randomF);
                    System.out.println("Added to exit queue 1: [" + randomP + ", " + randomF + "]");
                    QwaitingN1 = QwaitingN1 + randomP;
                    this.Qe1.add(randomPF1);
                    System.out.println(this.Qe1);
                }

                if (randomF == 2 && floorPeople.p2 >= 5) {
                    randomPF2.add(randomP);
                    randomPF2.add(randomF);
                    System.out.println("Added to exit queue 2: [" + randomP + ", " + randomF + "]");
                    QwaitingN2 = QwaitingN2 + randomP;
                    this.Qe2.add(randomPF2);
                    System.out.println(this.Qe2);
                }
                if (randomF == 3 && floorPeople.p3 >= 5) {
                    randomPF3.add(randomP);
                    randomPF3.add(randomF);
                    System.out.println("Added to exit queue 3: [" + randomP + ", " + randomF + "]");
                    QwaitingN3 = QwaitingN3 + randomP;
                    this.Qe3.add(randomPF3);
                    System.out.println(this.Qe3);
                }
                if (randomF == 4 && floorPeople.p4 >= 5) {
                    randomPF4.add(randomP);
                    randomPF4.add(randomF);
                    System.out.println("Added to exit queue 4: [" + randomP + ", " + randomF + "]");
                    QwaitingN4 = QwaitingN4 + randomP;
                    this.Qe4.add(randomPF4);
                    System.out.println(this.Qe4);
                }
                Thread.sleep(1000);
                System.out.println("waited\n\n");
                a++;

            }
        } catch (Exception e) {
            System.out.println("The error was caught (exit)" + e);
        }

    }

    /*public void threadSleep() {
        try {
            Thread.sleep(1000);
            System.out.println("waited\n\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

 /* public void addQueueExit() throws InterruptedException {
        Thread tE = new Thread();
        tE.start();
        int a = 0;
        while (a <= 5) {

            System.out.println("number of people exit: " + personNumber());
            System.out.println("floor number exit : " + floorNumber());
            if (randomF == 1) {
                this.Qe1.put(randomP);
                this.Qe1.put(0);
                System.out.println("Added to exit queue 1 exit : [" + randomP + ", " + 0 + "]");

                System.out.println(this.Qe1);
            }
            if (randomF == 2) {
                this.Qe2.put(randomP);
                this.Qe2.put(0);
                System.out.println("Added to exit queue 2: [" + randomP + ", " + 0 + "]");
                System.out.println(this.Qe2);
            }
            if (randomF == 3) {
                this.Qe3.put(randomP);
                this.Qe3.put(0);
                System.out.println("Added to exit queue 3: [" + randomP + ", " + 0 + "]");
                System.out.println(this.Qe3);
            }
            if (randomF == 4) {
                this.Qe4.put(randomP);
                this.Qe4.put(0);
                System.out.println("Added to exit queue 4: [" + randomP + ", " + 0 + "]");
                System.out.println(this.Qe4);
            }

            threadSleep();
            /*System.out.println("number of people: " + personNumber());
            System.out.println("floor number: " + floorNumber());
            this.Q1.put(randomP);
            QwaitingN=QwaitingN+randomP;
            System.out.println("Kuyrukta bekleyen toplam adet: "+QwaitingN);
            this.Q1.put(randomF);
            System.out.println("Added to queue: [" + randomP + ", " + randomF + "]");
            System.out.println(this.Q1);
            threadSleep();
            a++;

        }
    }*/
}
