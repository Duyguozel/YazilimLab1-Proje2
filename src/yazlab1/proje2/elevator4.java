package yazlab1.proje2;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import javax.swing.JLabel;

public class elevator4 implements Runnable {

    boolean a = false;
    static int location = 0;
    int capacity = 10;
    static int floorN;
    static int personN;
    static int destination;
    static int count_inside;
    static String inside;
    static boolean active = false;

    JLabel[] labels4;
    login l;
    exit e;

    public elevator4(login l, exit e, boolean b) {
        this.l = l;
        this.e = e;
        this.a = b;

    }

    public elevator4(JLabel[] label) {
        if (active == true) {
            label[9].setText("active: True");
            label[14].setText("mode: working");
            label[19].setText("floor: " + location);
            destination = floorN;
            label[24].setText("destination: " + destination);
            if (elevator4.location < elevator4.floorN) {
                label[29].setText("direction: up");
            }
            if (elevator4.location > elevator4.floorN) {
                label[29].setText("direction: down");
            }
            count_inside = personN;
            label[34].setText("count_inside: " + count_inside);
            inside = new String("[" + personN + "," + floorN + "]");
            label[39].setText("inside: " + inside);
        }
        if (active == false) {
            label[9].setText("active: false");
            label[14].setText("mode: idle");
            label[19].setText("floor: 0");
            label[24].setText("destination: 0");
            label[29].setText("direction: up");
            label[34].setText("count_inside: 0");
            label[39].setText("inside: []");
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
        synchronized (e) {
            elevator4.location = f;
            System.out.println("Exit " + f + "kuyrugu geldi");
            int m = 0;
            personN = Q.peek().get(0);
            floorN = Q.peek().get(1);
            Q.remove().get(m);
            System.out.println("\n");
            System.out.println("mode : working");
            System.out.println("floor : " + elevator4.location); // 1
            System.out.println("destination : " + 0);
            if (elevator4.location < elevator4.floorN) {
                System.out.println("direction : up ");
            }
            if (elevator4.location > elevator4.floorN) {
                System.out.println("direction : down ");
            }
            System.out.println("capacity :" + 10);
            System.out.println("inside :" + "[" + elevator4.personN + "," + elevator4.floorN + "]");
            System.out.println("Zemin Kata " + elevator4.personN + " kişi bırakıldı.");
            n = n - personN;
            elevator4.location = 0;

            System.out.println("Asansör 4'ün yeni konumu : " + elevator4.location);
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
                        l.QwaitingN = l.QwaitingN - elevator4.personN;
                        System.out.println("Login kuyrugu geldi");
                        System.out.println("\n");
                        System.out.println("mode : working");
                        System.out.println("floor : " + elevator4.location);
                        System.out.println("destination : " + elevator4.floorN);
                        if (elevator4.location < elevator4.floorN) {
                            System.out.println("direction : up ");
                        }
                        if (elevator4.location > elevator4.floorN) {
                            System.out.println("direction : down ");
                        }

                        System.out.println("capacity :" + 10);
                        System.out.println("inside :" + "[" + elevator4.personN + "," + elevator4.floorN + "]");

                    }
                    if (floorN == 1) {
                        synchronized (e) {
                            System.out.println("1. Kata " + elevator4.personN + " kişi bırakıldı.");

                            //System.out.println("1. kata bırakıldı asanör zemine inecek");
                            if (e.Qe1.isEmpty() == false) {
                                while (activeEF1() == true) {
                                    Take(e.Qe1, e.QwaitingN1, 1);
                                }
                            } else {
                                elevator4.location = 1;
                            }

                            floorPeople.p1 = floorPeople.p1 + elevator4.personN;

                        }
                    }
                    if (floorN == 2) {
                        synchronized (e) {
                            System.out.println("2. Kata " + elevator4.personN + " kişi bırakıldı.");
                            if (e.Qe2.isEmpty() == false) {
                                while (activeEF2() == true) {
                                    Take(e.Qe2, e.QwaitingN2, 2);
                                }
                            } else {
                                elevator4.location = 2;
                            }
                            floorPeople.p2 = floorPeople.p2 + elevator4.personN;

                        }
                    }
                    if (floorN == 3) {
                        synchronized (e) {
                            System.out.println("3. Kata " + elevator4.personN + " kişi bırakıldı.");
                            floorPeople.p3 = floorPeople.p3 + elevator4.personN;
                            if (e.Qe3.isEmpty() == false) {
                                while (activeEF3() == true) {
                                    Take(e.Qe3, e.QwaitingN3, 3);
                                }
                            } else {
                                elevator4.location = 3;
                            }
                            floorPeople.p3 = floorPeople.p3 + elevator4.personN;

                        }
                    }
                    if (floorN == 4) {
                        synchronized (e) {
                            System.out.println("4. Kata " + elevator4.personN + " kişi bırakıldı.");
                            if (e.Qe3.isEmpty() == false) {
                                while (activeEF4() == true) {
                                    Take(e.Qe4, e.QwaitingN4, 4);
                                }
                            } else {
                                elevator4.location = 4;
                            }
                            floorPeople.p4 = floorPeople.p4 + elevator4.personN;

                        }
                    }
                    System.out.println("Asansör 2'nin yeni konumu : " + elevator4.location);
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
            System.out.println("The error was caught (elevator4)" + e);

        }

    }

}
