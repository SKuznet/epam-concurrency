package com.epam.concurrency.hw6;

import java.net.URL;
import java.time.Duration;

public class LogLine {

    private String login;
    private URL url;
    private Duration duration;

    public LogLine(String login, URL url, Duration duration) {
        this.login = login;
        this.url = url;
        this.duration = duration;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(login).append(',').append(" ");
        sb.append(url).append(',').append(" ");
        sb.append(duration);
        return sb.toString();
    }
}
