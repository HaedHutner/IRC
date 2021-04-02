package dev.mvvasilev.irc.server.command.nick;

import dev.mvvasilev.irc.server.command.Command;
import dev.mvvasilev.irc.server.command.result.CommandResult;

public class NickCommand implements Command {
    public static final String KEYWORD = "nick";

    @Override
    public String getKeyword() {
        return KEYWORD;
    }

    @Override
    public CommandResult execute() {
        return CommandResult.empty();
    }
}
