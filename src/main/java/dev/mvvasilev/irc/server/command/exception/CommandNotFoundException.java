package dev.mvvasilev.irc.server.command.exception;

public class CommandNotFoundException extends CommandException {
    public CommandNotFoundException(String cmd) {
        super("Could not find command \"" + cmd + "\"");
    }
}
