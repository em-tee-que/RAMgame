package Themes;

//check the abstract class that this extends (Theme) for commenting

public class GalacticTheme extends Theme{
    public GalacticTheme() {
        themeName = "Galactic";

        button = "#b7abff";
        background = "#000000";
    
        banner = "src/images/titlebannergalactic.png";
        
        scoreTheme = "src/Images/scoregalactic.png";
        scoreText = "#b7abff";

        song = "src/Sounds/RAMgameThemeGalactic.wav";
    }
}
