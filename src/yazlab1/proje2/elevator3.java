package yazlab1.proje2;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import javax.swing.JLabel;

public class elevator3 implements Runnable {

    boolean a = false;
    static int location = 0;
    int capacity = 10;
    static int floorN;
    static int personN;
    static int destination;
    static int count_inside;
    static String inside;
    static boolean active = false;

    JLabel[] labels3;

    login l;
    exit e;

    public elevator3(login l, exit e, boolean b) {
        this.l = l;
        this.e = e;
        this.a = b;

    }

    public elevator3(JLabel[] label) {
        if (active == true) {
            label[8].setText("active: True");
            label[13].setText("mode: working");
            label[18].setText("floor: " + location);
            destination = floorN;
            label[23].setText("destination: " + destination);
            if (elevator3.location < elevator3.floorN) {
                label[28].setText("direction: up");
            }
            if (elevator3.location > elevator3.floorN) {
                label[28].setText("direction: down");
            }
            count_inside = personN;
            label[33].setText("count_inside: " + count_inside);
            inside = new String("[" + personN + "," + floorN + "]");
            label[38].setText("inside: " + inside);
        }
        if (active == false) {
            label[8].setText("active: false");
            label[13].setText("mode: idle");
            label[18].setText("floor: 0");
            label[23].setText("destination: 0");
            label[28].setText("direction: up");
            label[33].setText("count_inside: 0");
            label[38].setText("inside: []");
        }

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
        //while (activeEF1() == true) {
        synchronized (e) {
            elevator3.location = f;
            System.out.println("Exit " + f + "kuyrugu geldi");
            int m = 0;
            personN = Q.peek().get(0);
            floorN = Q.peek().get(1);
            Q.remove().get(m);
            System.out.println("\n");
            System.out.println("mode : working");
            System.out.println("floor : " + elevator3.location); // 1
            System.out.println("destination : " + 0);
            if (elevator3.location < elevator3.floorN) {
                System.out.println("direction : up ");
            }
            if (elevator3.location > elevator3.floorN) {
                System.out.println("direction : down ");
            }
            System.out.println("capacity :" + 10);
            System.out.println("inside :" + "[" + elevator3.personN + "," + elevator3.floorN + "]");
            System.out.println("Zemin Kata " + elevator3.personN + " kişi bırakıldı.");
            n = n - personN;
            elevator3.location = 0;

            System.out.println("Asansör 3'ün yeni konumu : " + elevator3.location);
            m++;

            if (Q.isEmpty()) {
                System.out.println("Kuyruk " + f + " boş.");
            }
        }
    }

    @Override
    public void run() {
        try {
            int a = 0;
            while (true) {
                while (activeEL() == true) {
                    int m = 0;
                    synchronized (l) {
                        personN = l.Q1.peek().get(0);
                        floorN = l.Q1.peek().get(1);
                        l.Q1.remove().get(m);
                        l.QwaitingN = l.QwaitingN - elevator3.personN;
                        System.out.println("Login kuyrugu geldi");
                        System.out.println("\n");
                        System.out.println("mode : working");
                        System.out.println("floor : " + elevator3.location);
                        System.out.println("destination : " + elevator3.floorN);
                        if (elevator3.location < elevator3.floorN) {
                            System.out.println("direction : up ");
                        }
                        if (elevator3.location > elevator3.floorN) {
                            System.out.println("direction : down ");
                        }

                        System.out.println("capacity :" + 10);
                        System.out.println("inside :" + "[" + elevator3.personN + "," + elevator3.floorN + "]");

                    }
                    if (floorN == 1) {
                        synchronized (e) {
                            System.out.println("1. Kata " + elevator3.personN + " kişi bırakıldı.");

                            //System.out.println("1. kata bırakıldı asanör zemine inecek");
                            if (e.Qe1.isEmpty() == false) {
                                while (activeEF1() == true) {
                                    Take(e.Qe1, e.QwaitingN1, 1);
                                }
                            } else {
                                elevator3.location = 1;
                            }

                            floorPeople.p1 = floorPeople.p1 + elevator3.personN;

                        }
                    }
                    if (floorN == 2) {
                        synchronized (e) {
                            System.out.println("2. Kata " + elevator3.personN + " kişi bırakıldı.");
                            if (e.Qe2.isEmpty() == false) {
                                while (activeEF2() == true) {
                                    Take(e.Qe2, e.QwaitingN2, 2);
                                }
                            } else {
                                elevator3.location = 2;
                            }
                            floorPeople.p2 = floorPeople.p2 + elevator3.personN;

                        }
                    }
                    if (floorN == 3) {
                        synchronized (e) {
                            System.out.println("3. Kata " + elevator3.personN + " kişi bırakıldı.");
                            floorPeople.p3 = floorPeople.p3 + elevator3.personN;
                            if (e.Qe3.isEmpty() == false) {
                                while (activeEF3() == true) {
                                    Take(e.Qe3, e.QwaitingN3, 3);
                                }
                            } else {
                                elevator3.location = 3;
                            }
                            floorPeople.p3 = floorPeople.p3 + elevator3.personN;

                        }
                    }
                    if (floorN == 4) {
                        synchronized (e) {
                            System.out.println("4. Kata " + elevator3.personN + " kişi bırakıldı.");
                            if (e.Qe3.isEmpty() == false) {
                                while (activeEF4() == true) {
                                    Take(e.Qe4, e.QwaitingN4, 4);
                                }
                            } else {
                                elevator3.location = 4;
                            }
                            floorPeople.p4 = floorPeople.p4 + elevator3.personN;

                        }
                    }
                    System.out.println("Asansör 2'nin yeni konumu : " + elevator3.location);
                    System.out.println("Login Kuyrugunun son durumu : " + l.Q1);
                    System.out.println("Login kuyruğundaki insan sayısı :" + l.QwaitingN);
                    m++;

                    if (l.Q1.isEmpty()) {
                        System.out.println("Login de insan kalmadı");
                        break;

                    }
                }
                a++;
                Thread.sleep(200);

            }
        } catch (Exception e) {
            System.out.println("The error was caught (elevator3)" + e);

        }

    }

}
