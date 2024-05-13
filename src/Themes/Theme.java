package Themes;

//this abstract class is used to represent a theme for the menu, acts as template for the themes we can make & makes it super easy to add new themes in future (OOP)!
public abstract class Theme {
    //what is the name of the theme (used for logic and to refer to it)
    public String themeName;

    //string hex codes for button and background colours
    public String button;
    public String background;

    //string path to banner png
    public String banner;

    //string path to score banner
    public String scoreTheme;
    //string hex code for text on scoreboard
    public String scoreText;

    //string path to the theme's song
    public String song;
}