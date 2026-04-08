import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// JavaFX imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class BloodBankP extends Frame implements ActionListener {

    Label title, result;
    TextField donorField;

    Button addBtn, donateBtn, requestBtn, showBtn, exitBtn;

    String donorName = "";
    String bloodGroup = "";

    // Blood availability counters
    int Apos = 0, Aneg = 0, Bpos = 0, Bneg = 0;
    int Opos = 0, Oneg = 0, ABpos = 0, ABneg = 0;

    Choice groupChoice;

    BloodBankP() {

        setTitle("Blood Bank Management System");
        setSize(500, 320);
        setLayout(new BorderLayout());

        // Title
        title = new Label("BLOOD BANK MANAGEMENT SYSTEM", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Center Panel
        Panel center = new Panel(new GridLayout(3, 1));

        Panel donorPanel = new Panel();
        donorPanel.add(new Label("Donor Name:"));
        donorField = new TextField(15);
        donorPanel.add(donorField);
        center.add(donorPanel);

        // Blood Group Selection
        Panel groupPanel = new Panel();
        groupPanel.add(new Label("Blood Group:"));

        groupChoice = new Choice();
        groupChoice.add("A+");
        groupChoice.add("A-");
        groupChoice.add("B+");
        groupChoice.add("B-");
        groupChoice.add("O+");
        groupChoice.add("O-");
        groupChoice.add("AB+");
        groupChoice.add("AB-");

        groupPanel.add(groupChoice);
        center.add(groupPanel);

        result = new Label("Welcome", Label.CENTER);
        center.add(result);

        add(center, BorderLayout.CENTER);

        // Buttons
        Panel buttons = new Panel();

        addBtn = new Button("Add Donor");
        donateBtn = new Button("Donate");
        requestBtn = new Button("Request");
        showBtn = new Button("Show Availability");
        exitBtn = new Button("Exit");

        buttons.add(addBtn);
        buttons.add(donateBtn);
        buttons.add(requestBtn);
        buttons.add(showBtn);
        buttons.add(exitBtn);

        add(buttons, BorderLayout.SOUTH);

        // Events
        addBtn.addActionListener(this);
        donateBtn.addActionListener(this);
        requestBtn.addActionListener(this);
        showBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        bloodGroup = groupChoice.getSelectedItem();

        if (e.getSource() == addBtn) {
            donorName = donorField.getText().trim();

            if (donorName.equals("")) {
                result.setText("Enter Donor Name!");
            } else {
                result.setText("Donor Added: " + donorName);
                donorField.setText("");
            }
        }

        // Donate Blood
        if (e.getSource() == donateBtn) {
            if (donorName.equals("")) {
                result.setText("Add donor first!");
            } else {
                switch (bloodGroup) {
                    case "A+": Apos++; break;
                    case "A-": Aneg++; break;
                    case "B+": Bpos++; break;
                    case "B-": Bneg++; break;
                    case "O+": Opos++; break;
                    case "O-": Oneg++; break;
                    case "AB+": ABpos++; break;
                    case "AB-": ABneg++; break;
                }
                result.setText("Blood Donated (" + bloodGroup + ")");
            }
        }

        // Request Blood
        if (e.getSource() == requestBtn) {

            boolean available = true;

            switch (bloodGroup) {
                case "A+": if (Apos > 0) Apos--; else available = false; break;
                case "A-": if (Aneg > 0) Aneg--; else available = false; break;
                case "B+": if (Bpos > 0) Bpos--; else available = false; break;
                case "B-": if (Bneg > 0) Bneg--; else available = false; break;
                case "O+": if (Opos > 0) Opos--; else available = false; break;
                case "O-": if (Oneg > 0) Oneg--; else available = false; break;
                case "AB+": if (ABpos > 0) ABpos--; else available = false; break;
                case "AB-": if (ABneg > 0) ABneg--; else available = false; break;
            }

            if (available)
                result.setText("Blood Given (" + bloodGroup + ")");
            else
                result.setText("Blood Not Available!");
        }

        // Show Availability
        if (e.getSource() == showBtn) {
            String msg =
                    "Blood Availability:\n\n" +
                    "A+ : " + Apos + "    A- : " + Aneg + "\n" +
                    "B+ : " + Bpos + "    B- : " + Bneg + "\n" +
                    "O+ : " + Opos + "    O- : " + Oneg + "\n" +
                    "AB+ : " + ABpos + "   AB- : " + ABneg;

            JOptionPane.showMessageDialog(this, msg);
        }

        if (e.getSource() == exitBtn) {
            System.exit(0);
        }
    }

    public static void main(String args[]) {

        new BloodBankP(); // AWT

        Application.launch(JavaFXDemo.class); // JavaFX
    }
}


// ✅ JavaFX Panel
class JavaFXDemo extends Application {

    public void start(Stage stage) {

        javafx.scene.control.Label label =
                new javafx.scene.control.Label("All Blood Groups Available");

        javafx.scene.control.Button btn =
                new javafx.scene.control.Button("Click");

        btn.setOnAction(e -> {
            label.setText("A+, A-, B+, B-, O+, O-, AB+, AB-");
        });

        VBox root = new VBox(20);
        root.getChildren().addAll(label, btn);
        root.setStyle("-fx-padding:20; -fx-alignment:center;");

        Scene scene = new Scene(root, 250, 150);

        stage.setTitle("JavaFX Blood Bank");
        stage.setScene(scene);
        stage.show();
    }
}