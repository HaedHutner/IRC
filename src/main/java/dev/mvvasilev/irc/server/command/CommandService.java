package dev.mvvasilev.irc.server.command;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import dev.mvvasilev.irc.server.command.exception.CommandException;
import dev.mvvasilev.irc.server.command.exception.CommandNotFoundException;

@Singleton
public class CommandService {

    @Inject
    Injector injector;

    @Inject
    CommandRegistry registry;

    public void interpret(String msg) throws CommandException {
        var cmd = msg.split(" ")[0].toLowerCase();

        CommandParser parser;

        if (registry.containsKey(cmd)) {
            parser = registry.get(cmd).get();
        } else {
            throw new CommandNotFoundException(cmd);
        }

        var command = parser.parse(msg);

        injector.injectMembers(command);

        command.execute();
    }
}
