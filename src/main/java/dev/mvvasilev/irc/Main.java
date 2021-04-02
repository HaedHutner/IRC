package dev.mvvasilev.irc;

public class Main {

    public static void main(String[] args) {
        IRCStarter.getInstance().init(args).startServer();
    }

}
