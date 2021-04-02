package dev.mvvasilev.irc.server.command.exception;

import dev.mvvasilev.irc.server.command.Command;

public class CommandException extends Exception {

    public CommandException(String str) {
        super(str);
    }

}
