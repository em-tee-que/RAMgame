package Themes;

//check the abstract class that this extends (Theme) for commenting

public class MedievalTheme extends Theme{
    public MedievalTheme() {
        themeName = "Medieval";

        button = "#FAD643";
        background = "#2a2c2d";
    
        banner = "src/images/titlebannermedieval.png";
    
        scoreTheme = "src/Images/scoremedieval.png";
        scoreText = "#FAD643";

        song = "src/Sounds/RAMgameThemeMedieval.wav";
    }
}
