package Colours;

//basically there are lots of places where we go from name of scheme (e.g. "Pastel") to scheme object (the class, e.g. "PastelColourScheme")
//any time we need to do this (saving settings, reading file on open) we use this factory :D
public class ColourSchemeFactory {
    public static ColourScheme getColourScheme(String name) {
        if (name.equals("Default")){
            return new DefaultColourScheme();
        }
        else if (name.equals("Primary")) {
            return new PrimaryColourScheme();
        }
        else if (name.equals("Deuteranopia")) {
            return new DeuteranopiaColourScheme();
        }
        else if (name.equals("Protanopia")) {
            return new ProtanopiaColourScheme();
        }
        else if (name.equals("Pastel")) {
            return new PastelColourScheme();
        }
        else if (name.equals("Tritanopia")) {
            return new TritanopiaColourScheme();
        } else {
            return new DefaultColourScheme();
        }
    }
}
