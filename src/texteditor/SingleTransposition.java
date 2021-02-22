
package texteditor;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


public class SingleTransposition {

    textEditorGui gui;
    int currentPosition = 0;

    
    public SingleTransposition() {
    }

    SingleTransposition(textEditorGui gui) {
        this.gui = gui;
    }
    //txt dosyasından alınan kelimeler ArrayList'e atildi. Return değeri olarak dönderildi. 
    private ArrayList dictOpen() {
        File myFile = new File("words.txt");
        ArrayList<String> dictWords = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                dictWords.add(data);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(gui, "The words.txt couldn't be found.");
        }
        return dictWords;
    }
    
    //Kod karısıklığını önlemek için highlight atama metodu yazıldı.
    private void highlight(String color, int indexOf, int length){
              
        Highlighter h = gui.textArea.getHighlighter();
        if ("yellow".equals(color)) {
            try {
                h.addHighlight(indexOf, indexOf + length, new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
            } catch (BadLocationException ex) {
                Logger.getLogger(SingleTransposition.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        else if ("green".equals(color)) {
            try {
                h.addHighlight(indexOf, indexOf + length, new DefaultHighlighter.DefaultHighlightPainter(Color.green));
            } catch (BadLocationException ex) {
                Logger.getLogger(SingleTransposition.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        else if("red".equals(color)){
            try {
                h.addHighlight(indexOf, indexOf + length, new DefaultHighlighter.DefaultHighlightPainter(Color.red));
            } catch (BadLocationException ex) {
                Logger.getLogger(SingleTransposition.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Arka plan renklerini kaldıran komut yazıldı..
    private void removeHighlight(){
        Highlighter h = gui.textArea.getHighlighter();
        h.removeAllHighlights();
    }
    
    //Bir stringin integer olup olmadığını belirleyen kod bloğu yazıldı.
    private boolean isInteger(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
   
    //Hataları belirleyen ve degisen kod blogu yazildi.
    public void findMistakes() {
        
        currentPosition = 0;
        removeHighlight();//Var olan arkaplan rengi sıfırlandı.    
        
        ArrayList<String> dictWords = dictOpen(); // dictOpen metodu çağrıldı ve değiskene atandı.
        
        String text = gui.textArea.getText();
        String[] tokens = text.split("\\.|\\^|\\+|\\(|\\)|\\?|\\#|\\$|\\{|\\[|\\]|\\}|\\s+|\\t|\\n|;|,|:|!|'|%|&|/|=|<|>"); //Our text was splitted word by word with delimeters.
        int indexOf, length;
        // textArea ve txt dosyalarındaki kelimeleri karsılastırmak için iki for döngüsü yazıldı. (textArea döngüsü baslangıcı)
        for (int i = 0; i < tokens.length; i++) { 
            indexOf = text.indexOf(tokens[i], currentPosition); //TextArea'daki kelimerin indeksleri bulundu.
            length = tokens[i].length();
            // Uzunluğu 0 olan kelimelerin position'i sıfırlandı.
            if(length == 0){
                currentPosition += 1;
                continue;
            }          
            int yellowHighlight = 0;
            int greenHighlight = 0;
            int redHighlight = 0;
            String yellowH = "";          
            //txt dosyasındaki kelimeleri döndüren döngünün baslangıcı            
            for (int j = 0; j < dictWords.size(); j++) {
                //Kelimeler ayniysa veya kelime nümerik bir degerse arka plan renginin degismesi için gerekli bilgileri tutan kod blogu
                if(tokens[i].equalsIgnoreCase(dictWords.get(j)) || isInteger(tokens[i])){
                    greenHighlight ++;
                    break;
                }
                else{
                    //Kelimelerin uzunluklarının esit olup olmadığını kontrol eden kod bloğu
                    if(length == dictWords.get(j).length() ){
                        int count = 0;       //Bir kelimede kaç harfin aynı olduğunu tutan değisken           
                        char[] wordsArray = tokens[i].toCharArray();
                        char[] dictArray = dictWords.get(j).toCharArray();
                        for(int k = 0; k < length; k++){
                            if(wordsArray[k] == dictArray[k]){
                                count ++;
                            }
                        }                       
                        if(count == length - 2)  //Uzunluğun single transposition olması için gereksinimleri arayan kod bloğu
                        {
                            boolean arrayEquality;
                            Arrays.sort(wordsArray);
                            Arrays.sort(dictArray);
                            arrayEquality = Arrays.equals(wordsArray, dictArray);
                           
                            //Kelimelerin aynı harflere sahip olup olmadığını ve bu bilgiye göre arka plan değisikliklerini tutan kod bloğu
                            if(arrayEquality){                                                                 
                                yellowHighlight ++;
                                yellowH = dictWords.get(j);                                
                            }
                            //Kelimelerin single transposition için doğru uzunluğa sahip olduğu ama aynı harflere sahip olmadığı durumunda arka plan rengini değisen kod bloğu 
                            else{
                                if(dictWords.size() - 1 == j){
                                    redHighlight ++;
                                }                            
                            }
                        }
                        //Kelimeler single transposition için doğru uzunluğa sahip olmadığında arka plan değisikliklerini tutan kod bloğu.  
                        else{
                            if(dictWords.size() - 1 == j){
                                redHighlight++;
                            }
                        }
                    }
                    //Kelimelerin uzunluğu esit olmadığında arka plan rengini değisikliklerini tutan kod bloğu.
                    else{
                        if(dictWords.size() - 1 == j){
                            redHighlight++;
                        }
                    }                   
                }              
            }          
            //Kelimenin aynısı bulunduysa arka plan rengini yesile dönderen kod bloğu.
            if(greenHighlight > 0){                               
                highlight("green", indexOf, length);
            }
            else{
                //Kelimenin sözlükte bulunmadığı fakat single transposition kurallarını sağlayan farklı kelime bulunduğunda arka plan rengini sarıya dönderip kelimeyi değistiren kod bloğu
                if(yellowHighlight > 0){
                    gui.textArea.replaceRange(yellowH, indexOf, indexOf + length);
                    highlight("yellow", indexOf, length);
                }
                //Kelimenin sözlükte bulunmadığı ve single transposition kurallarını sağlamadığı durumlarda arka plan rengini kırmızıya döndüren kod bloğu.
                else{
                    highlight("red", indexOf, length);
                }
            }
            
            currentPosition = length + indexOf;
        }          
    }   
}
