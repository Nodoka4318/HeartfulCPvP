package ml.heartfulcpvp.hfcpvp.modules.norender;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import ml.heartfulcpvp.hfcpvp.Plugin;
import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.modules.autocrystal.PlayerInteractEventListener;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class NoRenderModule extends Module {
    static final String NO_EXPLOSION_TEXTURE_PACK_URL = "http://sv18.kankantari.net/textures/97733db3-baa6-4f1b-bb28-ce3969b2758e/no-explosion.zip";
    static final String DEFAULT_TEXTURE_PACK_URL = "https://www.hello.com";

    public NoRenderModule() {
        super("NoRender", "norender", new PlayerData("norender"));

        // registerPacketListener(new ParticleAnimationPacketListener(this));
        registerListener(new PlayerResourcePackStatusEventListener(this));
    }

    @Override
    public void toggle(Player player) {
        if (playerData.isEnabled(player)) {
            player.setResourcePack(NO_EXPLOSION_TEXTURE_PACK_URL);

            playerData.setEnabled(player, false);
            Plugin.sendInfoMessage(player, "Disabled " + modName + ".");
        } else {
            // player.setTexturePack();

            playerData.setEnabled(player, true);
            Plugin.sendInfoMessage(player, "Enabled " + modName + ".");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            toggle(player);
            return true;
        }
        sender.sendMessage("This command is only allowed for players!");
        return false;
    }
}
