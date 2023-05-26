package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cryptanalyzer extends JFrame {
    private static final String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя .,\":-!?";

    private JLabel inputLabel;
    private JTextField inputField;
    private JLabel outputLabel;
    private JTextField outputField;
    private JButton encryptButton;
    private JButton decryptButton;

    public Cryptanalyzer() {
        setTitle("Caesar Cipher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setResizable(false);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        inputLabel = new JLabel("Исходный текст:");
        inputField = new JTextField(30);
        outputLabel = new JLabel("Результат:");
        outputField = new JTextField(30);
        outputField.setEditable(false);

        encryptButton = new JButton("Зашифровать");
        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String originalText = inputField.getText();
                String encryptedText = encryptText(originalText, 3);
                outputField.setText(encryptedText);
            }
        });

        decryptButton = new JButton("Расшифровать");
        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String encryptedText = inputField.getText();
                bruteForceDecryption(encryptedText);
            }
        });

        add(inputLabel);
        add(inputField);
        add(outputLabel);
        add(outputField);
        add(encryptButton);
        add(decryptButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Cryptanalyzer();
            }
        });
    }

    public static String encryptText(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            int index = alphabet.indexOf(ch);
            if (index != -1) {
                int newIndex = (index + key) % alphabet.length();
                char encryptedChar = alphabet.charAt(newIndex);
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }

    public static void bruteForceDecryption(String encryptedText) {
        for (int key = 1; key < alphabet.length(); key++) {
            String decryptedText = decryptText(encryptedText, key);
            System.out.println("Попытка расшифровки с ключом " + key + ":");
            System.out.println(decryptedText);
        }
    }

    public static String decryptText(String encryptedText, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (char ch : encryptedText.toCharArray()) {
            int index = alphabet.indexOf(ch);
            if (index != -1) {
                int newIndex = (index - key + alphabet.length()) % alphabet.length();
                char decryptedChar = alphabet.charAt(newIndex);
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(ch);
            }
        }
        return decryptedText.toString();
    }
}