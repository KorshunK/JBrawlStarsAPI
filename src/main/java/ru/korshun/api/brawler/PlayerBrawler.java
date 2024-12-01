package ru.korshun.api.brawler;

import java.util.List;

public interface PlayerBrawler extends Brawler {
    @Override
    int getID();
    @Override
    String getName();
    @Override
    List<StarPower> getStarPowers();
    @Override
    List<Gadget> getGadgets();
    int getPower();
    int getRank();
    int getTrophies();
    int getHighestTrophies();
}
