package ru.korshun.api.player;

import org.jetbrains.annotations.Nullable;
import ru.korshun.api.brawler.Brawler;
import ru.korshun.api.brawler.PlayerBrawler;
import ru.korshun.api.club.Club;

import java.util.List;

public interface Player {
    String getTag();
    String getName();
    int getTrophies();
    int getHighestTrophies();
    int getExpLevel();
    int getExpPoints();
    boolean isQualifiedFromChampionshipChallenge();
    int get3vs3Victories();
    int getSoloVictories();
    int getDuoVictories();
    int getBestRoboRumbleTime();
    int getBestTimeAsBigBrawler();
    List<PlayerBrawler> getBrawlers();
    List<Battle> getBattleLog();
    @Nullable Club getClub();
}
