package ml.heartfulcpvp.hfcpvp.playerdata;

import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.Map;

public class PlayerDataElement {
    private Map<String, String> data;
    private String player;

    public PlayerDataElement(String playerUuid) {
        player = playerUuid;
        data = new HashMap<>();
    }

    public void saveData(String key, String value) {
        if (data.containsKey(key))
            data.replace(key, value);
        else
            data.put(key, value);
    }

    public String getData(String key) throws InvalidObjectException {
        if (!data.containsKey(key))
            throw new InvalidObjectException("Key, '" + key + "' was not found.");
        return data.get(key);
    }

    // 我流フォーマットごめん
    public String build() {
        StringBuilder str = new StringBuilder(player);
        for (var key : data.keySet()) {
            str.append(":").append(key).append("$").append(data.get(key));
        }
        return str.toString();
    }

    public static PlayerDataElement fromString(String str) {
        var pars = str.split(":");
        var elem = new PlayerDataElement(pars[0]);
        for (int i = 1; i < pars.length; i++) {
            var vals = pars[i].split("\\$");
            elem.saveData(vals[0], vals[1]);
        }
        return elem;
    }
}
