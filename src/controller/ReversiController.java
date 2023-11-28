package controller;

import model.GameState;
import model.ReversiHexModel;
import model.ReversiModel;
import player.Player;
import player.PlayerTurn;
import view.ReversiGUI;
import view.ReversiView;

import java.util.ArrayList;
import java.util.List;


public class ReversiController implements ControllerListener {

    private final ReversiHexModel reversiModel;
    private final ReversiGUI reversiView;

    private final Player player;

    private ModelListener modelListener;
    private PlayerListener playerListener;
    private List<ReversiController> controllerListeners = new ArrayList<>();


    public ReversiController(ReversiHexModel reversiModel, ReversiGUI reversiView, Player player) {
        this.reversiModel = reversiModel;
        this.reversiView = reversiView;
        this.player = player;
        this.modelListener = new ModelListener();
        this.reversiModel.addListener(this.modelListener);
        this.playerListener = new PlayerListener();
        this.reversiView.addListener(this.playerListener);
    }

    public void listen() {
        PlayerTurn controllersPlayerTurn = this.player.getPlayerTurn();
        PlayerTurn modelsPlayerTurn = this.reversiModel.currentTurn();
        ModelEvent modelEvent = this.modelListener.getMostRecentEvent();
        if (modelEvent.getModelEventType() ==  ModelEventType.PLAYER1TURN) {
            this.reversiView.showPopup("It Is Now Your Turn: Player 1");
            if (modelsPlayerTurn == PlayerTurn.PLAYER1 && controllersPlayerTurn == PlayerTurn.PLAYER1) {
                // listen for a PlayerListener move
                PlayerEvent playerEvent = getNextPlayerAction();
                if (playerEvent.getExecutingPlayer() == PlayerTurn.PLAYER1) {
                    handlePlayerEvent(playerEvent);
                    this.reversiView.render();
                    this.notifyListeners();
                }
                else {
                    // display pane illegal move not your turn
                    this.reversiView.showPopup(modelEvent.getMessage());
                }
            } else {
                // display pane for illegal move: not your turn
                this.reversiView.showPopup(modelEvent.getMessage());
            }
        }
        else if (modelEvent.getModelEventType() == ModelEventType.PLAYER2TURN) {
            this.reversiView.showPopup("It Is Now Your Turn Player 2");
            if (modelsPlayerTurn == PlayerTurn.PLAYER2 && controllersPlayerTurn == PlayerTurn.PLAYER2) {
                // listen for a PlayerListener move
                PlayerEvent playerEvent = getNextPlayerAction();
                System.out.println(playerEvent.getPlayerEventType());
                if (playerEvent.getExecutingPlayer() == PlayerTurn.PLAYER2) {
                    handlePlayerEvent(playerEvent);
                    this.reversiView.render();
                    this.notifyListeners();
                }
                else {
                    this.reversiView.showPopup(modelEvent.getMessage());
                }
            } else {
                this.reversiView.showPopup(modelEvent.getMessage());
            }
        }
        else if (modelEvent.getModelEventType() == ModelEventType.TIE) {
            // display tie pane to both controllers views

        } else if (modelEvent.getModelEventType() == ModelEventType.ILLEGALMOVE) {
            // display pane to this controllers view

        } else if (modelEvent.getModelEventType() == ModelEventType.PLAYER1WON) {
            // display pane to both

        } else if (modelEvent.getModelEventType() == ModelEventType.PLAYER2WON) {
            // display pane to both

        }
    }
    private PlayerEvent getNextPlayerAction() {
        PlayerEvent nextPlayerAction = null;
        while (nextPlayerAction == null) {
            nextPlayerAction = this.playerListener.getMostRecentEvent();
        }
        this.playerListener.resetPlayerActions();
        return nextPlayerAction;
    }

    private void handlePlayerEvent(PlayerEvent playerEvent) {
        if (playerEvent.getPlayerEventType() == PlayerEventType.PASS) {
            this.reversiModel.pass();
            this.reversiView.showPopup(this.player.getPlayerTurn() + " passed");
        } else {
            String[] coordinates = playerEvent.getDescription().split(" ");
            this.reversiModel.makeMove(Integer.parseInt(coordinates[0]),
                    Integer.parseInt(coordinates[1]));
            this.reversiView.showPopup(this.player.getPlayerTurn() + " Moved to "
            + Integer.parseInt(coordinates[0]) + " " + Integer.parseInt(coordinates[1]));
        }
    }

    public void addListener(ReversiController reversiController) {
        this.controllerListeners.add(reversiController);
    }

    public void notifyListeners() {
        for (ReversiController rc: this.controllerListeners) {
            rc.handleEvent();
        }
    }
    @Override
    public void handleEvent() {
        this.reversiView.render();
    }
}
