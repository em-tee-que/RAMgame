package Colours;

//check the abstract class that this extends (ColourScheme) for commenting

public class PrimaryColourScheme extends ColourScheme{
    public PrimaryColourScheme() {
        //a saturated version of the default colour scheme
        name = "Primary";
        
        colourHex1 = "#FF0000";
        colourHex2 = "#00FF00";
        colourHex3 = "#0000FF";
        colourHex4 = "#000000";

        labelForeHex = "#FFFFFF";
        labelBackHex = "#C6C6C6";
    }
}
