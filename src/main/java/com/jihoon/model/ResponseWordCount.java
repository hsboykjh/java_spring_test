package com.jihoon.model;

import java.util.List;

public class ResponseWordCount {

    private List<WordCount> counts;

    public List<WordCount> getCounts() {
        return counts;
    }

    public void setCounts(List<WordCount> counts) {
        this.counts = counts;
    }
}
