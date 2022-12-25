package ml.heartfulcpvp.hfcpvp.modules.autocrystal;

import ml.heartfulcpvp.hfcpvp.playerdata.IPlayerData;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerDataElement;
import ml.heartfulcpvp.hfcpvp.playerdata.PlayerDataManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class AutoCrystalPlayerData implements IPlayerData {
    private final File file = new File(PlayerDataManager.directory + "/autocrystal.txt");
    private List<PlayerDataElement> dataList;

    @Override
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
}
