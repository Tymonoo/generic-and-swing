package edu.generic;

import generic.CMyLinkedList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.DefaultListModel;

public class CMainForm extends javax.swing.JFrame {
    private JPanel mainPanel;
    private JTextField strIdField;
    private JTextField strTextField;
    private JButton strButton;
    private JButton strClear;
    private JTextField strFind;
    private JButton strFindId;
    private JButton strFindIndex;
    private JList strList;
    private JTextField intIdField;
    private JTextField intTextField;
    private JButton intButton;
    private JList intList;
    private JTextField personIdFiled;
    private JTextField personTextFname;
    private JTextField personTextName;
    private JComboBox personYear;
    private JButton personButton;
    private JList personList;
    private JTextField imgIdField;
    private JButton imgButton;
    private JList imgList;

    private DefaultListModel<Object> modelStr;
    private DefaultListModel<Object> modelInt;
    private DefaultListModel<Object> modelPerson;
    private DefaultListModel<Object> modelImg;

    private CMyLinkedList<Integer,String> myListStr;
    private CMyLinkedList<Byte, Integer> myListInt;
    private CMyLinkedList<Long, CPerson> myListPerson;
    private CMyLinkedList<Integer, ImageIcon> myListImg;
    private JTextField strldField;
    private JLabel Id;


    public CMainForm(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.modelStr = new DefaultListModel<>();
        this.modelInt = new DefaultListModel<>();
        this.modelPerson = new DefaultListModel<>();
        this.modelImg = new DefaultListModel<>();

        this.myListStr = new CMyLinkedList<>();
        this.myListInt = new CMyLinkedList<>();
        this.myListPerson = new CMyLinkedList<>();
        this.myListImg = new CMyLinkedList<>();

        strList.setModel(modelStr);
        intList.setModel(modelInt);
        personList.setModel(modelPerson);
        imgList.setModel(modelImg);
        imgList.setCellRenderer(new CImgListRenderer(this.myListImg));


        strButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strButtonClick();
            }
        });
        strClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setStrButtonClearClick();
            }
        });
        strFindId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strGetByIdClick();
            }
        });
        strFindIndex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strGetByIndexClick();
            }
        });
        intButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intButtonClick();
            }
        });

        personButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personButtonClick();
            }
        });
        imgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageButtonClick();
            }
        });
    }

    private void strButtonClick(){
        try{
            Integer n = Integer.parseInt(strldField.getText());
            String text = strTextField.getText().trim();
            if(text.compareTo("") != 0){
                myListStr.add(n, text);
            }
            myListStr.printToList(modelStr);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Niepoprawna wartość. Komunikat: " + e.getMessage());
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void setStrButtonClearClick(){
        myListStr.clear();
        myListStr.printToList(modelStr);
    }

    private void strGetByIndexClick(){
        int idx = Integer.parseInt(strFind.getText());
        try {
            String s = myListStr.getByIndex(idx);
            JOptionPane.showMessageDialog(this, "Zwrócona wartość: " + s);

        }catch (IndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(this, "Brak elementu o indeksie = " + idx);
        }
    }

    private void strGetByIdClick(){
        int id = Integer.parseInt(strFind.getText());
        String s = myListStr.getById(id);
        if(s != null){
            JOptionPane.showMessageDialog(this, "Zwrócona wartość: " + s);
        }else{
            JOptionPane.showMessageDialog(this, "Brak elementu o id = " + id);
        }
    }

    private void intButtonClick(){
        try{
            Byte d = Byte.parseByte(intIdField.getText());
            Integer v = Integer.parseInt(intTextField.getText());
            myListInt.add(d,v);
            myListInt.printToList(modelInt);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Niepoprawna wartość. Komunikat: " + e.getMessage());
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void personButtonClick(){
        try{
            Long n = Long.parseLong(personIdFiled.getText());
            CPerson person = new CPerson(personTextFname.getText(),
                    personTextName.getText(),
                    Integer.parseInt(personYear.getItemAt(personYear.getSelectedIndex()).toString())
            );
            myListPerson.add(n, person);
            myListPerson.printToList(modelPerson);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Nieporawna wartość. Komunikat: " + e.getMessage());
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void imageButtonClick(){
        try {
            Integer n = Integer.parseInt(imgIdField.getText());
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("."));
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                myListImg.add(n, new ImageIcon(fc.getSelectedFile().getAbsolutePath()));
                myListImg.printToList(modelImg);
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Nieporawna wartość. Komunikat: " + e.getMessage());
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

}
