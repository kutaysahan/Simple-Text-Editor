
package texteditor;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


public class Find_Replace {
    textEditorGui gui; // 
    int currentPosition = 0; // Guncel pozisyonu tutmak icin degisken
    
    // Constructor
    public Find_Replace(textEditorGui gui) {
        this.gui = gui;
    }
    
    // Text icinde kelime bulma --> Find islemini gerceklestiren metot
    public void find(){
        String findFromText = gui.textArea.getText(); // Texte yazilan veri alindi
        String toFindText = gui.FindTextField.getText(); // Find kismina yazilan kelime alindi.
        
        // Bos string girisi oldugu durumda hata kontrolu
        if("".equals(toFindText)){
            JOptionPane.showMessageDialog(gui, "You must enter a value that will find.");
        }
        else{
            int indexOf = findFromText.indexOf(toFindText,currentPosition); // Aranan kelimenin textin neresinde oldugu bulundu.
            int length = toFindText.length(); // Aranmak istenen kelime uzunlugu
            Highlighter h = gui.textArea.getHighlighter(); // Highlighter sinifindan nesne olusturma
            h.removeAllHighlights();
            try {
                // Aranan kelime text area icinde bulunursa sari renge boyandi.
                h.addHighlight(indexOf, indexOf + length, new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
            } catch (BadLocationException ex) {
                JOptionPane.showMessageDialog(null, "Word Not Found.Try again.");
            }
            currentPosition = indexOf+length; // guncel pozisyon guncellendi
            
             // Guncel pozisyon sifirlama islemleri
            if(currentPosition >= findFromText.length())currentPosition = 0; // Eger guncel pozisyon aranan kelimenin uzunlugundan buyuk ya da esitse
            if(findFromText.indexOf(toFindText, currentPosition) == -1) // Aranan kelime text icinde bulunamiyorsa
                currentPosition = 0 ; 
        }
    }
    
    // Test sinifinda kullanilacak metot
    public int indexOf(String toFindText){
        currentPosition = 0;
        String findFromText = gui.textArea.getText();
        return findFromText.indexOf(toFindText,currentPosition); 
    }
    
    
    // İlk kelimeyi istenilen kelimeyle degistirme islemini gerceklestiren metot
    public void replace(){
        String findFromText = gui.textArea.getText(); // Text'e yazilan veri alindi.
        String toFindText = gui.FindTextField.getText(); // Bulunmak istenilen kelime
        
        // Bos string girisi oldugu durumda hata kontrolu
        if("".equals(toFindText)){
            JOptionPane.showMessageDialog(gui, "You must enter a value that will find.");
        }
        else{
            String withReplaceText = gui.ReplaceTextField.getText(); // Degistirilmek istenilen kelime
            gui.textArea.setText(findFromText.replaceFirst(toFindText, withReplaceText)); // Kelime degistirme islemi
            currentPosition = 0; // Guncel pozisyon sifirlandi
        }
    }
    
    // Butun istenilen kelimeleri bulma
    public void findAll(){
        currentPosition = 0; // Metot cagrildigi zaman guncel pozisyon 0
        String findFromText = gui.textArea.getText(); // Texte yazilan veri alindi
        String toFindText = gui.FindTextField.getText(); // Find kismina yazilan kelime alindi.
        Highlighter h = gui.textArea.getHighlighter(); // Highlighter sinifindan nesne olusturma
        h.removeAllHighlights(); 
        
        // Bos string girisi oldugu durumda hata kontrolu
        if("".equals(toFindText)){
            JOptionPane.showMessageDialog(gui, "You must enter a value that will find.");
        }
        else{
            while(findFromText.indexOf(toFindText, currentPosition) != -1){ // Texte yazilan veri icinde aranan kelime bulundugu surece
                int indexOf = findFromText.indexOf(toFindText,currentPosition); // Kelimenin var oldugu yerleri bulma
                int length = toFindText.length(); // Kelimenin uzunlugunu bulma islemi
                try {
                    // bulunan butun kelimeler sari renge boyandi.
                    h.addHighlight(indexOf, indexOf + length, new DefaultHighlighter.DefaultHighlightPainter(Color.yellow)); 
                } catch (BadLocationException ex) {
                    System.out.println("Word Not Found.");
                }
                currentPosition = indexOf+length ; // guncel pozisyon guncellendi
            }
            
            // Guncel pozisyon sifirlama islemleri
            if(currentPosition >= findFromText.length()){ // Eger guncel pozisyon aranan kelimenin uzunlugundan buyuk ya da esitse
                currentPosition = 0;
            }
            if(findFromText.indexOf(toFindText, currentPosition) == -1){ // Aranan kelime text icinde bulunamiyorsa
                currentPosition = 0 ;
            }
        }
    }
    
    public void ReplaceAll(){
    
        String findFromText = gui.textArea.getText();  // Texte yazilan veri alindi
        String toFindText = gui.FindTextField.getText(); // Bulunmak istenilen kelime
        
        // Bos string girisi oldugu durumda hata kontrolu
        if("".equals(toFindText)){
            JOptionPane.showMessageDialog(gui, "You must enter a value that will find.");
        }
        else{
            String withReplaceText = gui.ReplaceTextField.getText(); // degistirilmek istenilen kelime
            gui.textArea.setText(findFromText.replaceAll(toFindText, withReplaceText)); // butun kelimeleri degistiren kod
            currentPosition = 0; // guncel pozisyon sifirlandi
        }

    }
}
