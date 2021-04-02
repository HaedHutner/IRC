package dev.mvvasilev.irc;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import dev.mvvasilev.irc.internal.CommandLineModule;
import dev.mvvasilev.irc.internal.IRCModule;
import dev.mvvasilev.irc.internal.PropertiesModule;
import dev.mvvasilev.irc.server.IRCServer;

import java.awt.event.WindowStateListener;

public class IRCStarter {

    private static IRCStarter instance;

    private static Injector injector;

    @Inject
    private IRCServer ircServer;

    public IRCStarter init(String[] args) {
        injector = Guice.createInjector(
                new CommandLineModule(args),
                new PropertiesModule(),
                new IRCModule()
        );

        return this;
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

    public static IRCStarter getInstance() {
        if (instance == null) {
            instance = new IRCStarter();
        }

        return instance;
    }

}
