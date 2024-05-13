// importing input/output readers and the different colour schemes and themes
import java.io.File;
import java.util.Scanner;

import Colours.ColourScheme;
import Colours.DefaultColourScheme;
import Themes.DefaultTheme;
import Themes.Theme;

public class App {
    //the main method
    public static void main(String[] args) throws Exception {

        //default values, below if saved preferences file alreaady exists, will be overwritten
        ColourScheme scheme = new DefaultColourScheme();
        boolean mode = false;
        boolean sound = true;
        Theme theme = new DefaultTheme();

        //get path as string
        String path = (System.getProperty("user.dir") + "\\savedData.txt");
        File file = new File(path);

        //if there are saved preferences than the variables for settings are overwritten
        if (file.exists()) {
            Scanner reader = new Scanner(file);
            //get saved colour scheme preference
            String savedColour = reader.nextLine();
            scheme = Colours.ColourSchemeFactory.getColourScheme(savedColour);
            //is hard mode on?
            mode = Boolean.parseBoolean(reader.nextLine());
            //is sound on?
            sound = Boolean.parseBoolean(reader.nextLine());
            //get saved menu theme
            String savedTheme = reader.nextLine();
            theme = Themes.ThemeFactory.getTheme(savedTheme);
            reader.close();
        }

        //run main menu and pass it the preferences
        Menu mainMenu = Menu.getInstance();
        mainMenu.mainMenu(scheme, mode, sound, theme);
    }
}