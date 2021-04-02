package dev.mvvasilev.irc.server.command.user;

import dev.mvvasilev.irc.server.command.Command;
import dev.mvvasilev.irc.server.command.result.CommandResult;

public class UserCommand implements Command {
    public static final String KEYWORD = "user";

    @Override
    public String getKeyword() {
        return KEYWORD;
    }

    @Override
    public CommandResult execute() {

    }
}
