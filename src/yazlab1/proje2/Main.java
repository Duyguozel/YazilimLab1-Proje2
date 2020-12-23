package yazlab1.proje2;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<ArrayList<Integer>> Q1 = new ArrayBlockingQueue<ArrayList<Integer>>(500);
        BlockingQueue<ArrayList<Integer>> Qe1 = new ArrayBlockingQueue<ArrayList<Integer>>(500);
        BlockingQueue<ArrayList<Integer>> Qe2 = new ArrayBlockingQueue<ArrayList<Integer>>(500);
        BlockingQueue<ArrayList<Integer>> Qe3 = new ArrayBlockingQueue<ArrayList<Integer>>(500);
        BlockingQueue<ArrayList<Integer>> Qe4 = new ArrayBlockingQueue<ArrayList<Integer>>(500);

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        login l = new login(Q1);
        exit e = new exit(Qe1, Qe2, Qe3, Qe4);

        elevator1 e1 = new elevator1(l, e);
        Thread elev1 = new Thread(e1);

        elevator2 e2 = new elevator2(l, e, false);
        Thread elev2 = new Thread(e2);

        elevator3 e3 = new elevator3(l, e, false);
        Thread elev3 = new Thread(e3);

        elevator4 e4 = new elevator4(l, e, false);
        Thread elev4 = new Thread(e4);

        elevator5 e5 = new elevator5(l, e, false);
        Thread elev5 = new Thread(e5);

        control c = new control(elev1, elev2, elev3, elev4, elev5, e1, e2, e3, e4, e5);

        Thread log = new Thread(l);
        Thread ex = new Thread(e);
        Thread co = new Thread(c);

        OutputFrame o = new OutputFrame();
        o.setVisible(true);
        
        log.start();
        Thread.sleep(500);

        co.start();
        Thread.sleep(200);

        ex.start();
        Thread.sleep(1000);

    }

}
