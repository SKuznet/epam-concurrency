package com.epam.concurrency.hw6;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ReportExporter implements Runnable {

    private Path path;
    private Report report;
    private int linesInFile;
    private AtomicInteger counter = new AtomicInteger(1);

    public ReportExporter(Path path, Report report, int linesInFile) {
        this.path = path;
        this.report = report;
        this.linesInFile = linesInFile;
    }

    private boolean writeInFile(Path path, Report report) {
        Path absolutePath = path.toAbsolutePath();
        if (Files.isRegularFile(absolutePath)) {
            throw new PathNotADirectoryException("Given path should be a directory");
        }
        if (Files.notExists(absolutePath)) {
            throw new FileNotExistException("file by path not exists: " + absolutePath);
        }
        System.out.println("found file: " + absolutePath);
        absolutePath = createNewLogFileName(absolutePath);
        System.out.println("file morphed to " + absolutePath);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(absolutePath);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            List<String> existingLines = Files.readAllLines(absolutePath);
            System.out.println("created file " + absolutePath);
            if (existingLines.size() >= linesInFile) {
                throw new FileFullException("File is full: " + existingLines.size() + "/" + linesInFile);
            }
            for (String line : existingLines) {
                printWriter.println(line);
                System.out.println("write in file old line: " + line);
            }
            for (LogLine line : report.getLogLines()) {
                printWriter.println(line.toString());
                System.out.println("write in file: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private Path createNewLogFileName(Path path) {
        Path checkingPath = Paths.get(path.toString(), "log_" + counter.getAndIncrement());
        while (Files.exists(checkingPath)) {
            checkingPath = Paths.get(path.toString(), "log_" + counter.getAndIncrement());
        }
        return checkingPath;
    }

    @Override
    public void run() {
        writeInFile(path, report);
    }
}
