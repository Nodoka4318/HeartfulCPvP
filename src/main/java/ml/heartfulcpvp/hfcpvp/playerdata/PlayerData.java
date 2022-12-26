package ml.heartfulcpvp.hfcpvp.playerdata;

import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class PlayerData {
    private File file;
    private ArrayList<PlayerDataElement> dataList;

    public PlayerData(String modName) {
        file = new File(PlayerDataUtils.playerDataDirectory + "/" + modName + ".txt");
        dataList = new ArrayList<>();
    }

    public void write() throws IOException {
        if (file.exists()) {
            file.delete();
        }
        Files.createFile(file.toPath());
        Files.write(
                file.toPath(),
                dataList.stream().map(PlayerDataElement::build).toList(),
                StandardCharsets.UTF_8,
                StandardOpenOption.WRITE
        );
    }

    public void load() throws IOException {
        if (!file.exists())
            file.createNewFile();

        var allLines = Files.readAllLines(file.toPath());
        for (var l : allLines) {
            dataList.add(PlayerDataElement.fromString(l));
        }
    }

    public boolean isEnabled(Player player) {
        for (var d : dataList) {
            if (d.getPlayer().equals(player.getUniqueId().toString())) {
                return d.isEnabled();
            }
        }
        return false;
    }

    public void setEnabled(Player player, boolean enabled) {
        for (var d : dataList) {
            if (d.getPlayer().equals(player.getUniqueId().toString())) {
                d.setEnabled(enabled);
                return;
            }
        }
        dataList.add(new PlayerDataElement(player.getUniqueId().toString(), enabled));
    }

    public int getSize() {
        return dataList.size();
    }
}
