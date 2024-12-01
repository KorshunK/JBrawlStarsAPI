package ru.korshun.api.club;

import ru.korshun.api.player.Player;

import java.util.List;

public interface Club {
    String getTag();
    String getName();
    String getDescription();
    int getTrophies();
    int getRequiredTrophies();
    List<ClubMember> getMembers();
}
