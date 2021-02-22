package texteditor;

import java.util.Stack;


public class Edit_Methods {
    

    textEditorGui gui; // GUI yapisina Edit_Methods sinifinda kullanabilmek icin yapilan degisken
    Stack<String> undoActions = new Stack<>(); // Undo metodu icin String tipinde stack
    
    
    // Constructor
    public Edit_Methods(textEditorGui gui) {
        this.gui = gui;
    }
    
    // Stack pop islemi yaparak değerleri geri alan metot
    public void PopUndoStack(){
        
        // Veri kalmadıysa undo işlemi boş döndürür
        if(undoActions.size() < 1){
            return;
        }
        gui.textArea.setText(undoActions.pop());
    }
    
    
    // Key Pressed islemine baglanan metot
    public void PushUndoStack(){
        undoActions.push(gui.textArea.getText());
    }
    
}
