package ml.heartfulcpvp.hfcpvp.exceptions;

import java.io.Serial;

public class HfcpvpModuleClassNotRegisteredException extends HfcpvpException {
    @Serial
    private static final long serialVersionUID = 1L;

    public HfcpvpModuleClassNotRegisteredException(Class cls) {
        super("Module, " + cls.getSimpleName() + " was not registered!");
    }
}
