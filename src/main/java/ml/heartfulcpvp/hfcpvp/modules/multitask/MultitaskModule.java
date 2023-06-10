package ml.heartfulcpvp.hfcpvp.modules.multitask;

import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MultitaskModule extends Module {
    public MultitaskModule() {
        super("MultiTask", "multitask", new PlayerData("multitask"));

        registerListener(new Listener() {
            @EventHandler
            public void onPlayerInteract(PlayerMoveEvent event) {
                var player = event.getPlayer();
                var entityPlayer = ((CraftPlayer) player).getHandle();
                player.sendMessage(String.valueOf(entityPlayer.isHandRaised()));
                // player.sendMessage(player.getDisplayName());
            }
        });
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