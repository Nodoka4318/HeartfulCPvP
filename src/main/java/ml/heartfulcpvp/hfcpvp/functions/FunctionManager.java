package ml.heartfulcpvp.hfcpvp.functions;

import ml.heartfulcpvp.hfcpvp.HfcpvpException;
import ml.heartfulcpvp.hfcpvp.functions.crystalfixer.CrystalFixer;
import ml.heartfulcpvp.hfcpvp.functions.playerfixer.PlayerFixer;

import java.util.ArrayList;

public class FunctionManager {
    private ArrayList<Function> functions;

    public FunctionManager() {
        functions = new ArrayList<>();

        functions.add(new PlayerFixer());
        functions.add(new CrystalFixer());
    }

    public Function getFunction(Class functionClass) throws HfcpvpException {
        for (var f : functions) {
            if (f.getClass() == functionClass) {
                return f;
            }
        }
        throw new HfcpvpException("Class, " + functionClass.getSimpleName() + " not found.");
    }
}
