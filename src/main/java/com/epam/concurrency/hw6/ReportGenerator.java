package com.epam.concurrency.hw6;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class ReportGenerator implements Callable<Report> {

    private List<String> loginList;
    private List<URL> urlList;

    public ReportGenerator() {
        this.loginList = Arrays.asList("universal",
                                       "RollerDo",
                                       "bigSweetGarnet",
                                       "Leonid",
                                       "Zstudent");
        this.urlList = Arrays.asList(toUrl("slyack.com"),
                                     toUrl("youtube.com"),
                                     toUrl("epam.com"),
                                     toUrl("Adaptation.com"),
                                     toUrl("google.com"));
    }

    private URL toUrl(String path) {
        URL url = null;
        try {
            url = Paths.get(path).toUri().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public Report createReport() {
        int lines = 20;
        Report report = new Report();
        Random random = new Random();
        Duration duration = Duration.ofSeconds(10).plusSeconds(random.nextInt(590));
        for (int i = 0; i < lines; i++) {
            LogLine logLine = new LogLine(loginList.get(random.nextInt(loginList.size())),
                urlList.get(random.nextInt(urlList.size())),
                duration);
            report.addLine(logLine);
        }
        return report;
    }

    @Override
    public Report call() throws Exception {
        return createReport();
    }
}
