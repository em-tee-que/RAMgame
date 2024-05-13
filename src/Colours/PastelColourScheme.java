package Colours;

//check the abstract class that this extends (ColourScheme) for commenting

public class PastelColourScheme extends ColourScheme{
    public PastelColourScheme() {
        //a pastel colour scheme that is not only cute but makes the game a bit harder (less contrast between colour switches)
        name = "Pastel";
        
        colourHex1 = "#FF9DCB";
        colourHex2 = "#9EBDFF";
        colourHex3 = "#9BF7BE";
        colourHex4 = "#FFF6A2";

        labelForeHex = "#FFFFFF";
        labelBackHex = "#FFC097";
    }
}
