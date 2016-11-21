package com.jihoon.service;

import com.jihoon.config.ApplicationProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import static com.jihoon.util.HashMapUtil.printHashMap;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class WordCountServiceImplTests {

    private static final String BASE_FILE_PATH = "./src/test/resources";
    private static final String fileName = "./static/testSample.txt";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        //create temp Row Data File
        try {

            String content = "apple, banana, orange\napple, mango, Banana. apple\nOrange orange apple";

            File file = new File(BASE_FILE_PATH+fileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            //this.AbsoluteFileName = file.getAbsoluteFile();
            System.out.println("file.getPath(): "+file.getPath());
            System.out.println("file.getCanonicalPath(): "+file.getCanonicalPath());

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//
//    @After
//    public void finish() {
//        //Delete temp Row Data File
//
//        try{
//            // create new file
//            File file = new File(fileName);
//
//            // if file exists, then delete it
//            if (file.exists()) {
//                file.delete();
//            }
//        }catch(Exception e){
//            // if any error occurs
//            e.printStackTrace();
//        }
//    }

    @Test
    public void createHashMap() throws Exception {

        ApplicationProperties applicationProperties = new ApplicationProperties("testId","testPassword","testRole",fileName);

        WordCountServiceImpl wordCountServiceImpl = new WordCountServiceImpl(applicationProperties);

        HashMap<String, Integer> testHashMap = wordCountServiceImpl.getWordCountHashMap();

        printHashMap(testHashMap);
        assertThat(testHashMap.get("apple")).isEqualTo(4);
        assertThat(testHashMap.get("orange")).isEqualTo(3);
        assertThat(testHashMap.get("banana")).isEqualTo(2);
        assertThat(testHashMap.get("mango")).isEqualTo(1);
    }
}
