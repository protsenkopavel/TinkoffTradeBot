package net.protsenko.core;

import lombok.Data;

@Data
public class Parameters {
    private final static int ARGUMENTS_NUMBER = 2;
    private String token;
    private boolean sandBoxMode;

    public Parameters(String token, boolean sandBoxMode) {
        setParameter(token, sandBoxMode);
    }

    public Parameters(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(String.format(
                    "Invalid number of arguments [%d], excepted [%d]",
                    args.length, ARGUMENTS_NUMBER));
        }
        setParameter(args[0], Boolean.parseBoolean(args[1]));
    }

    private void setParameter(String token, boolean sandBoxMode) {
        this.token = token;
        this.sandBoxMode = sandBoxMode;
    }

    @Override
    public String toString() {
        return String.format("core.Parameters: sandBoxMode = %s", sandBoxMode ? "true" : "false");
    }
}
