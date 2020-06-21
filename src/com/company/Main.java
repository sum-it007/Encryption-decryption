package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main {

    public static void main(String[] args) {
	encryption obj = new encryption();
    }
}

class encryption extends JFrame implements ActionListener{

    JFileChooser fileChooser;
    JButton encryptButton;
    JButton decryptButton;
    JLabel info;
    File file;
    File file2 = new File("C:\\Users\\Sumit\\Desktop\\encrypted.txt");
    File file3 = new File("C:\\Users\\Sumit\\Desktop\\decrypted.txt");
    int returnVal;
    char XorKey = 'P';
    String currentLine;
    FileInputStream fin;
    FileOutputStream fout;


    public encryption()
    {
        setTitle("Encryption/Decryption");
        setLayout(null);
        setSize(360,170);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        fileChooser = new JFileChooser();
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");
        info = new JLabel("* Decrypts only those file which are encrypted by this app");

        add(encryptButton);
        add(decryptButton);
        add(info);

        encryptButton.setBounds(70,10,200,30);
        decryptButton.setBounds(70,50,200,30);
        info.setBounds(10,90,340,30);

        encryptButton.addActionListener(this);
        decryptButton.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==encryptButton){
            returnVal = fileChooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                byte[] bFile = new byte[(int) file.length()];
                byte[] outFile = new byte[bFile.length];
                try{
                    fin = new FileInputStream(file);
                    fin.read(bFile);
                    fin.close();
                    for(int i=0;i<bFile.length;i++)
                    {
                        outFile[i]=(byte)(bFile[i]^XorKey);
                    }

                    fout = new FileOutputStream(file2);
                    fout.write(outFile);

                }catch (Exception err)
                {
                    System.out.println(err.getMessage());
                }

            }
        }else if(e.getSource()==decryptButton){
            returnVal = fileChooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                byte[] bFile = new byte[(int) file.length()];
                byte[] outFile = new byte[bFile.length];
                try{
                    fin = new FileInputStream(file2);
                    fin.read(bFile);
                    fin.close();
                    for(int i=0;i<bFile.length;i++)
                    {
                        outFile[i]=(byte)(bFile[i] ^ XorKey);
                    }

                    fout = new FileOutputStream(file3);
                    fout.write(outFile);

                }catch (Exception err)
                {
                    System.out.println(err.getMessage());
                }

            }
        }
    }
}
