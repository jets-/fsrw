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
    private Integer repeat;

    private Default aDefault;

    public FileProperties() {
    }

    public FileProperties(String name, String mnt1, String mnt2, Integer size, Integer repeat, Default aDefault) {
        this.name = name;
        this.mnt1 = mnt1;
        this.mnt2 = mnt2;
        this.size = size;
        this.repeat = repeat;
        this.aDefault = aDefault;
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

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public Default getDefault() {
        return aDefault;
    }

    public void setDefault(Default aDefault) {
        this.aDefault = aDefault;
    }

    public static class Default {
        private String device1;
        private String device2;
        private String fs;

        public String getDevice1() {
            return device1;
        }

        public void setDevice1(String device1) {
            this.device1 = device1;
        }

        public String getDevice2() {
            return device2;
        }

        public void setDevice2(String device2) {
            this.device2 = device2;
        }

        public String getFs() {
            return fs;
        }

        public void setFs(String fs) {
            this.fs = fs;
        }
    }
}
