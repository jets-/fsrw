package com.usto.re.fsrw.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "file")
@Configuration("file")
public class FileProperties {

    private String name;
    private String mnt1;
    private String mnt2;
    private Integer size;

    public FileProperties() {
    }

    public FileProperties(String name, String mnt1, String mnt2, Integer size) {
        this.name = name;
        this.mnt1 = mnt1;
        this.mnt2 = mnt2;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMnt1() {
        return mnt1;
    }

    public void setMnt1(String mnt1) {
        this.mnt1 = mnt1;
    }

    public String getMnt2() {
        return mnt2;
    }

    public void setMnt2(String mnt2) {
        this.mnt2 = mnt2;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
