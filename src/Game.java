import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class Game {
    public void game(String[] args) throws Exception {
        JOptionPane intro = JOptionPane.showMessageDialog(null, args, null, 0);
        //UIManager introUI = new UIManager();

        UIManager.put("intro.background", new ColorUIResource(81, 152, 114));

        intro.setSize(1280, 720);
        intro.setLayout(null);
    }
}