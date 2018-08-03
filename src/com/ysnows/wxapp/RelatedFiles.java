package com.ysnows.wxapp;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTree;

class RelatedFiles extends JFrame {
    private JPanel mPanel;
    private JButton button1;
    private JList list1;

    public RelatedFiles() {
        setContentPane(mPanel);

        DefaultListModel<String> stringDefaultListModel = new DefaultListModel<>();
        stringDefaultListModel.addElement("article.js");
        stringDefaultListModel.addElement("article.wxml");
        stringDefaultListModel.addElement("article.wss");


        list1.setModel(stringDefaultListModel);
        list1.requestFocus();

    }
}
