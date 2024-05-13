package Themes;

//check the abstract class that this extends (Theme) for commenting

public class SpookyTheme extends Theme {
    public SpookyTheme() {
        themeName = "Spooky";

        button = "#000000";
        background = "#ee9900";
    
        banner = "src/images/titlebannerspooky.png";

        scoreTheme = "src/Images/scorespooky.png";
        scoreText = "#FFFFFF";
    
        song = "src/Sounds/RAMgameThemeSpooky.wav";
    }
}