package com.epam.concurrency.hw1.bio;

import java.util.Arrays;

public class WindowWrapper implements Comparable<WindowWrapper> {

    private long[] window;

    public WindowWrapper(long[] window) {
        this.window = window;
    }

    public long[] getWindow() {
        return window;
    }

    public void setWindow(long[] window) {
        this.window = window;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WindowWrapper that = (WindowWrapper) o;
        return Arrays.equals(window, that.window);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(window);
    }

    @Override
    public String toString() {
        return "window=" + Main.printWindow(window, 0, (Main.M - (Main.M / 31) * 31) * 2) + " "
                + Arrays.toString(window);
    }

    @Override
    public int compareTo(WindowWrapper other) {
        for (int i = 0; i < this.window.length; i++) {
            long compare = window[i] ^ other.window[i];
            if (compare != 0) {
                return -1;
            }
        }
        return 0;
    }
}
