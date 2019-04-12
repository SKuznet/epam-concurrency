package com.epam.concurrency.hw6;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class ReportImporter {

    private Path path;
    private int parallelizm;
    private Report outReport;
    private ConcurrentLinkedDeque<Path> fileList;
    private static AtomicInteger counter = new AtomicInteger();

    public ReportImporter(Path path, int parallelizm) {
        this.path = path;
        this.parallelizm = parallelizm;
        fileList = new ConcurrentLinkedDeque<>();
    }

    public Report importReport() {
        if (Files.notExists(path)) {
            throw new PathNotExistsException("path not exists");
        }
        if (Files.isRegularFile(path)) {
            throw new PathNotADirectoryException("Given path should be a directory");
        }
        for (File file : path.toFile().listFiles()) {
            Path path = file.toPath();
            fileList.offer(path);
        }
        Path path;
        try {
            path = parseFileList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Path parseFileList() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(parallelizm);
        Future<Path> submit = null;
        for (int i = 0; i < parallelizm; i++) {
            submit = pool.submit(new FileCollapser(fileList));
        }
        Path path = submit.get();
        pool.shutdown();
        return path;
    }

    private static class FileCollapser implements Callable<Path> {


        private ConcurrentLinkedDeque<Path> fileList;

        public FileCollapser(ConcurrentLinkedDeque<Path> fileList) {
            this.fileList = fileList;
        }

        @Override
        public Path call() throws Exception {
            Path file1;
            Path file2;
            while((file1 = fileList.poll()) != null && (file2 = fileList.poll()) != null) {
                Path file3 = collaplseTwoFilesFromFileList(file1, file2);
                fileList.remove(file1);
                fileList.remove(file2);
                fileList.offerLast(file3);
            }
            Path result = Paths.get(file1 + "_result");
            Path move = Files.move(file1, result);
            return move;
        }

        private Path collaplseTwoFilesFromFileList(Path file1, Path file2) {
            Path file3 = file1.getParent().resolve(Paths.get("" + counter.getAndIncrement()));
            try (InputStream inputStream1 = Files.newInputStream(file1);
                 InputStream inputStream2 = Files.newInputStream(file2);
                 InputStream inputStream = new SequenceInputStream(inputStream1, inputStream2);
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                 OutputStream outputStream = Files.newOutputStream(file3);
                 PrintWriter printWriter = new PrintWriter(outputStream)
            ) {
                int read;
                while ((read = bufferedInputStream.read()) > 0) {
                    printWriter.write(read);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return file3;
        }
    }


}
