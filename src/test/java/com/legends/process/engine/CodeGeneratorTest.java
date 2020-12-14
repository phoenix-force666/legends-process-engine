package com.legends.process.engine;

import com.legends.cloud.db.generator.LegendsCodeGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 */
@SpringBootTest(classes = LegendsCodeGenerator.class)
@RunWith(SpringRunner.class)
public class CodeGeneratorTest {
    @Autowired
    private LegendsCodeGenerator legendsCodeGenerator;


    /**
     *
     * Method: main(String[] args)
     *
     */
    @Test
    public void testMain() throws Exception {
        legendsCodeGenerator.run();
    }


} 
