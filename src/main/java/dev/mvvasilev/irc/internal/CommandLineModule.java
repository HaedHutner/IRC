package dev.mvvasilev.irc.internal;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.apache.commons.cli.*;

import java.util.Properties;

public class CommandLineModule extends AbstractModule {

    private String[] args;

    public CommandLineModule(String[] arguments) {
        this.args = arguments;
    }

    @Override
    protected void configure() {
    }

    @Provides
    @Named("command-line-arguments")
    String[] commandLineArguments() {
        return args;
    }

    @Provides
    CommandLineParser commandLineParser() {
        return new DefaultParser();
    }

    @Provides
    Options commandLineOptions() {
        return new Options()
                .addOption("p", "port", true, "The port the irc server will run on, default 6667")
                .addOption("r", "properties", true, "The properties file that will be used to configure the server. Command line overrides file properties. Default ./application.properties")
                .addOption("o", "ssl-port", true, "ssl port, if ssl is enabled, default 7667")
                .addOption("s", "ssl-enabled", true, "enable ssl, default false");
    }

    @Provides
    CommandLine commandLine(
            CommandLineParser commandLineParser,
            Options commandLineOptions,
            @Named("command-line-arguments") String[] commandLineArguments
    ) throws ParseException {
        return commandLineParser.parse(commandLineOptions, commandLineArguments);
    }
}
