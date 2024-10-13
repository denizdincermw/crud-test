import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class RemoveData {

       static DbData db = new DbData();
        public static void start() {

            JLabel id = new JLabel("Enter an ID to remove the whole data with");
            JFrame f = new JFrame("Remove Data");
            JButton removeBtn = new JButton("Remove");
            JTextArea idArea = new JTextArea();

            removeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    db.removeAllData(Integer.valueOf(idArea.getText()));
                }
            });

            id.setBounds(60, 10, 350, 50);
            idArea.setBounds(55, 50, 460, 40);
            id.setLabelFor(idArea);

            removeBtn.setBounds(55,580, 300 ,50);

           /* checkBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkAreaResult.append(db.getAllDataById(Integer.valueOf(checkIdArea.getText())));

                }
            });*/

            f.setVisible(true);
            f.add(removeBtn);
            f.add(id);
            f.add(idArea);
            f.setSize(640, 720);
            f.setLayout(null);
        }
}
