package ml.heartfulcpvp.hfcpvp.functions.playerfixer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BEPlayer {
    final static String PREFIX = "*BE_";

    public static boolean isBE(Player player) {
        return player.getName().contains(PREFIX);
    }

    public static String getRawName(Player player) {
        return player.getName().replace(PREFIX, "");
    }

    public static void fixDisplayName(Player player) {
        if (player != null) {
            Bukkit.getPlayer(player.getName()).setDisplayName(getRawName(player));
        }
    }
}
