package com.company;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileNIO {

    public static void main(String[] args) {

        int count;
                                          // channel to the file
        try(SeekableByteChannel channel = Files.newByteChannel(Paths.get("test.txt"))) {

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

        System.out.println();
        System.out.println();

        //mapping
        try(FileChannel channel = (FileChannel)Files.newByteChannel(Paths.get("test.txt"))) {

            long fileSize = channel.size();

            //mapping
            MappedByteBuffer mappedBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);

            for (int i = 0; i < fileSize; i++) {
                System.out.print((char)mappedBuffer.get());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
