package main.settingsWindow;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

//package components;

/**
 * This application that requires the following additional files:
 *   TreeDemoHelp.html
 *    arnold.html
 *    bloch.html
 *    chan.html
 *    jls.html
 *    swingtutorial.html
 *    tutorial.html
 *    tutorialcont.html
 *    vm.html
 */
import main.constants.imageDrivers.ButtonStatus;

import javax.swing.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;

public class TreeDemo extends JPanel implements TreeSelectionListener {

    private JTree tree;

    private JLabel jLabel;

    public TreeDemo() {
//        super(new GridLayout(1,0));

        //Create the nodes.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Images");
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        JScrollPane treeView = new JScrollPane(tree);

//        treeView.setMinimumSize(new Dimension(1000, 100));

        add(treeView);


        jLabel = new JLabel("select image");
        add(jLabel);


//        //Add the scroll panes to a split pane.
//        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
//        splitPane.setTopComponent(treeView);
//        splitPane.setBottomComponent(htmlView);
//
//        Dimension minimumSize = new Dimension(100, 50);
//        htmlView.setMinimumSize(minimumSize);
//        treeView.setMinimumSize(minimumSize);
//        splitPane.setDividerLocation(100);
//        splitPane.setPreferredSize(new Dimension(500, 300));
//
//        //Add the split pane to this panel.
//        add(splitPane);
    }

    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (node == null) return;

        Object nodeInfo = node.getUserObject();

        if (node.isLeaf()) {
            BookInfo book = (BookInfo)nodeInfo;

            displayURL(book.bookURL);

            System.out.println("new node pressed");
        }
    }

    private void displayURL(URL url) {
        if (url != null) {
            System.out.println("#1");
            jLabel.setText("image selected #1");
        } else { //null url
            jLabel.setText("image selected #2");
            System.out.println("#2");
        }
    }

    private class BookInfo {
        public String bookName;
        public URL bookURL;

        public BookInfo(String book, String filename) {
            bookName = book;
            bookURL = getClass().getResource(filename);

            if (bookURL == null) {
                System.err.println("Couldn't find file: " + filename);
            }
        }

        public String toString() {
            return bookName;
        }
    }

    private DefaultMutableTreeNode getTreeNode(String name) {
        return new DefaultMutableTreeNode(new BookInfo(name, "filename not added"));
    }

    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;

        category = new DefaultMutableTreeNode("button");
        top.add(category);

        category.add(getTreeNode("victory"));
        category.add(getTreeNode("defeat"));
        category.add(getTreeNode("play again"));


        category = new DefaultMutableTreeNode("closed tiles");
        top.add(category);

        category.add(getTreeNode("closed cell"));
        category.add(getTreeNode("flag"));
        category.add(getTreeNode("not sure"));


        category = new DefaultMutableTreeNode("opened tiles");
        top.add(category);

        category.add(getTreeNode("mine"));
        category.add(getTreeNode("0"));
        category.add(getTreeNode("1"));
        category.add(getTreeNode("2"));
        category.add(getTreeNode("3"));
        category.add(getTreeNode("4"));
        category.add(getTreeNode("5"));
        category.add(getTreeNode("6"));
        category.add(getTreeNode("7"));
        category.add(getTreeNode("8"));


        category = new DefaultMutableTreeNode("settings check flags");
        top.add(category);

        category.add(getTreeNode("false"));
        category.add(getTreeNode("not sure"));
        category.add(getTreeNode("true"));


        category = new DefaultMutableTreeNode("time");
        top.add(category);

        category.add(getTreeNode("0"));
        category.add(getTreeNode("1"));
        category.add(getTreeNode("2"));
        category.add(getTreeNode("3"));
        category.add(getTreeNode("4"));
        category.add(getTreeNode("5"));
        category.add(getTreeNode("6"));
        category.add(getTreeNode("7"));
        category.add(getTreeNode("8"));
        category.add(getTreeNode("9"));

    }


    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> {
            //Create and set up the window.
            JFrame frame = new JFrame("TreeDemo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Add content to the window.
            frame.add(new TreeDemo());
            frame.pack();
            frame.setVisible(true);
        });
    }
}