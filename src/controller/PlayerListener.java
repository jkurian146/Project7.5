package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerListener {
    private List<PlayerEvent> playereventlog = new ArrayList();
    public void update(PlayerEvent playerEvent) {
        this.playereventlog.add(playerEvent);
    }

    public PlayerEvent getMostRecentEvent() {
        try {
            PlayerEvent mostRecentEvent = playereventlog.get(playereventlog.size() - 1);
            return mostRecentEvent;
        } catch (Exception e) {
            return null;
        }
    }

    public void resetPlayerActions() {
        this.playereventlog.clear();
    }
}
