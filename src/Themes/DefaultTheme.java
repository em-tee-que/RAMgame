package Themes;

//check the abstract class that this extends (Theme) for commenting

public class DefaultTheme extends Theme {
    public DefaultTheme() {
        themeName = "Default";

        button = "#CCF7B5";
        background = "#3B6A48";
    
        banner = "src/images/titlebanner.png";
    
        scoreTheme = "src/Images/scoredefault.png";
        scoreText = "#CCF7B5";

        song = "src/Sounds/RAMgameTheme.wav";
    }
}