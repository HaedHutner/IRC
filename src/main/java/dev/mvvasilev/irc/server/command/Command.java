package dev.mvvasilev.irc.server.command;

import dev.mvvasilev.irc.server.command.result.CommandResult;

public interface Command {

    String getKeyword();

    CommandResult execute();

}
