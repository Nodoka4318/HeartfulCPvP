package ml.heartfulcpvp.hfcpvp.modules.multitask;

import ml.heartfulcpvp.hfcpvp.modules.Module;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerData;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.EnumHand;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
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
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        /*
        if (sender instanceof Player player) {
            toggle(player);
            return true;
        }
        sender.sendMessage("This command is only allowed for players!");
        return false;
         */
        sender.sendMessage("this command is not implemented yet");
        return true;
    }
}