package com.pvdnc.notebook;

import com.pvdnc.notebook.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotebookApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void hash(){
        String str="password(*)123456";
        String hashStr= MD5Utils.toHash(str);

    }

}
