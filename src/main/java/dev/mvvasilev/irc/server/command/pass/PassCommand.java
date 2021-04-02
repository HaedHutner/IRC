package dev.mvvasilev.irc.server.command.pass;

import com.google.inject.Inject;
import dev.mvvasilev.irc.server.command.Command;
import dev.mvvasilev.irc.server.command.result.CommandError;
import dev.mvvasilev.irc.server.command.result.CommandResult;
import org.apache.commons.cli.CommandLine;

public class PassCommand implements Command {
    public static final String KEYWORD = "pass";

    @Inject
    CommandLine commandLine;

    private final String passToCheck;

    PassCommand(String passwordToCheck) {
        this.passToCheck = passwordToCheck;
    }

    @Override
    public String getKeyword() {
        return KEYWORD;
    }

    @Override
    public CommandResult execute() {
        if (passToCheck == null) {
            return CommandResult.builder().error(CommandError.ERR_NEEDMOREPARAMS).build();
        }

        var serverPassword = commandLine.getOptionValue("server-password");

        if (serverPassword == null) {
            return CommandResult.builder().success(true).build();
        }

        if (!passToCheck.equals(serverPassword)) {
            return CommandResult.builder().success(false).build();
        } else {
            return CommandResult.builder().success(true).build();
        }
    }
}
