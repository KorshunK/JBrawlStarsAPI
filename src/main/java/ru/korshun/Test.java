package ru.korshun;

import ru.korshun.api.brawler.Brawler;
import ru.korshun.api.club.Club;
import ru.korshun.api.player.Player;

public class Test {
    public static void main(String[] args) {
        BrawlStarsAPI.setApiKey("API KEY");
        Player player = BrawlStarsAPI.getPlayer("#VLQPVPY");
        player.getClub().getMembers().forEach(member -> {
            System.out.println(member.getName() + " - " + member.getRole());
        });
    }
}

