import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_DOWN;

public class UI extends JFrame {

    private JPanel panel2;
    private JPanel panel1;
    private JLabel aLabel;
    private JTextField aTextField;
    private JLabel bLabel;
    private JTextField bTextField;
    private JTextField cTextField;
    private JLabel cLabel;
    private JLabel dLabel;
    private JTextField dTextField;
    private JLabel resultLabel;
    private JTextArea displeyTextArea;
    private JScrollPane scrollPane;
    private JButton buttonCount;
    CubicEquaction cubicEquaction=new CubicEquaction();


    UI(){
        pack();
        setContentPane(panel1);
        panel1.setSize(500,300);
        setVisible(true);
        buttonCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BigDecimal a = getA();
                BigDecimal b = getB();
                BigDecimal c = getC();
                BigDecimal d = getD();
                cubicEquaction.solve(a,b,c,d);
                setResults();



            }
        });
    }

    public  static void main(String args[]){

        UI ui =new UI();
    }

    private BigDecimal getA(){
        String aString =aTextField.getText();
         BigDecimal a=new BigDecimal(aString).setScale(12, HALF_DOWN);
         return  a;
    }

    private BigDecimal getB(){
        String bString =bTextField.getText();
        BigDecimal b=new BigDecimal(bString).setScale(12, HALF_DOWN);
        System.out.println(b);
        return  b;
    }

    private BigDecimal getC(){
        String bString =cTextField.getText();
        BigDecimal c=new BigDecimal(bString).setScale(12, HALF_DOWN);
        System.out.println(c);
        return  c;
    }


    private BigDecimal getD(){
        String bString =dTextField.getText();
        BigDecimal d=new BigDecimal(bString).setScale(12, HALF_DOWN);
        return  d;
    }

    private void setResults(){
        System.out.println(cubicEquaction.nRoots.doubleValue());
        if(cubicEquaction.nRoots.doubleValue() == 1)
        {
            String  result= "x1= "+ cubicEquaction.x1.toString();
            displeyTextArea.setText(result);
            System.out.println(result);
        }
        if (cubicEquaction.nRoots.doubleValue()!=1) {
            String resutl="x1= "+cubicEquaction.x1.toString()+System.lineSeparator()
                    +"x2 = "+cubicEquaction.x2+System.lineSeparator()
                    +"x3 = "+cubicEquaction.x3;
            displeyTextArea.setText(resutl);
            System.out.println(resutl);
           }
    }


}
