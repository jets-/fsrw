package com.usto.re.fsrw.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class IOUtil {

    public byte[] read(String mnt) {
        Path path = Paths.get(mnt);

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(path.toAbsolutePath().toFile(), "r")) {
            FileChannel channel = randomAccessFile.getChannel();
            int size = (int) Files.size(path);

            byte[] bytes = new byte[size];
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
            buffer.get(bytes);

            return bytes;
        } catch (IOException e) {
            return null;
        }
    }

    public boolean put(String mnt, byte[] data) {
        Path path = Paths.get(mnt);

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(path.toAbsolutePath().toFile(), "rw")) {
            FileChannel channel = randomAccessFile.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, data.length);
            buffer.put(data);

            return Files.exists(path) && Files.size(path) == data.length;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
