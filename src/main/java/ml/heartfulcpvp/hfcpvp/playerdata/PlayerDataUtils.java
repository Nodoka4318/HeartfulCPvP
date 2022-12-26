package ml.heartfulcpvp.hfcpvp.playerdata;

import java.io.File;

public class PlayerDataUtils {
    public static final File baseDirectory = new File("./plugins/HeartfulCPvP");
    public static final File playerDataDirectory = new File("./plugins/HeartfulCPvP/playerdata");

    public static void createDirectory() {
        if (!baseDirectory.exists())
            baseDirectory.mkdir();
        if (!playerDataDirectory.exists())
            playerDataDirectory.mkdir();
    }
}
