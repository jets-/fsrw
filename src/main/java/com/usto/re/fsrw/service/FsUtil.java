package com.usto.re.fsrw.service;

import com.usto.re.fsrw.FsrwHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FsUtil {

    @Autowired
    private Task sh;

    private static Logger LOG = LoggerFactory.getLogger(FsrwHandler.class);

    public boolean mount(String fs, String dev, String mnt) {
        try {
            String output = sh.run("mount.sh", fs, dev, mnt);
            return output.equals("0");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean umount(String dev, String mnt) {
        try {
            String output = sh.run("umount.sh", dev, dev);
            return output.equals("0");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean clear(String mnt) {
            return false;
    }
}
