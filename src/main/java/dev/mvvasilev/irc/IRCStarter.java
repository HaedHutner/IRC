package dev.mvvasilev.irc;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import dev.mvvasilev.irc.internal.CommandLineModule;
import dev.mvvasilev.irc.internal.IRCModule;
import dev.mvvasilev.irc.internal.PropertiesModule;
import dev.mvvasilev.irc.server.IRCServer;

public class IRCStarter {

    private Injector injector;

    @Inject
    private IRCServer ircServer;

    public IRCStarter(String[] args) {
        injector = Guice.createInjector(
                new CommandLineModule(args),
                new PropertiesModule(),
                new IRCModule()
        );
    }

    public void startServer() {
        injector.injectMembers(this);
        injector.injectMembers(ircServer);
        try {
            ircServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
