package yazlab1.proje2;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import javax.print.attribute.standard.Destination;
import javax.swing.JLabel;

public class elevator1 implements Runnable {

    static int location = 0;
    int capacity = 10;
    static int floorN;
    static int personN;
    JLabel[] labels1;

    login l;
    exit e;

    static boolean active;
    static String mode;
    static int destination;
    static String direction;
    static int count_inside;
    static String inside;
    static int floorTxt;
   

    public elevator1(login l, exit e) {
        this.l = l;
        this.e = e;
    }

    public elevator1(JLabel[] label) {

        this.labels1 = label;
        active = true;
        label[6].setText("active: True");
        mode = "working";
        label[11].setText("mode: working");
        label[16].setText("floor: " + location);
        destination = floorN;
        label[21].setText("destination: " + destination);

        if (elevator1.location < elevator1.floorN) {
            direction = "up";
            label[26].setText("direction: up");
        }
        if (elevator1.location > elevator1.floorN) {
            direction = "down";
            label[26].setText("direction: down");
        }
        count_inside = personN;
        label[31].setText("count_inside: " + count_inside);
        inside = new String("[" + personN + "," + floorN + "]");
        label[36].setText("inside: " + inside);
        floorTxt = location;

    }

    public boolean activeEL() {
        if (l.Q1.isEmpty() == false) {
            return true;
        } else {
            return false;
        }
    }

    public boolean activeEF1() {
        if (e.Qe1.isEmpty() == false) {
            return true;
        } else {
            return false;
        }
    }

    public boolean activeEF2() {
        if (e.Qe2.isEmpty() == false) {
            return true;
        } else {
            return false;
        }
    }

    public boolean activeEF3() {
        if (e.Qe3.isEmpty() == false) {
            return true;
        } else {
            return false;
        }
    }

    public boolean activeEF4() {
        if (e.Qe4.isEmpty() == false) {
            return true;
        } else {
            return false;
        }
    }

    public void Take(BlockingQueue<ArrayList<Integer>> Q, int n, int f) {

        synchronized (e) {
            elevator1.location = f;
            System.out.println("Exit " + f + "kuyrugu geldi");
            int m = 0;
            personN = Q.peek().get(0);
            floorN = Q.peek().get(1);
            Q.remove().get(m);
            System.out.println("\n");
            System.out.println("mode : working");

            System.out.println("floor : " + elevator1.location); // 1
            System.out.println("destination : " + 0);

            if (elevator1.location < elevator1.floorN) {
                System.out.println("direction : up ");
            }
            if (elevator1.location > elevator1.floorN) {
                System.out.println("direction : down ");
            }
            System.out.println("capacity :" + 10);

            count_inside = personN;
            System.out.println("count_inside: " + count_inside);

            System.out.println("inside :" + "[" + elevator1.personN + "," + elevator1.floorN + "]");
            System.out.println("Zemin Kata " + elevator1.personN + " kişi bırakıldı.\n\n");
            n = n - personN;
            elevator1.location = 0;

            System.out.println("Asansör 1'in yeni konumu : " + elevator1.location);
            m++;

            if (Q.isEmpty()) {
                System.out.println("Kuyruk " + f + " boş.");
            }
        }

    }

    @Override
    public void run() {
        try {
            while (true) {

                while (activeEL() == true) {

                    int m = 0;

                    synchronized (l) {
                        personN = l.Q1.peek().get(0);
                        floorN = l.Q1.peek().get(1);
                        l.Q1.remove().get(m);
                        l.QwaitingN = l.QwaitingN - elevator1.personN;
                        System.out.println("Login kuyrugu geldi");
                        System.out.println("\n");
                        System.out.println("mode : working");
                        System.out.println("floor : " + elevator1.location);
                        System.out.println("destination : " + elevator1.floorN);
                        if (elevator1.location < elevator1.floorN) {
                            System.out.println("direction : up ");

                        }
                        if (elevator1.location > elevator1.floorN) {
                            System.out.println("direction : down ");
                        }

                        System.out.println("capacity :" + 10);
                        System.out.println("inside :" + "[" + elevator1.personN + "," + elevator1.floorN + "]");

                    }

                    if (floorN == 1) {
                        synchronized (e) {
                            System.out.println("1. Kata " + elevator1.personN + " kişi bırakıldı.\n\n");

                            if (e.Qe1.isEmpty() == false) {
                                while (activeEF1() == true) {
                                    Take(e.Qe1, e.QwaitingN1, 1);
                                }
                            } else {
                                System.out.println("Logindeki kuyruk alınmak için zemine iniliyor.\n");
                                elevator1.location = 0;
                            }
                            floorPeople.p1 = floorPeople.p1 + elevator1.personN;

                        }

                    }
                    if (floorN == 2) {
                        synchronized (e) {
                            System.out.println("2. Kata " + elevator1.personN + " kişi bırakıldı.\n\n");
                            if (e.Qe2.isEmpty() == false) {
                                while (activeEF2() == true) {
                                    Take(e.Qe2, e.QwaitingN2, 2);
                                }
                            } else {
                                System.out.println("Logindeki kuyruk alınmak için zemine iniliyor.\n");
                                elevator1.location = 0;
                            }
                            floorPeople.p2 = floorPeople.p2 + elevator1.personN;

                        }

                    }
                    if (floorN == 3) {
                        synchronized (e) {
                            System.out.println("3. Kata " + elevator1.personN + " kişi bırakıldı.\n");
                            floorPeople.p3 = floorPeople.p3 + elevator1.personN;
                            if (e.Qe3.isEmpty() == false) {
                                while (activeEF3() == true) {
                                    Take(e.Qe3, e.QwaitingN3, 3);
                                }
                            } else {
                                System.out.println("Logindeki kuyruk alınmak için zemine iniliyor.\n");
                                elevator1.location = 0;
                            }
                            floorPeople.p3 = floorPeople.p3 + elevator2.personN;

                        }
                    }
                    if (floorN == 4) {
                        synchronized (e) {
                            System.out.println("4. Kata " + elevator1.personN + " kişi bırakıldı.\n");
                            if (e.Qe3.isEmpty() == false) {
                                while (activeEF4() == true) {
                                    Take(e.Qe4, e.QwaitingN4, 4);
                                }
                            } else {
                                System.out.println("Logindeki kuyruk alınmak için zemine iniliyor.\n");
                                elevator1.location = 0;
                            }
                            floorPeople.p4 = floorPeople.p4 + elevator1.personN;

                        }
                    }
                    System.out.println("Asansör 1'in yeni konumu : " + elevator1.location);
                    System.out.println("Login Kuyrugunun son durumu : " + l.Q1);
                    System.out.println("Login kuyruğundaki insan sayısı :" + l.QwaitingN);
                    m++;

                    if (l.Q1.isEmpty()) {
                        System.out.println("Login de insan kalmadı\n");
                        break;

                    }
                }

                Thread.sleep(200);


            }
        } catch (Exception e) {
            System.out.println("The error was caught (elevator1)" + e);

        }

    }
}
