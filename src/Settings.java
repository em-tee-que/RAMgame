import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Component;

public class Settings {
    public void runSettings() {

        JFrame settings = new JFrame("settings");
        settings.setVisible(true);
        settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settings.setSize(1280, 720);
        settings.setLocationRelativeTo(null);
        settings.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        settings.add(panel);

        JLabel schemeSelection = new JLabel("Select colour scheme:");
        schemeSelection.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(schemeSelection);

        String[] choices = { "CHOICE 1", "CHOICE 2", "CHOICE 3", "CHOICE 4", "CHOICE 5", "CHOICE 6" };

        JComboBox<String> comboBox = new JComboBox<String>(choices);

        comboBox.setMaximumSize(comboBox.getPreferredSize());
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(comboBox);

        JButton saveChanges = new JButton("save!");
        saveChanges.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(saveChanges);
    }
}