package com.usto.re.fsrw;

import com.usto.re.fsrw.service.FsEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class FsrwApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(FsrwApplication.class);

    @Autowired
    private FsrwHandler handler;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FsrwApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setLogStartupInfo(false);
        app.run(args);
        LOG.info("FINISHED");
    }

    @Override
    public void run(String... args) throws Exception {
        if(args != null && args.length > 2)  {
            String[] io = Arrays.copyOfRange(args, 1, args.length);
            switch (FsEnum.fetch(args[0])) {
                case XFS:
                    handler.xfs(getInput(io), getOutput(io));
                    break;
                case EXT4:
                    handler.ext4(getInput(io), getOutput(io));
                    break;
                case BRFS:
                    handler.brfs(getInput(io), getOutput(io));
                    break;
                case F2FS:
                    handler.f2fs(getInput(io), getOutput(io));
                    break;
                default:
                    handler.full(getInput(args), getOutput(args));
                    break;
            }
        } else if(args != null && args.length == 2)
            handler.full(getInput(args), getOutput(args));
        else
            LOG.info("Modo de uso >> this.jar [xfs | ext4 | brfs | f2fs | _ ]  [dev1]  [dev2]");
    }

    private String getInput(String... args) {
        String input = null;
        if(args.length > 0 && args[0] != null && !args[0].equals("")) {
            input = args[0];
        }
        return input;
    }

    private String getOutput(String... args) {
        String output = null;
        if(args.length > 1 && args[1] != null && !args[1].equals("")) {
            output = args[1];
        }
        return output;
    }
}
