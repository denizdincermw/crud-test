import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class SaveDataWindow {

    static JTextPane tfHeader = new JTextPane();
    static JTextPane tfContext = new JTextPane();
    static JTextPane tfDate = new JTextPane();
    static JTextPane tfId = new JTextPane();
    public static void start() {
        DbData db = new DbData();
        JFrame f = new JFrame("Set Data");
        JButton saveBtn = new JButton("Save");
        //JTextArea tArea = new JTextArea();

        JLabel hdr, cntxt, date, id;
        hdr = new JLabel("Enter a header");
        cntxt = new JLabel("Enter a context");
        date = new JLabel("Enter a date (YYYY-MM-DD)");
        id = new JLabel("Enter an ID (The ID must be unique)");

        hdr.setBounds(60, 10, 150, 50);
        tfHeader.setBounds(55, 50, 460, 40);
        hdr.setLabelFor(tfHeader);

        cntxt.setBounds(60, 85, 150, 50);
        tfContext.setBounds(55, 125, 460, 420);
        cntxt.setLabelFor(tfContext);

        date.setBounds(60,540,250,50);
        tfDate.setBounds(55,580,150,20);
        date.setLabelFor(tfDate);

        id.setBounds(315,540,250,50);
        tfId.setBounds(310,580,150,20);
        date.setLabelFor(tfDate);

        saveBtn.setBounds(55,610,150,50);

        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db.setAllData(tfHeader.getText(),tfContext.getText(),Date.valueOf(tfDate.getText()),Integer.valueOf(tfId.getText()));

            }
        });

        f.setVisible(true);
        f.add(hdr);
        f.add(cntxt);
        f.add(date);
        f.add(id);
        f.add(tfHeader);
        f.add(tfContext);
        f.add(tfDate);
        f.add(tfId);
        f.add(saveBtn);
        f.setSize(640, 720);
        f.setLayout(null);
    }
}
