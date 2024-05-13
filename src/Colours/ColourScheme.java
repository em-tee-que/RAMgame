package Colours;

//this abstract class is used to represent a colour scheme for the game, acts as template for the schemes we can make & makes it super easy to add new schemes in future (OOP)!
public abstract class ColourScheme {
    //setting up universal colour value variables
    //these are defined in each respective colour scheme and are used throughout the other classes
    //string name used to refer to scheme
    public String name;

    //string hex codes for the four colours you play with :o
    public String colourHex1;
    public String colourHex2;
    public String colourHex3;
    public String colourHex4;

    //string hexcodes for the background & foreground of the score label in game
    public String labelForeHex;
    public String labelBackHex;
}