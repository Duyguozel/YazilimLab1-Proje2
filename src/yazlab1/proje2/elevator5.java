package yazlab1.proje2;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import javax.swing.JLabel;

public class elevator5 implements Runnable {

    boolean a = false;
    static int location = 0;
    int capacity = 10;
    static int floorN;
    static int personN;
    static int destination;
    static int count_inside;
    static String inside;
    static boolean active = false;

    JLabel[] labels5;
    login l;
    exit e;

    public elevator5(login l, exit e, boolean b) {
        this.l = l;
        this.e = e;
        this.a = b;

    }

    public elevator5(JLabel[] label) {
        if (active == true) {
            label[10].setText("active: True");
            label[15].setText("mode: working");
            label[20].setText("floor: " + location);
            destination = floorN;
            label[25].setText("destination: " + destination);
            if (elevator5.location < elevator5.floorN) {
                label[30].setText("direction: up");
            }
            if (elevator5.location > elevator5.floorN) {
                label[30].setText("direction: down");
            }
            count_inside = personN;
            label[35].setText("count_inside: " + count_inside);
            inside = new String("[" + personN + "," + floorN + "]");
            label[40].setText("inside: " + inside);
        }
        if (active == false) {
            label[10].setText("active: false");
            label[15].setText("mode: idle");
            label[20].setText("floor: 0");
            label[25].setText("destination: 0");
            label[30].setText("direction: up");
            label[35].setText("count_inside: 0");
            label[40].setText("inside: []");
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
            elevator5.location = f;
            System.out.println("Exit " + f + "kuyrugu geldi");
            int m = 0;
            personN = Q.peek().get(0);
            floorN = Q.peek().get(1);
            Q.remove().get(m);
            System.out.println("\n");
            System.out.println("mode : working");
            System.out.println("floor : " + elevator5.location); // 1
            System.out.println("destination : " + 0);
            if (elevator5.location < elevator5.floorN) {
                System.out.println("direction : up ");
            }
            if (elevator5.location > elevator5.floorN) {
                System.out.println("direction : down ");
            }
            System.out.println("capacity :" + 10);
            System.out.println("inside :" + "[" + elevator5.personN + "," + elevator5.floorN + "]");
            System.out.println("Zemin Kata " + elevator5.personN + " kişi bırakıldı.");
            n = n - personN;
            elevator5.location = 0;

            System.out.println("Asansör  5'in yeni konumu : " + elevator5.location);
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
                        l.QwaitingN = l.QwaitingN - elevator5.personN;
                        System.out.println("Login kuyrugu geldi");
                        System.out.println("\n");
                        System.out.println("mode : working");
                        System.out.println("floor : " + elevator5.location);
                        System.out.println("destination : " + elevator5.floorN);
                        if (elevator5.location < elevator5.floorN) {
                            System.out.println("direction : up ");
                        }
                        if (elevator5.location > elevator5.floorN) {
                            System.out.println("direction : down ");
                        }

                        System.out.println("capacity :" + 10);
                        System.out.println("inside :" + "[" + elevator5.personN + "," + elevator5.floorN + "]");

                    }
                    if (floorN == 1) {
                        synchronized (e) {
                            System.out.println("1. Kata " + elevator5.personN + " kişi bırakıldı.");

                            if (e.Qe1.isEmpty() == false) {
                                while (activeEF1() == true) {
                                    Take(e.Qe1, e.QwaitingN1, 1);
                                }
                            } else {
                                elevator5.location = 1;
                            }

                            floorPeople.p1 = floorPeople.p1 + elevator5.personN;

                        }
                    }
                    if (floorN == 2) {
                        synchronized (e) {
                            System.out.println("2. Kata " + elevator5.personN + " kişi bırakıldı.");
                            if (e.Qe2.isEmpty() == false) {
                                while (activeEF2() == true) {
                                    Take(e.Qe2, e.QwaitingN2, 2);
                                }
                            } else {
                                elevator5.location = 2;
                            }
                            floorPeople.p2 = floorPeople.p2 + elevator5.personN;

                        }
                    }
                    if (floorN == 3) {
                        synchronized (e) {
                            System.out.println("3. Kata " + elevator5.personN + " kişi bırakıldı.");
                            floorPeople.p3 = floorPeople.p3 + elevator5.personN;
                            if (e.Qe3.isEmpty() == false) {
                                while (activeEF3() == true) {
                                    Take(e.Qe3, e.QwaitingN3, 3);
                                }
                            } else {
                                elevator5.location = 3;
                            }
                            floorPeople.p3 = floorPeople.p3 + elevator5.personN;

                        }
                    }
                    if (floorN == 4) {
                        synchronized (e) {
                            System.out.println("4. Kata " + elevator5.personN + " kişi bırakıldı.");
                            if (e.Qe3.isEmpty() == false) {
                                while (activeEF4() == true) {
                                    Take(e.Qe4, e.QwaitingN4, 4);
                                }
                            } else {
                                elevator5.location = 4;
                            }
                            floorPeople.p4 = floorPeople.p4 + elevator5.personN;

                        }
                    }
                    System.out.println("Asansör 2'nin yeni konumu : " + elevator5.location);
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
            System.out.println("The error was caught (elevator5)" + e);

        }

    }

}
