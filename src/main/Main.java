package main;

import controller.PuzzleController;
import view.ChooseView;
import view.IntroView;

public class Main {
    public static void main(String[] args) {
       
        IntroView introView = new IntroView();
        introView.setVisible(true);
        ChooseView chooseView = new ChooseView();

        PuzzleController controller = new PuzzleController(introView, chooseView);
    }
}
