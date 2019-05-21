package com.usto.re.fsrw.service;

import com.usto.re.fsrw.FsrwHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FsUtil {

    private final Task sh;

    private static Logger LOG = LoggerFactory.getLogger(FsrwHandler.class);

    @Autowired
    public FsUtil(Task sh) {
        this.sh = sh;
    }

    public boolean mkfs(FsEnum fs, String dev) {
        try {
            String output = sh.run("mkfs.sh", fs.getValue().toLowerCase(), dev);
            return output.equals("0");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean mount(String dev, String mnt) {
        try {
            String output = sh.run("mount.sh", dev, mnt);
            return output.equals("0");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean umount(String dev) {
        try {
            String output = sh.run("umount.sh", dev);
            return output.equals("0");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean clear(String mnt) {
        try {
            String output = sh.run("rm.sh", mnt);
            return output.equals("0");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean isDevice(String dev) {
        try {
            String output = sh.run("dev.sh", dev, dev);
            return output.equals("0");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean isFreeToUse(String dev) {
        return this.isDevice(dev) && this.isMounted(dev);
    }

    public boolean isMounted(String dev) {
        try {
            String output = sh.run("mounted.sh", dev, dev);
            return output.equals("0");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean mkdir(String mnt1) {
        try {
            String output = sh.run("mkdir.sh", mnt1);
            return output.equals("0");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean touch(String path) {
        try {
            String output = sh.run("touch.sh", path);
            return output.equals("0");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public String getMntFrom(String dev) {
        try {
            return sh.run("mnt.sh", dev);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return "";
        }
    }
}
