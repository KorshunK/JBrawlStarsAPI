package ru.korshun.api.player;

public interface Battle {
    String getMode();
    String getResult();
    int getDuration();
    String getType();
    Player getStarPlayer();
}
