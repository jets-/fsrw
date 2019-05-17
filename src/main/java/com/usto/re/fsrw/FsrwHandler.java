package com.usto.re.fsrw;

import com.usto.re.fsrw.service.FsEnum;
import com.usto.re.fsrw.service.FsUtil;
import com.usto.re.fsrw.service.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FsrwHandler {

    @Autowired
    private IOUtil ioUtil;

    @Autowired
    private FsUtil fsUtil;

    private static Logger LOG = LoggerFactory.getLogger(FsrwHandler.class);

    public void xfs(String input, String output) {
        System.out.println(FsEnum.XFS);
    }

    public void ext4(String input, String output) {
        System.out.println(FsEnum.EXT4);

    }

    public void brfs(String input, String output) {
        System.out.println(FsEnum.BRFS);
    }


    public void f2fs(String input, String output) {
        System.out.println(FsEnum.F2FS);
    }


    public void full(String input, String output) {
        LOG.info("STARTING");
        System.out.println();
        this.xfs(input, output);
        System.out.println();
        this.ext4(input, output);
        System.out.println();
        this.brfs(input, output);
        System.out.println();
        this.f2fs(input, output);
        System.out.println();
        LOG.info("FINISHED");
    }


}
