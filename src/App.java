import Colours.DefaultColourScheme;

public class App {
    public static void main(String[] args) throws Exception {
        Menu mainMenu = Menu.getInstance();
        mainMenu.mainMenu(new DefaultColourScheme());
    }
}