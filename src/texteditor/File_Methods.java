/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.JOptionPane;


public class File_Methods {
    
    textEditorGui gui;
    String filename; // Dosya adini tutan degisken
    String fileAddress; // Dosya adresini tutan degisken
    String textString;
    
    // Constructor
    public File_Methods(textEditorGui gui){
        this.gui = gui;
    }
    
    // Yeni bir dosya olusturma islemi
    public void newFile(){
        if(filename == null)
            textString = "";
            
        if (hasUnsaveData()) {
            int answer = showWarningMessage();
            switch (answer) {
                case JOptionPane.YES_OPTION:
                    filename = JOptionPane.showInputDialog("File name:"); // Dosya adi degisiklik sorusu
                    gui.textArea.setText("");
                    gui.setTitle(filename);     
                    save();
                    break;   
                case JOptionPane.NO_OPTION:                  
                    filename = JOptionPane.showInputDialog("File name:"); // Dosya adi degisiklik sorusu
                    gui.textArea.setText("");
                    gui.setTitle(filename);         
                    break;
                     
                case JOptionPane.CANCEL_OPTION:;
                    break;
            }
        } 
        else {
            filename = JOptionPane.showInputDialog("File name:"); // Dosya adi degisiklik sorusu
            gui.textArea.setText("");
            gui.setTitle(filename);
        }      
    }
    
    // Open islemini gerceklestiren metot
    public void openFile(){
        //Dosya acma islemi icin FileDialog Load yapisi kullanildi.
        FileDialog fd = new FileDialog(gui,"Open", FileDialog.LOAD);
        fd.setVisible(true);


        // Dosya adi ve adresi elde edildi.
        if(fd.getFile() != null ){
            filename = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.setTitle(filename);
        }
        try {
            // Acma islemi sirasinda dosyayi yüklemek icin dosya adresi ve dosya adi kullanildi.
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + filename));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null){
                sb.append(line + "\n");
            }
            gui.textArea.setText(sb.toString());
            br.close();

        } catch (IOException e) {
            System.out.println("FILE NOT FOUND.");
        }
        textString = gui.textArea.getText();
    }
    
    // Save islemini gerceklestiren metot
    public void save(){
        
        // Kaydedilmis bir dosya yoksa sadece saveAs metodu cagrildi.
        if(filename == null){
            saveAs();           
        }
        // Aksi kosulda yeni bir kay±t olusturur gibi saveAs metotundaki gibi FileWriter yapisi kullanildi.
        else {         
            try {
                FileWriter fw = new FileWriter(fileAddress + filename);               
                fw.write(gui.textArea.getText());
                gui.setTitle(filename);
                fw.close();
                textString = gui.textArea.getText();
            } catch (IOException e) {
                System.out.println("ERROR");
            }
        }
        
    }

    // SaveAs islemini gerceklestiren metot
    public void saveAs(){
        
        // Kaydetme islemi icin FileDialog yapisi kullanildi.
        FileDialog fd = new FileDialog(gui,"SaveAs",FileDialog.SAVE);
        fd.setVisible(true);
    
        if(fd.getFile() != null){
            filename = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.setTitle(filename);           
        } 
        
        try {           
            // Kaydetme islemi icin FileWriter yapisi kullanildi.
            FileWriter fw = new FileWriter(fileAddress + filename);
            fw.write(gui.textArea.getText());
            fw.close();
            textString = gui.textArea.getText();
            
        } catch (IOException e) {
            System.out.println("ERROR");
        }
        
    }
    
    // Kaydedilmemis veri varsa true donduren metot
    private boolean hasUnsaveData() {
        return !(textString.equals(gui.textArea.getText()));
    }
    
    
    // cikis islemlerinde kullanilacak olan uyari kutusu
    private int showWarningMessage() {
        String[] buttonLabels = new String[] {"Yes", "No", "Cancel"};
        String defaultOption = buttonLabels[0];
        Icon icon = null;
         
        return JOptionPane.showOptionDialog(this.gui,
                "There's still something unsaved.\n" +
                "Do you want to save before exiting?",
                "Warning",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                icon,
                buttonLabels,
                defaultOption);    
    }
    
   
    // Exit islemini gerceklestiren metot
    public void exit(){
        if(filename == null){
            JOptionPane.showMessageDialog(gui, "The file to close was not found.");
            gui.textArea.setText("");
            filename = null;
            gui.setTitle(filename);
            return;
        }
        
        if (hasUnsaveData()) {
            int answer = showWarningMessage();
            switch (answer) {
                case JOptionPane.YES_OPTION:
                    saveAs();
                    break;   
                case JOptionPane.NO_OPTION:
                    gui.textArea.setText("");
                    filename = null;
                    gui.setTitle(filename);
                    break;                   
                case JOptionPane.CANCEL_OPTION:;
                    break;
            }
        } 
        else {
            gui.textArea.setText("");
            filename = null;
            gui.setTitle(filename);            
        }      
    } 
}
