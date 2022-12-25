package ml.heartfulcpvp.hfcpvp.playerdata;

import java.io.File;

public class PlayerDataManager {
    public static final File directory = new File("./plugins/HeartfulCPvP/playerdata");

    public static void createDirectory() {
        if (!directory.exists())
            directory.mkdir();
    }
}
