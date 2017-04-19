package kalahProject;

import UI.AppMainWindow;

/**
 * Created by jhinchley on 3/14/17.
 * this is the main class that allows communication between the Model and its observers/Views
 */
public class Game {
    public static void main(String args[]) {
        //start a GUI
        AppMainWindow window = new AppMainWindow();
        window.setVisible();
    }
}