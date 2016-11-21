package com.jihoon.controller;

import com.jihoon.model.WordCount;
import com.jihoon.model.RequestWordCount;
import com.jihoon.model.ResponseWordCount;
import com.jihoon.service.WordCountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Base route path
@RequestMapping("/counter-api")
@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private WordCountService wordCountService;

//    @RequestMapping(path = "/test", method = RequestMethod.GET)
//    public String countWords() {
//        return "OK";
//    }

    @RequestMapping(path = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWordCount countWords(@RequestBody RequestWordCount search) {

        List<String> searchList = search.getSearchText();
        List wordCountList = new ArrayList<WordCount>();
        ResponseWordCount result = new ResponseWordCount();

        Integer count;

        for(String searchName : searchList){
            logger.debug("searchName :" +searchName);

            if(wordCountService.getWordCountHashMap().get(searchName.toLowerCase()) == null){
                count = 0;
            } else {
                count = wordCountService.getWordCountHashMap().get(searchName.toLowerCase());
            }

            wordCountList.add(new WordCount( searchName , count ));
        }

        result.setCounts(wordCountList);
        return result;
    }

    @RequestMapping(path = "/top/{number}", method = RequestMethod.GET, headers="Accept=text/csv;charset=UTF-8")
    public String countRanks(@PathVariable Integer number) {

        String result = "";
        Integer index = 0;

        // wordCountHashMap is already sorted hashMap (DESC)
        for (Map.Entry<String, Integer> entry : wordCountService.getWordCountHashMap().entrySet())
        {
            logger.debug(entry.getKey() + " : "+ entry.getValue());
            result = result + entry.getKey() + "|" + entry.getValue() + "\n";
            index++;

            //check the total elements
            if(index >= number)
                break;
        }
        return result;
    }
}