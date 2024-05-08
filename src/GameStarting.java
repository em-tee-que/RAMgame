import javax.swing.JOptionPane;

import Settings.ColourScheme;

public class GameStarting {
    public void beginGame(ColourScheme selectedColourScheme){
                int ready = JOptionPane.showConfirmDialog(null, "You will be shown a sequence of colours, starting with 2 and increasing by 1 each time. Are you ready?", "...Ready For It?", JOptionPane.YES_NO_OPTION);

                if (ready == JOptionPane.YES_OPTION) {
                    Game game = new Game();
                    game.runGame(selectedColourScheme);
                }
                else {
                    
                }
    }
}