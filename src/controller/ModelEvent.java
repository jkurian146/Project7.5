package controller;

import player.PlayerTurn;

public class ModelEvent {
    private final ModelEventType modelEventType;
    private final String message;

    public ModelEvent(ModelEventType modelEventType, String message) {
        this.modelEventType = modelEventType;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public ModelEventType getModelEventType() {
        return this.modelEventType;
    }
}
