import java.io.File;
import java.util.Scanner;

import Colours.ColourScheme;
import Colours.DefaultColourScheme;

public class App {
    public static void main(String[] args) throws Exception {

        //deafult values, below if saved preferences file alreaady exists, will be overwritten
        ColourScheme scheme = new DefaultColourScheme();
        boolean mode = false;

        String path = (System.getProperty("user.dir") + "\\savedData.txt");
        File file = new File(path);

        if (file.exists()) {
            Scanner reader = new Scanner(file);
            String savedColour = reader.nextLine();
            scheme = Colours.ColourSchemeFactory.getColourScheme(savedColour);
            mode = Boolean.parseBoolean(reader.nextLine());
            reader.close();
        }
        Menu mainMenu = Menu.getInstance();
        mainMenu.mainMenu(scheme, mode);
    }
}