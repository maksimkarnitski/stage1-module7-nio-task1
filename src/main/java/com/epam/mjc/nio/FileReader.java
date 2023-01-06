package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            FileChannel fileChannel = randomAccessFile.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            while (fileChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                for (int i = 0; i < byteBuffer.limit(); i++) {
                    stringBuilder.append((char)byteBuffer.get());
                }
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] abc = stringBuilder.toString().split("\r\n");

        String nameValue = abc[0].substring(abc[0].indexOf(" ") + 1);
        int ageValue = Integer.parseInt(abc[1].substring(abc[1].indexOf(" ") + 1));
        String emailValue = abc[2].substring(abc[2].indexOf(" ") + 1);
        long phoneValue = Long.parseLong(abc[3].substring(abc[3].indexOf(" ") + 1));

        return new Profile(nameValue, ageValue, emailValue, phoneValue);
    }
}
