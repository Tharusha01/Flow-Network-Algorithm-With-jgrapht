import javax.swing.*;

/*
 * w1697753 - Ishan Tharusha Wijayabahu
 */

public class Test extends JApplet{


    public static void main(String[] args) {

        /*
         * Creating object of Graph class for display the graphs and details.
         */
        long start = System.currentTimeMillis();
        JGraph gui = new JGraph();
        gui.displayGUI();
        long end = System.currentTimeMillis();
        System.out.println("Start Time : "+start);
        System.out.println("End Time : "+end);
        System.out.println("Time :" + (end - start));

    }

}
