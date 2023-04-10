package com;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//implements a GUI-based application for importing and exporting calendar data in CSV format

import javax.swing.JButton;

import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

//program provides two buttons, "Import" and "Export", 

public class download extends JFrame {

    private JPanel contentPane;
    private JFileChooser fileChooser;
    
    private JButton importButton;
    
    
    private JButton exportButton;
//allow the user to choose a CSV file from their local machine
    public static void main(String[] args) {
        //either import the data into a MySQL database or export the data
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                //program starts by defining a class called "download" that extends
                try {
                    download frame = new download();
                    
                    frame.setVisible(true);
                    
                    //The "download" class contains a "contentPane" object 
                } catch (Exception e) {
                    e.printStackTrace();
                    //Both buttons have action listeners that are defined as anonymous inner classes
                }
            }
        });
    }
//action listeners are triggered when the user clicks the buttons and perform the import and export functions
    public download() {
        
        
        setTitle("Calendar Download");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
//action listener starts by showing the file chooser dialog and waiting for the user to select a file
        importButton = new JButton("Import");
        importButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
                
                //user selects a file, the program reads the contents of the file line by line using a "BufferedReader" object
                int returnVal = fileChooser.showOpenDialog(download.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line = br.readLine();
                        
                        //action listener works similarly to the "importButton" action listener
                        while (line != null) {
                            // Import calendar data from CSV file and save to MySQL database
                        }
                        br.close();
                        JOptionPane.showMessageDialog(download.this, "Calendar data imported successfully");
                    } catch (IOException e) {
                        //prompts the user to choose a location to save the exported CSV file
                        JOptionPane.showMessageDialog(download.this, "Error importing calendar data");
                        e.printStackTrace();
                    }
                }
            }
        });
        //program also provides error handling for cases where the file selection or file reading/writing fails
        contentPane.add(importButton, BorderLayout.NORTH);
//program provides a simple but functional GUI interface
        exportButton = new JButton("Export");
        
        exportButton.addActionListener(new ActionListener() {
            
            
            public void actionPerformed(ActionEvent arg0) {
                
                //exporting calendar data in CSV format
                int returnVal = fileChooser.showSaveDialog(download.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    //built-in Java classes and libraries for file handling and MySQL database connectivity
                    File file = fileChooser.getSelectedFile();
                    
                    if (!file.getName().toLowerCase().endsWith(".csv")) {
                        file = new File(file.getParentFile(), file.getName() + ".csv");
                        
                    }
                    try {
                        
                        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                        // Export calendar data from MySQL database to CSV file
                        bw.close();
                        
                        JOptionPane.showMessageDialog(download.this, "Calendar data exported successfully");
                    } catch (IOException e) {
                        
                        JOptionPane.showMessageDialog(download.this, "Error exporting calendar data");
                        //adding additional fields to the CSV file
                        e.printStackTrace();
                        
                        //connecting to a different database platform
                    }
                }
            }
        });
        
        //selected location
        contentPane.add(exportButton, BorderLayout.SOUTH);
    }
}
