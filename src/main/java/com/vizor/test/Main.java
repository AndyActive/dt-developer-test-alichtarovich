package com.vizor.test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Main extends JFrame {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    private static int page=0;
    private static boolean allPictures= false;


    public void run() {
        ArrayList<String> results = new ArrayList<>();
        File[] files = new File("assets").listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }


        JFrame frame = new JFrame("DT Developer Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("Home menu");


        JTextField download = new JTextField("add file", 20);
        download.addActionListener(e -> {
            try {
                System.out.println(download.getText());
                Files.copy(Path.of(download.getText()), Path.of("assets/" + Math.random()+ ".png"));
            } catch (IOException o) {
                o.printStackTrace();
                System.out.println("file not found");
            }
        });
        JTextField searchByName = new JTextField("Search", 20);
        searchByName.addActionListener(e -> {
            for (String s : results) {
                if (s.equals(searchByName.getText())) {
                        new ViewPicture(s);
                }
            }
        });

        JButton next = new JButton("Next");
        next.addActionListener(e -> {
            if(page+10<results.size()) {
                allPictures=false;
                page += 10;
                new Main().run();
                frame.dispose();
            }
        });

        JButton previous = new JButton("Previous");
        previous.addActionListener(e -> {
            if (page-10>0) {
                allPictures=false;
                page -= 10;
                new Main().run();
                frame.dispose();
            }
        });


        JButton showAllPictures = new JButton("Show all pictures");
        showAllPictures.addActionListener(e -> {
                allPictures = true;
                page = 0;
                new Main().run();
                frame.dispose();
        });

        jMenu.add(download);
        jMenu.add(searchByName);
        jMenu.addSeparator();
        jMenu.add(next);
        jMenu.add(previous);
        jMenu.addSeparator();
        jMenu.addSeparator();
        jMenu.add(showAllPictures);
        jMenuBar.add(jMenu);
        frame.setJMenuBar(jMenuBar);

        JTabbedPane jTabbedPane = new JTabbedPane();
        for (int i =page; i<results.size(); i++) {
            if (allPictures || i< ((page)+10)){
                jTabbedPane.add(new JLabel(new ImageIcon("assets/" + results.get(i))));
            }
        }
        frame.add(jTabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main()::run);
    }
}

