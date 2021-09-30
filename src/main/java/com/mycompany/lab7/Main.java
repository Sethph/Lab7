/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab7;

import edu.tarleton.caesarcipher.Caesar;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sethp
 */
public class Main {

    public static void main(String[] args) {
        int shift = 12;
        Caesar cipher = new Caesar(shift);

        Scanner sc = new Scanner(System.in);

        String exit = "exit";
        System.out.println("Enter cmd encrypt, decrypt or exit");
        String cmd = sc.nextLine();
        while (!cmd.equals(exit)) {
            System.out.println("Enter file Name");
            String fileName = sc.nextLine();
            switch (cmd) {
                case "encrypt":
                    encryptFile(fileName, cipher);
                    break;
                case "decrypt":
                    decryptFile(fileName, cipher);
                    break;
                default:
                    System.out.println("Not a valid cmd");
                    break;
            }
            System.out.println("Enter cmd encrypt, decrypt or exit");
            cmd = sc.nextLine();
        }

    }

    public static void encryptFile(String fileName, Caesar cipher) {
        try {
            Path path = Paths.get(fileName);
            Charset cs = Charset.forName("UTF-8");
            List<String> lines = Files.readAllLines(path, cs);
            List<String> encryptedLines = new ArrayList<>();
            for (String line : lines) {
                String encryptedLine = cipher.encrypt(line);
                encryptedLines.add(encryptedLine);
            }
            try ( PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"))) {
                for (String line : encryptedLines) {
                    //System.out.println(line);
                    out.printf("%s%n", line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void decryptFile(String fileName, Caesar cipher) {
        try {
            Path path = Paths.get(fileName);
            Charset cs = Charset.forName("UTF-8");
            List<String> lines = Files.readAllLines(path, cs);
            List<String> decryptedLines = new ArrayList<>();
            for (String line : lines) {
                String decryptedLine = cipher.decrypt(line);
                decryptedLines.add(decryptedLine);
            }
            try ( PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"))) {
                for (String line : decryptedLines) {
                    //System.out.println(line);
                    out.printf("%s%n", line);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
