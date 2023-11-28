import controller.ReversiController;
import model.ReversiHexModel;
import model.ReversiHexModelAI;
import player.Player;
import player.PlayerTurn;
import strategy.StrategyType;
import view.ReversiGUI;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        if (true) {
//            makeAiGame(7,StrategyType.MAXIMIZE);
//        }
        int boardsize = 11;
        String strategy = "none";

        try {
            boardsize = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your Board" +
                    " Size:"));
        } catch (NumberFormatException nfe) {
            // do nothing
        }
        if (args.length < 2) {
            makeStandardGame(boardsize);
        } else if (args.length == 2){
            String param1 = args[0];
            String param2 = args[1];
            if (param1.equalsIgnoreCase("human") && (param2.equalsIgnoreCase("strategy1") ||
                    param2.equalsIgnoreCase("strategy2")
                    || param2.equalsIgnoreCase("strategy3")
                    || param2.equalsIgnoreCase("strategy4"))) {
                StrategyType st;
                switch (param2) {
                    case "strategy1":
                        st = StrategyType.MAXIMIZE;
                    case "strategy2":
                        st = StrategyType.AVOIDCORNER;
                    case "strategy3":
                        st = StrategyType.GOFORCORNER;
                    case "strategy4":
                        st = StrategyType.MINIMAX;
                    default:
                        st = StrategyType.MAXIMIZE;
                }
                makeAiGame(boardsize,st);
            } else if (param2.equalsIgnoreCase("human") && (param1.equalsIgnoreCase("strategy1")
                    || param1.equalsIgnoreCase("strategy2")
                    || param1.equalsIgnoreCase("strategy3")
                    || param1.equalsIgnoreCase("strategy4"))) {
                StrategyType st;
                switch (param1) {
                    case "strategy1":
                        st = StrategyType.MAXIMIZE;
                    case "strategy2":
                        st = StrategyType.AVOIDCORNER;
                    case "strategy3":
                        st = StrategyType.GOFORCORNER;
                    case "strategy4":
                        st = StrategyType.MINIMAX;
                    default:
                        st = StrategyType.MAXIMIZE;
                }
                makeAiGame(boardsize,st);
            } else {
                makeStandardGame(boardsize);
            }
        } else {
            makeStandardGame(boardsize);
        }
    }

    private static void makeStandardGame(int boardsize) {
        ReversiHexModel model = new ReversiHexModel();
        model.startGame(boardsize);
        ReversiGUI viewPlayer1 = new ReversiGUI(model);
        ReversiGUI viewPlayer2 = new ReversiGUI(model);
        Player player1 = new Player(PlayerTurn.PLAYER1);
        Player player2 = new Player(PlayerTurn.PLAYER2);
        ReversiController controller1 = new ReversiController(model, viewPlayer1, player1);
        ReversiController controller2 = new ReversiController(model, viewPlayer2, player2);
        controller1.addListener(controller2);
        controller2.addListener(controller1);
        while (!model.isGameOver()) {
            controller1.listen();
            controller2.listen();
        }
    }

    private static void makeAiGame(int boardsize, StrategyType strategyType) {
        ReversiHexModelAI model = new ReversiHexModelAI(strategyType);
        model.startGame(boardsize);
        ReversiGUI viewPlayer1 = new ReversiGUI(model);
        ReversiGUI viewPlayer2 = new ReversiGUI(model);
        Player player1 = new Player(PlayerTurn.PLAYER1);
        Player player2 = new Player(PlayerTurn.PLAYER2);
        ReversiController controller1 = new ReversiController(model, viewPlayer1, player1);
        ReversiController controller2 = new ReversiController(model, viewPlayer2, player2);
        controller1.addListener(controller2);
        controller2.addListener(controller1);
        while (!model.isGameOver()) {
            controller1.listen();
            controller2.listen();
        }
    }
}

