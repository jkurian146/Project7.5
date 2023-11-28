package controller;

import player.PlayerTurn;

public class PlayerEvent {
    private final PlayerEventType playerEventType;
    private final String description;
    private final PlayerTurn executingPlayer;

    public PlayerEvent(PlayerEventType playerEventType, String description, PlayerTurn executingPlayer) {
        this.playerEventType = playerEventType;
        this.description = description;
        this.executingPlayer = executingPlayer;
    }

    public PlayerEventType getPlayerEventType() {
        return playerEventType;
    }

    public String getDescription() {
        return description;
    }

    public PlayerTurn getExecutingPlayer() {
        return executingPlayer;
    }
}
