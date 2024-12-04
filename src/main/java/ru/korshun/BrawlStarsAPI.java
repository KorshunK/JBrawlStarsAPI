package ru.korshun;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.korshun.api.brawler.Brawler;
import ru.korshun.api.brawler.Gadget;
import ru.korshun.api.brawler.PlayerBrawler;
import ru.korshun.api.brawler.StarPower;
import ru.korshun.api.club.Club;
import ru.korshun.api.club.ClubMember;
import ru.korshun.api.player.Battle;
import ru.korshun.api.player.Player;
import ru.korshun.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BrawlStarsAPI {
    private static List<Brawler> brawlers = new ArrayList<>();
    private static String API_KEY = "";
    public static void main(String[] args) {
        checkVersion();
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static void setApiKey(String apiKey) {
        checkVersion();
        API_KEY = apiKey;
    }

    private static void loadBrawlers() {
        JSONArray array = JsonUtils.readJsonFromUrl("https://api.brawlify.com/v1/brawlers").getJSONArray("list");
        for(Object o : array) {
            JSONObject b = (JSONObject) o;
            brawlers.add(new Brawler() {
                @Override
                public int getID() {
                    return b.getInt("id") - 16000000;
                }

                @Override
                public String getName() {
                    return b.getString("name");
                }

                @Override
                public List<StarPower> getStarPowers() {
                    List<StarPower> sps = new ArrayList<>();
                    for(Object sp : b.getJSONArray("starPowers")) {
                        JSONObject starPower = (JSONObject) sp;
                        sps.add(new StarPower() {
                            @Override
                            public int getID() {
                                return starPower.getInt("id") - 23000000;
                            }

                            @Override
                            public String getName() {
                                return starPower.getString("name");
                            }
                        });
                    }
                    return sps;
                }

                @Override
                public List<Gadget> getGadgets() {
                    List<Gadget> gadgets = new ArrayList<>();
                    for(Object gd : b.getJSONArray("gadgets")) {
                        JSONObject gadget = (JSONObject) gd;
                        gadgets.add(new Gadget() {
                            @Override
                            public int getID() {
                                return gadget.getInt("id");
                            }

                            @Override
                            public String getName() {
                                return gadget.getString("name");
                            }
                        });
                    }
                    return gadgets;
                }
            });
        }
    }

    public static List<PlayerBrawler> getPlayerBrawlers(Player player) {
        List<PlayerBrawler> brawlers1 = new ArrayList<>();
        JSONArray array = JsonUtils.readJsonFromUrl("https://api.brawlstars.com/v1/players/" + player.getTag()).getJSONArray("brawlers");
        for(Object o : array) {
            JSONObject object = (JSONObject) o;
            brawlers1.add(new PlayerBrawler() {
                @Override
                public int getID() {
                    return object.getInt("id") - 16000000;
                }

                @Override
                public String getName() {
                    return object.getString("name");
                }

                @Override
                public List<StarPower> getStarPowers() {
                    List<StarPower> sps = new ArrayList<>();
                    for(Object sp : object.getJSONArray("starPowers")) {
                        JSONObject starPower = (JSONObject) sp;
                        sps.add(new StarPower() {
                            @Override
                            public int getID() {
                                return starPower.getInt("id") - 23000000;
                            }

                            @Override
                            public String getName() {
                                return starPower.getString("name");
                            }
                        });
                    }
                    return sps;
                }

                @Override
                public List<Gadget> getGadgets() {
                    List<Gadget> gadgets = new ArrayList<>();
                    for(Object gd : object.getJSONArray("gadgets")) {
                        JSONObject gadget = (JSONObject) gd;
                        gadgets.add(new Gadget() {
                            @Override
                            public int getID() {
                                return gadget.getInt("id");
                            }

                            @Override
                            public String getName() {
                                return gadget.getString("name");
                            }
                        });
                    }
                    return gadgets;
                }

                @Override
                public int getPower() {
                    return object.getInt("power");
                }

                @Override
                public int getRank() {
                    return object.getInt("rank");
                }

                @Override
                public int getTrophies() {
                    return object.getInt("trophies");
                }

                @Override
                public int getHighestTrophies() {
                    return object.getInt("highestTrophies");
                }
            });
        }
        return brawlers1;
    }

    public static Player getPlayer(String tag) {
        JSONObject o = JsonUtils.readJsonFromUrl("https://api.brawlstars.com/v1/players/" + tag);
        if (o == null) {
            return null;
        }
        return new Player() {
            @Override
            public String getTag() {
                return tag;
            }

            @Override
            public String getName() {
                return o.getString("name");
            }

            @Override
            public int getTrophies() {
                return o.getInt("trophies");
            }

            @Override
            public int getHighestTrophies() {
                return o.getInt("highestTrophies");
            }

            @Override
            public int getExpLevel() {
                return o.getInt("expLevel");
            }

            @Override
            public int getExpPoints() {
                return o.getInt("expPoints");
            }

            @Override
            public boolean isQualifiedFromChampionshipChallenge() {
                return o.getBoolean("isQualifiedFromChampionshipChallenge");
            }

            @Override
            public int get3vs3Victories() {
                return o.getInt("3vs3Victories");
            }

            @Override
            public int getSoloVictories() {
                return o.getInt("soloVictories");
            }

            @Override
            public int getDuoVictories() {
                return o.getInt("duoVictories");
            }

            @Override
            public int getBestRoboRumbleTime() {
                return o.getInt("bestRoboRumbleTime");
            }

            @Override
            public int getBestTimeAsBigBrawler() {
                return o.getInt("bestTimeAsBigBrawler");
            }

            @Override
            public List<PlayerBrawler> getBrawlers() {
                return getPlayerBrawlers(this);
            }

            @Override
            public List<Battle> getBattleLog() {
                JSONObject o1 = JsonUtils.readJsonFromUrl("https://api.brawlstars.com/v1/players/" + tag + "/battlelog");
                List<Battle> battles = new ArrayList<>();
                for(Object object : o1.getJSONArray("items")) {
                    JSONObject jsonObject = ((JSONObject) object).getJSONObject("battle");
                    battles.add(new Battle() {
                        @Override
                        public String getMode() {
                            return jsonObject.getString("mode");
                        }

                        @Override
                        public String getResult() {
                            if(getMode().contains("Showdown")) {
                                return String.valueOf(jsonObject.getInt("rank"));
                            }
                            return jsonObject.getString("result");
                        }

                        @Override
                        public int getDuration() {
                            return jsonObject.getInt("duration");
                        }

                        @Override
                        public String getType() {
                            return jsonObject.getString("type");
                        }

                        @Override
                        public Player getStarPlayer() {
                            if(getMode().contains("Showdown")) {
                                return null;
                            }
                            try {
                                return getPlayer(jsonObject.getJSONObject("starPlayer").getString("tag"));
                            } catch (NullPointerException e) {}
                            return null;
                        }
                    });
                }
                return battles;
            }

            @Nullable
            @Override
            public Club getClub() {
                try {
                    return BrawlStarsAPI.getClub(o.getJSONObject("club").getString("tag"));
                } catch (JSONException e) {
                    return null;
                }
            }
        };
    }

    public static Club getClub(String tag) {
        JSONObject jsonObject = JsonUtils.readJsonFromUrl("https://api.brawlstars.com/v1/clubs/" + tag);
        return new Club() {
            @Override
            public String getTag() {
                return jsonObject.getString("tag");
            }

            @Override
            public String getName() {
                return jsonObject.getString("name");
            }

            @Override
            public String getDescription() {
                return jsonObject.getString("description");
            }

            @Override
            public int getTrophies() {
                return jsonObject.getInt("trophies");
            }

            @Override
            public int getRequiredTrophies() {
                return jsonObject.getInt("requiredTrophies");
            }

            @Override
            public List<ClubMember> getMembers() {
                List<ClubMember> members = new ArrayList<>();
                JSONObject jsonMembers = JsonUtils.readJsonFromUrl("https://api.brawlstars.com/v1/clubs/" + tag + "/members");
                for(Object o : jsonMembers.getJSONArray("items")) {
                    JSONObject jsonObject1 = (JSONObject) o;
                    members.add(getClubMember(this.getTag(), getPlayer(jsonObject1.getString("tag"))));
                }
                return members;
            }
        };
    }

    public static ClubMember getClubMember(String clubTag, Player player) {
        JSONObject club = JsonUtils.readJsonFromUrl("https://api.brawlstars.com/v1/clubs/" + clubTag);
        for(Object o : club.getJSONArray("members")) {
            JSONObject jsonObject = (JSONObject) o;
            if(player.getTag().equals(jsonObject.getString("tag"))) {
                return new ClubMember() {
                    @Override
                    public String getTag() {
                        return jsonObject.getString("tag");
                    }

                    @Override
                    public String getName() {
                        return player.getName();
                    }

                    @Override
                    public int getTrophies() {
                        return player.getTrophies();
                    }

                    @Override
                    public int getHighestTrophies() {
                        return player.getHighestTrophies();
                    }

                    @Override
                    public int getExpLevel() {
                        return player.getExpLevel();
                    }

                    @Override
                    public int getExpPoints() {
                        return player.getExpPoints();
                    }

                    @Override
                    public boolean isQualifiedFromChampionshipChallenge() {
                        return player.isQualifiedFromChampionshipChallenge();
                    }

                    @Override
                    public int get3vs3Victories() {
                        return player.get3vs3Victories();
                    }

                    @Override
                    public int getSoloVictories() {
                        return player.getSoloVictories();
                    }

                    @Override
                    public int getDuoVictories() {
                        return player.getDuoVictories();
                    }

                    @Override
                    public int getBestRoboRumbleTime() {
                        return player.getBestRoboRumbleTime();
                    }

                    @Override
                    public int getBestTimeAsBigBrawler() {
                        return player.getBestTimeAsBigBrawler();
                    }

                    @Override
                    public List<PlayerBrawler> getBrawlers() {
                        return player.getBrawlers();
                    }

                    @Override
                    public List<Battle> getBattleLog() {
                        return player.getBattleLog();
                    }

                    @Nullable
                    @Override
                    public Club getClub() {
                        return BrawlStarsAPI.getClub(clubTag);
                    }

                    @Override
                    public String getRole() {
                        return jsonObject.getString("role");
                    }
                };
            }
        }
        return null;
    }

    public static HashMap<String, Brawler> getBrawlersNames() {
        loadBrawlers();
        HashMap<String, Brawler> b = new HashMap<>();
        for(Brawler brawler : getBrawlers()) {
            b.put(brawler.getName(), brawler);
        }
        return b;
    }

    private static void checkVersion() {
        if(getVersion() == null) exit(0);
        if(getVersion().equals("1.0-SNAPSHOT")) {
            API_KEY = null;
            System.out.println("You need use API version bigger than 1.0-SNAPSHOT");
            exit(0);
        }
    }

    public static void exit(int code) {
        System.exit(code);
    }

    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(BrawlStarsAPI.class.getClassLoader().getResourceAsStream("api.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static String getVersion() {
        return getProperties().getProperty("version");
    }

    public static List<Brawler> getBrawlers() {
        loadBrawlers();
        return brawlers;
    }
}