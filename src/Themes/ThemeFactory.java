package Themes;

//this factory is used to remove repetitive code for getting the correct theme based on name (from combo boxes or txt file)
//used in settings (to return theme based on name selected in combo box) and in app (to return theme based on name saved in txt file)
public class ThemeFactory {
    public static Theme getTheme(String themeName) {
        //if the name is default, return default... etc.
        if (themeName.equals("Default")){
            return new DefaultTheme();
        }
        if (themeName.equals("Medieval")){
            return new MedievalTheme();
        }
        if (themeName.equals("Spooky")) {
            return new SpookyTheme();
        }
        if (themeName.equals("Galactic")) {
            return new GalacticTheme();
        }
        //if something goes wrong just use default, shouldn't happen
        return new DefaultTheme();
    }

}
