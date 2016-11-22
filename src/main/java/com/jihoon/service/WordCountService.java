package com.jihoon.service;

import java.util.List;

public interface WordCountService {
    public List getWordCountSearch(List<String> searchList);
    public String getWordCountTopRanks(Integer number);
}
