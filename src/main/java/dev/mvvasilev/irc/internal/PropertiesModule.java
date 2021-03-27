package dev.mvvasilev.irc.internal;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.apache.commons.cli.CommandLine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesModule extends AbstractModule {

    private static final String DEFAULT_PROPERTIES = "./application.properties";

    @Override
    protected void configure() {
    }

//    @Provides
//    Properties properties(CommandLine commandLine) throws IOException {
//
//        String propertiesFile = commandLine.getOptionValue("properties", DEFAULT_PROPERTIES);
//
//        if (propertiesFile != null) {
//            try (InputStream input = new FileInputStream(propertiesFile)) {
//                Properties prop = new Properties();
//
//                prop.load(input);
//
//                return prop;
//            }
//        }
//    }
}
