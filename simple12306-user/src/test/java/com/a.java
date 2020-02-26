package com;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/23 16:13
 */
public class a {
    @Test
    public void t1() throws IOException {
        List<String> list = FileUtils.readLines(new File("rsa.pri"));
        System.out.println(list.size());
    }
}
