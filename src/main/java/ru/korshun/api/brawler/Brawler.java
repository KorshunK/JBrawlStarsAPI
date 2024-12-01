package ru.korshun.api.brawler;

import java.util.List;

public interface Brawler {
    int getID();
    String getName();
    List<StarPower> getStarPowers();
    List<Gadget> getGadgets();
}
