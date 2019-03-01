package com.company;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFileNIO {

    public static void main(String[] args) {

        int count;
        Path path = null;

        try {
                   // path to the file
            path = Paths.get("test.txt");
        } catch(InvalidPathException e) {
            System.out.println("Invalid Path");
            return;
        }

                                          // channel to the file
        try(SeekableByteChannel channel = Files.newByteChannel(path)) {

            //buffer allocation
            ByteBuffer buffer = ByteBuffer.allocate(128);

            do {
                //read a data and write into buffer
                count = channel.read(buffer);

                if (count != -1) {
                    buffer.rewind();
                    for (int i = 0; i < count; i++) {
                        System.out.print((char)buffer.get());
                    }
                }

            } while (count != -1);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
