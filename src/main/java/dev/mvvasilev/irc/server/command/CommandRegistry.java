package dev.mvvasilev.irc.server.command;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import dev.mvvasilev.irc.server.command.nick.NickCommandParser;
import dev.mvvasilev.irc.server.command.pass.PassCommandParser;
import dev.mvvasilev.irc.server.command.user.UserCommandParser;

import java.util.HashMap;

@Singleton
public final class CommandRegistry extends HashMap<String, Provider<CommandParser>> {

    {{
        put("pass", PassCommandParser::new);
        put("user", UserCommandParser::new);
        put("nick", NickCommandParser::new);
    }};

}
