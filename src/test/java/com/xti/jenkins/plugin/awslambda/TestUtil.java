package com.xti.jenkins.plugin.awslambda;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class TestUtil {
    public File getResource(String resourcePath){
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(resourcePath);
        if(resource != null){
            try {
                File tempEcho = Files.createTempFile("aws-lambda-plugin", "echo.zip").toFile();
                FileUtils.copyFile(new File(resource.getFile()), tempEcho);
                tempEcho.deleteOnExit();
                return tempEcho;
            } catch (IOException e) {
                throw new IllegalStateException("Could not load " + resourcePath);
            }

        } else {
            throw new IllegalStateException("Could not load " + resourcePath);
        }
    }
}
