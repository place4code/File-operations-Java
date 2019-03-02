package com.company;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReadFileNIO {

    public static void main(String[] args) {

        //######################################################
        //write without mapping
        try (FileChannel channel = (FileChannel)Files.newByteChannel(Paths.get("write.txt"),
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE)){

            //buffer
            ByteBuffer buffer = ByteBuffer.allocate(26);
            //put in buffer
            for (int i = 0; i < 26; i++) {
                buffer.put((byte)('A' + i)); //65('A') + 0(i)... 65 = A, 66 = B
            }
            //position buffer 0
            buffer.rewind();
            //write to the file
            channel.write(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //######################################################
        //write with mapping
        try (FileChannel channel = (FileChannel)Files.newByteChannel(Paths.get("writeMap.txt"),
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE,
                StandardOpenOption.READ)){

            //mapped buffer
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 26);
            //put in buffer
            for (int i = 0; i < 26; i++) {
                buffer.put((byte)('A' + i)); //65('A') + 0(i)... 65 = A, 66 = B
            }
            //position buffer 0
            buffer.rewind();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //######################################################
        //read without mapping
        int count;
                                          // channel to the file
        try(SeekableByteChannel channel = Files.newByteChannel(Paths.get("write.txt"))) {

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

        //######################################################
        //read with mapping
        try(FileChannel channel = (FileChannel)Files.newByteChannel(Paths.get("writeMap.txt"))) {

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
