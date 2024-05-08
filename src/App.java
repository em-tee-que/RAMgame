import Colours.DefaultColourScheme;

public class App {
    public static void main(String[] args) throws Exception {
        Menu mainMenu = new Menu();
        mainMenu.mainMenu(new DefaultColourScheme());
    }
}