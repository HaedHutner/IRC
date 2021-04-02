package dev.mvvasilev.irc.server.command.result;

public enum CommandError {
    ERR_NEEDMOREPARAMS(461);

    private final int errorCode;

    private CommandError(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
