package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = null;
        String filePath;
        Scanner scanner = new Scanner(System.in);

        //###############################################################
        //################################         Find a file:

        boolean fileFound = false;
        while (!fileFound) {
            System.out.println("Enter the path to the file in which you want to save the data: ");
            filePath = scanner.nextLine();
            if (filePath != null) {
                file = new File(filePath);
                if (file.exists()) {
                    System.out.println("Path: " + filePath + " is correct");
                    fileFound = true;
                } else {
                    System.out.println("Path: " + filePath + " is incorrect");
                }
            }

        }

        //###############################################################
        //################################         Save to file:

        //FileWriter fileWriter = new FileWriter(file);
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
        boolean endOfTyping = false;
        while (!endOfTyping) {
            System.out.println("Write something: ");
            String content = scanner.nextLine();
            if (content.equals("-")) {
                endOfTyping = true;
                continue;
            }
            System.out.println(content);
            fileWriter.write(content + System.lineSeparator());
        }
        fileWriter.close();

        //###############################################################
        //################################         Read from a file:

        int lineCounter = 0;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        StringBuilder content = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
            lineCounter++;
        }
        bufferedReader.close();

        System.out.println("Lines in the file: " + lineCounter);
        System.out.println(content);

        //###############################################################
        //################################         Binary:

        /*


        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file));

        System.out.println("Your birthday in format: dd-mm-yyyy");
        String birth = scanner.nextLine().replaceAll("-", "");
        System.out.println(birth);
        outputStream.writeInt(Integer.valueOf(birth));
        outputStream.close();

        DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
        System.out.println("Content from a file: " + inputStream.readInt());


        */

    }
}
