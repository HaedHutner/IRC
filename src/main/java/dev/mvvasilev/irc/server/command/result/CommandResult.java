package dev.mvvasilev.irc.server.command.result;

public final class CommandResult {

    private boolean isSuccess;

    private CommandError error;

    private CommandResponse response;

    private String message;

    public static CommandResultBuilder builder() {
        return new CommandResultBuilder();
    }

    public CommandResult(boolean isSuccess, CommandError error, CommandResponse response, String message) {
        this.isSuccess = isSuccess;
        this.error = error;
        this.response = response;
        this.message = message;
    }

    public static CommandResult empty() {
        return new CommandResult(true, null, null, null);
    }

    public static CommandResult error(CommandError error, String message) {
        return new CommandResult(false, error, null, message);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public CommandError getError() {
        return error;
    }

    public CommandResponse getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }
}
