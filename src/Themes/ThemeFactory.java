package Themes;

public class ThemeFactory {
    public static Theme getTheme(String themeName) {
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
        return new DefaultTheme();
    }

}
