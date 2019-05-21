package com.usto.re.fsrw;

import com.usto.re.fsrw.properties.FileProperties;
import com.usto.re.fsrw.service.FsEnum;
import com.usto.re.fsrw.service.FsUtil;
import com.usto.re.fsrw.service.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class FsrwHandler {

    private final IOUtil ioUtil;

    private final FsUtil fsUtil;

    private final FileProperties properties;

    private static Logger LOG = LoggerFactory.getLogger(FsrwHandler.class);

    @Autowired
    public FsrwHandler(IOUtil ioUtil, FsUtil fsUtil, FileProperties properties) {
        this.ioUtil = ioUtil;
        this.fsUtil = fsUtil;
        this.properties = properties;
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

        if(!fsUtil.isMounted(dev1)) {
            LOG.error("Disco origem precisa estar montado");
            return;
        }
         if(fsUtil.isMounted(dev2))
             fsUtil.umount(dev2);

        LOG.info("Formatando... ".concat(dev2));
        fsUtil.mkfs(fs, dev2);

        fsUtil.mount(dev2, properties.getMnt2());
        this.readWrite(dev1);
    }

    private void checkDevices(String dev1, String dev2) throws Exception {
        if(!fsUtil.isDevice(dev1) || !fsUtil.isDevice(dev2))
            throw new Exception("Invalid Device");

        fsUtil.mkdir(properties.getMnt2());
    }

    private byte[] getRandomData() {
        Random random = new Random();
        byte[] data = new byte[properties.getSize()];
        random.nextBytes(data);
        return data;
    }

    private void write(byte[] data) {
        double totalTime = 0;

        for(int i = 1; i <= properties.getRepeat(); i++) {
            LOG.info("Iniciando escrita... " +i);
            double start = System.nanoTime();

            ioUtil.put(properties.getMnt1().concat(properties.getName()) +i, data);

            double finish = System.nanoTime();
            double time = (finish - start) / 1000000;
            totalTime += time;
            LOG.info("Tempo: " + time + " ms");
            LOG.info("----------");
        }
        LOG.info("Tempo médio de R: " + totalTime/ properties.getRepeat());
    }

    private void readWrite(String dev) {
        double rTime = 0;
        double rwTime = 0;
        String basePath = fsUtil.getMntFrom(dev);
        List<File> files = ioUtil.getAllFiles(basePath);

        for(File file : files) {
            LOG.info("Iniciando cópia... " + file.getAbsolutePath());
            double start = System.nanoTime();

            byte[] data = ioUtil.read(file.getAbsolutePath());

            double finish = System.nanoTime();
            double time = (finish - start) / 1000000;
            LOG.info("Tempo leitura: " + time + " ms");
            rTime += time;

            String destiny = file.getAbsolutePath().replaceAll(basePath, this.properties.getMnt2());
            start = System.nanoTime();

            ioUtil.put(destiny, data);

            finish = System.nanoTime();
            time = (finish - start) / 1000000;
            rwTime += time;
            LOG.info("Tempo escrita: " + time + " ms");
            LOG.info("----------");
        }
        LOG.info("Tempo médio de R: " + rTime/files.size());
        LOG.info("Tempo médio de RW: " + rwTime/files.size());
    }


}
