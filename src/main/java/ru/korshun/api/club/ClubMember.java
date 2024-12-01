package ru.korshun.api.club;

import org.jetbrains.annotations.Nullable;
import ru.korshun.api.brawler.PlayerBrawler;
import ru.korshun.api.player.Battle;
import ru.korshun.api.player.Player;

import java.util.List;

public interface ClubMember extends Player {
    @Override
    String getTag();

    @Override
    String getName();

    @Override
    int getTrophies();

    @Override
    int getHighestTrophies();

    @Override
    int getExpLevel();

    @Override
    int getExpPoints();

    @Override
    boolean isQualifiedFromChampionshipChallenge();

    @Override
    int get3vs3Victories();

    @Override
    int getSoloVictories();

    @Override
    int getDuoVictories();

    @Override
    int getBestRoboRumbleTime();

    @Override
    int getBestTimeAsBigBrawler();

    @Override
    List<PlayerBrawler> getBrawlers();

    @Override
    List<Battle> getBattleLog();

    @Nullable
    @Override
    Club getClub();
    String getRole();
}
