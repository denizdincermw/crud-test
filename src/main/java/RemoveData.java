import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class RemoveData {

       static DbData db = new DbData();
        public static void start() {

            JLabel id = new JLabel("Enter an ID to remove the whole with");
            JFrame f = new JFrame("Remove Data");
            JButton removeBtn = new JButton("Remove");
            JButton checkBtn = new JButton("Check");
            JTextArea checkAreaResult = new JTextArea();
            JTextPane checkIdArea = new JTextPane();


            checkIdArea.setBounds(30,30,200,50);

            checkBtn.setBounds(30,80,150,50);

            checkAreaResult.setBounds(30,130,250,50);

            removeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    db.removeAllData(Integer.valueOf(checkIdArea.getText()));
                }
            });
            checkBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkAreaResult.append(db.getAllDataById(Integer.valueOf(checkIdArea.getText())));

                }
            });

            f.setVisible(true);
            f.add(removeBtn);
            f.add(id);
            f.add(checkAreaResult);
            f.add(checkBtn);
            f.add(checkIdArea);
            f.setSize(640, 720);
            f.setLayout(null);
        }
}
