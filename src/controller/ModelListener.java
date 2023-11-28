package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelListener {
    private List<ModelEvent> modeleventlog =
            new ArrayList(Arrays.asList(new ModelEvent(ModelEventType.PLAYER1TURN, "It's Your Turn Player 1")));
    public void update(ModelEvent modelEvent) {
        this.modeleventlog.add(modelEvent);
    }

    public ModelEvent getMostRecentEvent() {
        ModelEvent mostRecentEvent = modeleventlog.get(modeleventlog.size() - 1);
        return mostRecentEvent;
    }
}
