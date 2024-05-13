package Colours;

//check the abstract class that this extends (ColourScheme) for commenting

public class TritanopiaColourScheme extends ColourScheme{
    //blue-yellow blind
    public TritanopiaColourScheme(){
        //a colour scheme that is accessible to those with tritanopia colourblindness
        name = "Tritanopia";
        
        colourHex1 = "#fe296b";
        colourHex2 = "#61d2fa";
        colourHex3 = "#fac5d1";
        colourHex4 = "#364f69";

        labelForeHex = "#FFFFFF";
        labelBackHex = "#6A4C93";
    }
}
