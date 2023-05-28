package ml.heartfulcpvp.hfcpvp.modules.norender;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.injector.server.TemporaryPlayer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import ml.heartfulcpvp.hfcpvp.Plugin;

public class ParticleAnimationPacketListener extends PacketAdapter {
    private NoRenderModule mod;

    public ParticleAnimationPacketListener(NoRenderModule mod) {
        super(
                Plugin.getPlugin(),
                ListenerPriority.NORMAL
        );

        this.mod = mod;
    }

    @Override
    public void onPacketSending(PacketEvent e) {
        if (e.getPlayer() instanceof TemporaryPlayer || e.getPlayer() == null) {
            return;
        }

        if (!mod.getPlayerData().isEnabled(e.getPlayer())) {
            return;
        }

        if (e.getPacketType() == PacketType.Play.Server.WORLD_PARTICLES) {
            e.setCancelled(true);
        }

        if (e.getPacketType() == PacketType.Play.Server.EXPLOSION) {
            e.setCancelled(true);
        }
    }
}
