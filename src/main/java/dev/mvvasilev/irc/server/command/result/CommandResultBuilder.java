package dev.mvvasilev.irc.server.command.result;

public class CommandResultBuilder {

    private boolean isSuccess;

    private CommandError error;

    private CommandResponse response;

    private String message;

    public CommandResultBuilder() {
    }

    public CommandResultBuilder success(boolean success) {
        isSuccess = success;
        return this;
    }

    public CommandResultBuilder error(CommandError error) {
        this.error = error;
        return this;
    }

    public CommandResultBuilder response(CommandResponse response) {
        this.response = response;
        return this;
    }

    public CommandResultBuilder message(String message) {
        this.message = message;
        return this;
    }

    public CommandResult build() {
        // if lacking positive outcome response, but a negative outcome is available, set as failed result
        if (response == null && error != null) {
            isSuccess = false;
        }

        return new CommandResult(isSuccess, error, response, message);
    }
}
