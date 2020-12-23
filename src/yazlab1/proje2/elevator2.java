package yazlab1.proje2;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import javax.swing.JLabel;

public class elevator2 implements Runnable {

    boolean a = false;
    static int location = 0;
    int capacity = 10;
    static int floorN;
    static int personN;
    static boolean active = false;

    login l;
    exit e;

    JLabel[] labels2;

    static String mode;
    static int destination;
    static String direction;
    static int count_inside;
    static String inside;
    static int floorTxt;

    public elevator2(login l, exit e, boolean b) {
        this.l = l;
        this.e = e;
        this.a = b;

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

    public elevator2(JLabel[] label) {
        if (active == true) {
            label[7].setText("active: True");
            label[12].setText("mode: working");
            label[17].setText("floor: " + location);
            destination = floorN;
            label[22].setText("destination: " + destination);
            if (elevator1.location < elevator1.floorN) {
                label[27].setText("direction: up");
            }
            if (elevator1.location > elevator1.floorN) {
                label[27].setText("direction: down");
            }
            count_inside = personN;
            label[32].setText("count_inside: " + count_inside);
            inside = new String("[" + personN + "," + floorN + "]");
            label[37].setText("inside: " + inside);
        }
        if (active == false) {
            label[7].setText("active: false");
            label[12].setText("mode: idle");
            label[17].setText("floor: 0");
            label[22].setText("destination: 0");
            label[27].setText("direction: up");
            label[32].setText("count_inside: 0");
            label[37].setText("inside: []");
        }

    }

    public void Take(BlockingQueue<ArrayList<Integer>> Q, int n, int f) {

        synchronized (e) {
            elevator2.location = f;
            System.out.println("Exit " + f + "kuyrugu geldi");
            int m = 0;
            personN = Q.peek().get(0);
            floorN = Q.peek().get(1);
            Q.remove().get(m);
            System.out.println("\n");
            System.out.println("mode : working");
            System.out.println("floor : " + elevator2.location); // 1
            System.out.println("destination : " + 0);
            if (elevator2.location < elevator2.floorN) {
                System.out.println("direction : up ");
            }
            if (elevator2.location > elevator2.floorN) {
                System.out.println("direction : down ");
            }
            System.out.println("capacity :" + 10);
            
            count_inside = personN;
            System.out.println("count_inside: " + count_inside);

            System.out.println("inside :" + "[" + elevator2.personN + "," + elevator2.floorN + "]");
            System.out.println("Zemin Kata " + elevator2.personN + " kişi bırakıldı.");
            n = n - personN;
            elevator2.location = 0;

            System.out.println("Asansör 2 nin  yeni konumu : " + elevator2.location);
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
                        l.QwaitingN = l.QwaitingN - elevator2.personN;
                        System.out.println("Login kuyrugu geldi");
                        System.out.println("\n");
                        System.out.println("mode : working");
                        System.out.println("floor : " + elevator2.location);
                        System.out.println("destination : " + elevator2.floorN);
                        if (elevator2.location < elevator2.floorN) {
                            System.out.println("direction : up ");
                        }
                        if (elevator2.location > elevator2.floorN) {
                            System.out.println("direction : down ");
                        }

                        System.out.println("capacity :" + 10);
                        System.out.println("inside :" + "[" + elevator2.personN + "," + elevator2.floorN + "]");

                    }
                    if (floorN == 1) {
                        synchronized (e) {
                            System.out.println("1. Kata " + elevator2.personN + " kişi bırakıldı.");

                            if (e.Qe1.isEmpty() == false) {
                                while (activeEF1() == true) {
                                    Take(e.Qe1, e.QwaitingN1, 1);
                                }
                            } else {
                                elevator2.location = 1;
                            }

                            floorPeople.p1 = floorPeople.p1 + elevator2.personN;

                        }
                    }
                    if (floorN == 2) {
                        synchronized (e) {
                            System.out.println("2. Kata " + elevator2.personN + " kişi bırakıldı.");
                            if (e.Qe2.isEmpty() == false) {
                                while (activeEF2() == true) {
                                    Take(e.Qe2, e.QwaitingN2, 2);
                                }
                            } else {
                                elevator2.location = 2;
                            }
                            floorPeople.p2 = floorPeople.p2 + elevator2.personN;

                        }
                    }
                    if (floorN == 3) {
                        synchronized (e) {
                            System.out.println("3. Kata " + elevator2.personN + " kişi bırakıldı.");
                            floorPeople.p3 = floorPeople.p3 + elevator2.personN;
                            if (e.Qe3.isEmpty() == false) {
                                while (activeEF3() == true) {
                                    Take(e.Qe3, e.QwaitingN3, 3);
                                }
                            } else {
                                elevator2.location = 3;
                            }
                            floorPeople.p3 = floorPeople.p3 + elevator2.personN;

                        }
                    }
                    if (floorN == 4) {
                        synchronized (e) {
                            System.out.println("4. Kata " + elevator2.personN + " kişi bırakıldı.");
                            if (e.Qe3.isEmpty() == false) {
                                while (activeEF4() == true) {
                                    Take(e.Qe4, e.QwaitingN4, 4);
                                }
                            } else {
                                elevator2.location = 4;
                            }
                            floorPeople.p4 = floorPeople.p4 + elevator2.personN;

                        }
                    }
                    System.out.println("Asansör 2'nin yeni konumu : ****" + elevator2.location);
                    System.out.println("Login Kuyrugunun son durumu : " + l.Q1);
                    System.out.println("Login kuyruğundaki insan sayısı :" + l.QwaitingN);
                    m++;

                    if (l.Q1.isEmpty()) {
                        System.out.println("Login de insan kalmadı");
                        break;

                    }
                }

                Thread.sleep(200);

            }
        } catch (Exception e) {
            System.out.println("The error was caught (elevator2)" + e);

        }

    }

}
