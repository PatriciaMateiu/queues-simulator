package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimulatorFrame extends JFrame {
    private JFrame initial = new JFrame();
    private JFrame newf = new JFrame();
    private JTextField amin = new JTextField(5);
    private JTextField amax = new JTextField(5);
    private JTextField smin = new JTextField(5);
    private JTextField smax = new JTextField(5);
    private JTextField interval = new JTextField(5);
    private JTextField queues = new JTextField(5);
    private JLabel l1 = new JLabel("Minimum arriving time ");
    private JLabel l2 = new JLabel("Maximum arriving time ");
    private JLabel l3 = new JLabel("Minimum service time ");
    private JLabel l4 = new JLabel("Maximum service time ");
    private JLabel l5 = new JLabel("Simulation interval ");
    private JLabel l6 = new JLabel("Number of queues ");

    private JButton b1 = new JButton("Start simulation");
    private  static final long serialVersionUID = 1L;
    private JPanel panel1 = new JPanel();

    private JTextArea area= new JTextArea();
    private int WIDTH = 800, HEIGHT = 800;

    private JPanel panel_ = new JPanel();
    private JPanel panel1_ = new JPanel();
    private JPanel panel2_ = new JPanel();
    private JPanel panel3_ = new JPanel();
    public JTextArea area1= new JTextArea();
    private JLabel q1 = new JLabel("Queue 1");
    private JLabel q2 = new JLabel("Queue 2");
    private JLabel q3 = new JLabel("Queue 3");
    private JLabel l7 = new JLabel("Average waiting time");
    private JLabel l8 = new JLabel("Average service time");
    private JLabel l9 = new JLabel("Peak hour");
    private JTextField t1 = new JTextField(20);
    private JTextField t2 = new JTextField(20);
    private JTextField t3 = new JTextField(20);
    public JTextField t4 = new JTextField(5);
    public JTextField t5 = new JTextField(5);
    public JTextField t6 = new JTextField(5);





    public SimulatorFrame() {
        panel1.setPreferredSize(new Dimension(600, 100));
        //panel2.setPreferredSize(new Dimension(300, 600));
        //panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        initial.setSize(600,200);
        panel1.add(l1);
        panel1.add(amin);
        panel1.add(l2);
        panel1.add(amax);
        panel1.add(l3);
        panel1.add(smin);
        panel1.add(l4);
        panel1.add(smax);
        panel1.add(l5);
        panel1.add(interval);
        panel1.add(l6);
        panel1.add(queues);
        panel1.add(b1);
        JScrollPane scrollPane = new JScrollPane(area);
       // area.setEditable(false);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //panel2.add(area);
       // panel2.add(scrollPane);
        initial.add(panel1);
        //panel.add(panel2);
        //initial.add(panel);
        //initial.add(panel2);
        initial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initial.setLocationRelativeTo(null);
        initial.setVisible(true);



    }
    public int getMinArrival() {
        String s = amin.getText();
        try
        {
            int i = Integer.parseInt(s.trim());
            return i;
        }
        catch (NumberFormatException nfe)
        {
            System.out.println(" getMinArrival : NumberFormatException: " + nfe.getMessage());
            return -1;
        }

    }

    public int getMaxArrival() {
        String s = amax.getText();
        try {
            int i = Integer.parseInt(s.trim());
            return i;
        } catch (NumberFormatException nfe) {
            System.out.println(" getMaxArrival : NumberFormatException: " + nfe.getMessage());
            return -1;
        }

    }

    public int getMinService() {
        String s = smin.getText();
        try {
            int i = Integer.parseInt(s.trim());
            return i;
        } catch (NumberFormatException nfe) {
            System.out.println(" getMinService : NumberFormatException: " + nfe.getMessage());
            return -1;
        }

    }

    public int getMaxService() {
        String s = smax.getText();
        try {
            int i = Integer.parseInt(s.trim());
            return i;
        } catch (NumberFormatException nfe) {
            System.out.println(" getMaxService : NumberFormatException: " + nfe.getMessage());
            return -1;
        }

    }
    public int getInterval() {
        String s = interval.getText();
        try {
            int i = Integer.parseInt(s.trim());
            return i;
        } catch (NumberFormatException nfe) {
            System.out.println(" getInterval : NumberFormatException: " + nfe.getMessage());
            return 0;
        }
    }
    public int getNrQueues() {
        String s = queues.getText();
        try {
            int i = Integer.parseInt(s.trim());
            return i;
        } catch (NumberFormatException nfe) {
            System.out.println(" getNrQueues : NumberFormatException: " + nfe.getMessage());
            return 0;
        }
    }

    public void addListener(ActionListener al){
        b1.addActionListener(al);
    }

    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(null, errMessage, "Error!", JOptionPane.ERROR_MESSAGE);
    }

    public JFrame fr = new JFrame();
    public JTextArea jta = new JTextArea();
    public JPanel jp = new JPanel();

    public JTextArea getArea(){
        return area;
    }

    public void addText(JTextArea a, String string){
        //System.out.println("start gettext");
        String s = a.getText();
        if(s != ""){
            //System.out.println("nonnull text area");
             s = s + "\n" + string;}
        else {s = string;}
            //System.out.println("null text area");}
        a.setText(s);
    }

    public JTextField getTextField(int id){
        if(id == 1)
            return t1;
        if(id == 2)
            return t2;
        if(id == 3)
            return t3;
        else return null;
    }

    public void updateQueue(JTextField q, char mode, int client){
        String s = q.getText();
        String t = String.valueOf(client);
        String txt="";
        if(mode=='a'){
                if(s!=null){
                    s = s + " " + t;}
                else s = t;
        }
        if(mode=='r'){
            if(s.length()!=0) {
                //System.out.println("Client to be removed : " + client);
                //System.out.println(s);
                int indx = s.indexOf(t);
                //System.out.println(indx);
                if(t.length()==1){
                    txt = s.substring(1, indx) + s.substring(indx + 1);}
                else if(t.length()==2){
                   txt = s.substring(1, indx) + s.substring(indx + 2);}
            s = txt;}
            }
        q.setText(s);
    }


   /* public void displayData(){
        initial.setVisible(false);
        this.dispose();
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        newPanel.setPreferredSize(new Dimension(200, 500));
        int q = getNrQueues();
        for(int i = 0; i < q; i++){
            JPanel pan = new JPanel();
            JLabel l = new JLabel("QUEUE " + (i + 1));
            pan.add(l);
            newPanel.add(pan);
        }
        //JTextArea jta = new JTextArea(100, 20);
        JLabel ll = new JLabel("Evolution ");
        ll.setHorizontalAlignment(SwingConstants.LEFT);
        newPanel.add(ll);
        JScrollPane scrollPane = new JScrollPane(jta);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //jta.setEditable(false);
        newPanel.add(scrollPane);
        newf.add(newPanel);
        newf.setVisible(true);
        newf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newf.setLocationRelativeTo(null);

    }*/
   /*public void displayData(Task[] tsks){
       /*initial.setVisible(false);
       initial.dispose();
       JFrame frr = new JFrame();
       frr.setPreferredSize(new Dimension(500, 300));
       JPanel p = new JPanel();
       p.setPreferredSize(new Dimension(300,100));
       frr.add(p);
       //panel.removeAll();
       //panel.repaint();
       //JTextArea ta = new JTextArea(50,50);
       //ta.setEditable(false);
       //System.out.println("list size: " + tsks.length);
       while(true){
            for(int i = 0 ; i < tsks.length; i++){
                addText(area, tsks[i].toString());}
       }
       //p.add(ta);
       //JScrollPane scrollPane = new JScrollPane(ta);
       //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       //p.add(scrollPane);
       //p.setLayout(new GridLayout());
       //panel.repaint();
       //panel.revalidate();
       //frr.setVisible(true);
       //frr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       //frr.setLocationRelativeTo(null);
   }*/

   /*public JFrame newFrame(){
       JFrame jf = new JFrame();
       jf.setSize(WIDTH, HEIGHT);
       JScrollPane scrollPane = new JScrollPane(area);
       panel2.setPreferredSize(new Dimension(300, 600));
       panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
       area.setEditable(false);
       scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       panel2.add(area);
       panel2.add(scrollPane);
       jf.add(panel2);
       //initial.add(panel2);
       jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       jf.setLocationRelativeTo(null);
       jf.setVisible(true);

       return jf;
   }*/

    public void displayFrame(){
        panel_.setPreferredSize(new Dimension(300, 300));
        newf.setSize(WIDTH, HEIGHT);
        panel_.setLayout(new BoxLayout(panel_, BoxLayout.Y_AXIS));
        panel1_.add(new JLabel("Logs"));
        //panel1_.add(q1);
        area1.setRows(20);
        area1.setColumns(50);
        JScrollPane scrollPane1 = new JScrollPane(area1);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1_.add(scrollPane1);
        area1.setEditable(false);
        if(this.getNrQueues() == 1){
            panel2_.add(q1);
            panel2_.add(t1);}
        else if(this.getNrQueues() == 2){
            panel2_.add(q1);
            panel2_.add(t1);
            panel2_.add(q2);
            panel2_.add(t2);}
        else if(this.getNrQueues() == 3){
            panel2_.add(q1);
            panel2_.add(t1);
            panel2_.add(q2);
            panel2_.add(t2);
            panel2_.add(q3);
            panel2_.add(t3);}
        else{
            showError("Invalid nr of queues!");
            return;}

        panel3_.add(l7);
        panel3_.add(t4);
        panel3_.add(l8);
        panel3_.add(t5);
        panel3_.add(l9);
        panel3_.add(t6);

        panel_.add(panel1_);
        panel_.add(panel2_);
        panel_.add(panel3_);
        newf.add(panel_);
        newf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newf.setLocationRelativeTo(null);
        newf.setVisible(true);

    }

}
