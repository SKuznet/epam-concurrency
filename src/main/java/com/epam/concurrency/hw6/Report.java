package com.epam.concurrency.hw6;

import java.util.ArrayList;
import java.util.List;

public class Report {

    private List<LogLine> logLines = new ArrayList<>();

    public void addLine(LogLine line) {
        logLines.add(line);
    }

    public List<LogLine> getLogLines() {
        return logLines;
    }
}
