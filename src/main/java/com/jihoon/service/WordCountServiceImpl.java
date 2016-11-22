package com.jihoon.service;

import com.jihoon.config.ApplicationProperties;
import com.jihoon.model.ResponseWordCount;
import com.jihoon.model.WordCount;
import com.jihoon.util.HashMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service("WordCountService")
public class WordCountServiceImpl implements WordCountService{

    private static final Logger logger = LoggerFactory.getLogger(WordCountServiceImpl.class);

    private String RowDataFilePath;
    private HashMap<String,Integer> sortedHashMap;

    @Autowired
    public WordCountServiceImpl(ApplicationProperties applicationProperties
    ){
        logger.debug("RowDataFile : " + applicationProperties.getRowDataFilePath());
        this.RowDataFilePath = applicationProperties.getRowDataFilePath();
        this.sortedHashMap = this.initHashMap(this.RowDataFilePath);
    }

    /**
     * get WordCount information from hashmap.
     *
     * @param searchList word names to find the count in the RowDataFile.
     * @return wordCountList
     */
    public List getWordCountSearch(List<String> searchList) {
        List wordCountList = new ArrayList<WordCount>();
        ResponseWordCount result = new ResponseWordCount();

        Integer count;

        for(String searchName : searchList){
            logger.debug("searchName :" +searchName);

            if(this.getWordCountHashMap().get(searchName.toLowerCase()) == null){
                count = 0;
            } else {
                count = this.getWordCountHashMap().get(searchName.toLowerCase());
            }

            wordCountList.add(new WordCount( searchName , count ));
        }

        return wordCountList;
    }

    /**
     * get Top # information based on the count-number in the sorted hashmap.
     *
     * @param number
     * @return String
     */
    public String getWordCountTopRanks(Integer number) {

        String result = "";
        Integer index = 0;

        // wordCountHashMap is already sorted hashMap (DESC)
        for (Map.Entry<String, Integer> entry : this.getWordCountHashMap().entrySet())
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

    /**
     * Initialize the HashMap.
     * 1. find and read RowDataFile.
     * 2. Preprocessing RowDataFile.
     * 3. Parse the RowDataFile.
     * 4. Make a hashmap with RowDataFile information ( word name : count number)
     * 5. Sort the hashmap ( DESC )
     *
     * @param rowDataFileName
     * @return HashMap<String,Integer>
     */
    private HashMap<String,Integer> initHashMap(String rowDataFileName) {

        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();

        try {

            ClassLoader classLoader = this.getClass().getClassLoader();
            File file = new File(classLoader.getResource(RowDataFilePath).getPath());

            //File is found
            logger.debug("File Found : " + file.exists());

            //1. Read File Content
            String content_temp = new String(Files.readAllBytes(file.toPath()));

            //2. preprocess data cleansing ( transform to lowercase , remove '.' or ',' )
            String content = content_temp.toLowerCase().replaceAll("\\.|,", " ");

            //3. split and read each Word
            String[] words = Pattern.compile("\\s+").split(content);


            //4. make HashMap info
            for (String word:words) {

                // first case : set value : 1
                if (!hashMap.containsKey(word)) {  // first time we've seen this string
                    hashMap.put(word, 1);
                }
                else {
                    // other case : set value : count + 1
                    int count = hashMap.get(word);
                    hashMap.put(word, count + 1);
                }
            }

            //HashMapUtil.printHashMap(hashMap);

        } catch ( IOException e) {
            logger.error(e.getMessage());
        }

        //5. HashMap sort By Desc order
        return HashMapUtil.sortByOrder(hashMap, false);
    }

    /**
     * getWordCountHashMap
     *
     * @return HashMap<String,Integer>
     */
    public HashMap<String,Integer> getWordCountHashMap() {

        return this.sortedHashMap;
    }

    /**
     * getRowDataFilePath
     *
     * @return String
     */
    public String getRowDataFilePath() {
        return RowDataFilePath;
    }
}