package Colours;

//check the abstract class that this extends (ColourScheme) for commenting

public class DeuteranopiaColourScheme extends ColourScheme{
    //green-blind
    public DeuteranopiaColourScheme() {
        //a colour scheme that is accessible to those with deuteranopia colourblindness
        name = "Deuteranopia";
        
        colourHex1 = "#0130A2";
        colourHex2 = "#DCC901";
        colourHex3 = "#625B49";
        colourHex4 = "#BCBEE7";

        labelForeHex = "#FFFFFF";
        labelBackHex = "#5DC1D5";
    }
}
