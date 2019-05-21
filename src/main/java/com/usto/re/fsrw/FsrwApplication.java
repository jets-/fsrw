package com.usto.re.fsrw;

import com.usto.re.fsrw.service.FsEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FsrwApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(FsrwApplication.class);

    private final FsrwHandler handler;

    @Autowired
    public FsrwApplication(FsrwHandler handler) {
        this.handler = handler;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FsrwApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setLogStartupInfo(false);
        app.run(args);
        LOG.info("FINISHED");
    }

    @Override
    public void run(String... args) throws Exception {
        if(args != null && args.length > 2)
            handler.exec(FsEnum.fetch(args[0]), args[1], args[2]);
        else if(args != null && args.length == 2)
            handler.exec(FsEnum.NONE, args[0], args[1]);
        else
            LOG.info("Modo de uso >> this.jar [xfs | ext4 | brfs | f2fs | _ ]  [dev1]  [dev2]");
    }
}
