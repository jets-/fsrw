package com.usto.re.fsrw;

import com.usto.re.fsrw.properties.FileProperties;
import com.usto.re.fsrw.service.FsEnum;
import com.usto.re.fsrw.service.FsUtil;
import com.usto.re.fsrw.service.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

@Component
public class FsrwHandler {

    private final IOUtil ioUtil;

    private final FsUtil fsUtil;

    private final FileProperties file;

    private static Logger LOG = LoggerFactory.getLogger(FsrwHandler.class);

    @Autowired
    public FsrwHandler(IOUtil ioUtil, FsUtil fsUtil, FileProperties file) {
        this.ioUtil = ioUtil;
        this.fsUtil = fsUtil;
        this.file = file;
    }


    public void exec(FsEnum fs, String dev1, String dev2) throws Exception {
        this.checkDevices(dev1, dev2);

        if(fs.equals(FsEnum.NONE)) {
            Arrays.asList(FsEnum.values()).forEach(fsEnum -> {
                if(fsEnum.isValid())
                    this.start(fsEnum, dev1, dev2);
            });
        } else
            this.start(fs, dev1, dev2);
    }

     private void start(FsEnum fs, String dev1, String dev2) {
        LOG.info("############## ".concat(fs.getValue()).concat(" ##############"));

        if(fsUtil.isMounted(dev1))
            fsUtil.umount(dev1);

         if(fsUtil.isMounted(dev2))
             fsUtil.umount(dev2);

        LOG.info("Formatando... ".concat(dev1));
        fsUtil.mkfs(fs, dev1);
        LOG.info("Formatando... ".concat(dev2));
        fsUtil.mkfs(fs, dev2);

        fsUtil.mount(dev1, file.getMnt1());
        byte[] data = this.getRandomData();
        this.logW(data);

        fsUtil.mount(dev2, file.getMnt2());
        this.logRW();
    }

    private void checkDevices(String dev1, String dev2) throws Exception {
        if(!fsUtil.isDevice(dev1) || !fsUtil.isDevice(dev2))
            throw new Exception("Invalid Device");

        fsUtil.config(file.getMnt1(), file.getMnt2(), file.getName(), file.getName());
    }

    private byte[] getRandomData() {
        Random random = new Random();
        byte[] data = new byte[file.getSize()];
        random.nextBytes(data);
        return data;
    }

    private void logW(byte[] data) {
        LOG.info("Iniciando escrita...");
        double start = System.nanoTime();

        ioUtil.put(file.getMnt1().concat(file.getName()), data);

        double finish = System.nanoTime();
        double time = (finish - start)/1000000;
        LOG.info("Tempo: " + time + " ms");
        LOG.info("----------");
    }

    private void logRW() {
        LOG.info("Iniciando cópia...");
        double start = System.nanoTime();

        byte[] data = ioUtil.read(file.getMnt1().concat(file.getName()));

        double finish = System.nanoTime();
        double time = (finish - start)/1000000;
        LOG.info("Tempo leitura: " + time + " ms");
        start = System.nanoTime();

        ioUtil.put(file.getMnt2().concat(file.getName()), data);

        finish = System.nanoTime();
        time = (finish - start)/1000000;
        LOG.info("Tempo escrita: " + time + " ms");
        LOG.info("----------");
    }


}
