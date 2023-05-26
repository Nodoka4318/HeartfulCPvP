package ml.heartfulcpvp.hfcpvp.playerdata;

import ml.heartfulcpvp.hfcpvp.HfcpvpException;

import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.Map;

public class PlayerDataElement {
    private Map<String, String> data;
    private String player;
    private boolean isEnabled;

    public PlayerDataElement(String playerUuid, boolean isEnabled) {
        player = playerUuid;
        this.isEnabled = isEnabled;
        data = new HashMap<>();
    }

    public void saveData(String key, String value) {
        if (data.containsKey(key))
            data.replace(key, value);
        else
            data.put(key, value);
    }

    public String getData(String key) throws HfcpvpException {
        if (!data.containsKey(key))
            throw new HfcpvpException("Key, '" + key + "' was not found.");
        return data.get(key);
    }

    // 我流フォーマットごめん
    public String build() {
        StringBuilder str = new StringBuilder(player + ":" + isEnabled);
        for (var key : data.keySet()) {
            str.append(":").append(key).append("$").append(data.get(key));
        }
        return str.toString();
    }

    public static PlayerDataElement fromString(String str) {
        var pars = str.split(":");
        var elem = new PlayerDataElement(pars[0], Boolean.getBoolean(pars[1])); // TODO: getBooleanでbool値が取得できてない？
        for (int i = 2; i < pars.length; i++) {
            var vals = pars[i].split("\\$");
            elem.saveData(vals[0], vals[1]);
        }
        return elem;
    }

    public String getPlayer() {
        return player;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
