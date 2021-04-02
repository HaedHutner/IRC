package dev.mvvasilev.irc.server.command.exception;

public class CommandUnparsableException extends CommandException {
    public CommandUnparsableException(String cmd) {
        super("Failed to parse command \"" + cmd + "\".");
    }
}
