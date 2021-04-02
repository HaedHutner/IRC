package dev.mvvasilev.irc.server.command;

import dev.mvvasilev.irc.server.command.exception.CommandUnparsableException;

public interface CommandParser {

    Command parse(String raw) throws CommandUnparsableException;

}
