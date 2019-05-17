package com.usto.re.fsrw.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Task {

    private String getOutput(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines().collect(Collectors.joining(System.lineSeparator()));
    }

    public String run(String sh, String... args) throws Exception {
        Path script = Paths.get("scripts/".concat(sh));

        List<String> commands = new ArrayList<>();
        commands.add("bash");
        commands.add(script.toAbsolutePath().toString());
        commands.addAll(Arrays.asList(args));

        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        Process process = processBuilder.start();

        int returnCode = process.waitFor();

        if(returnCode == 0){
            return this.getOutput(process.getErrorStream());
        } else {
            String output = this.getOutput(process.getErrorStream());
            throw new RuntimeException(output);
        }
    }
}
