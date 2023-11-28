import controller.ReversiController;
import model.ReversiHexModel;
import player.Player;
import player.PlayerTurn;
import view.ReversiGUI;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ReversiHexModel model = new ReversiHexModel();
        model.startGame(5);
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

