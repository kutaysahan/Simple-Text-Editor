
package texteditor;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Find_ReplaceTest {
    
    textEditorGui gui = new textEditorGui();
    
    public Find_ReplaceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class Find_Replace.
     */
    @Test
    public void testFind() {
        System.out.println("find"); 
        gui.textArea.setText("Hello World"); // Text metni Hello World kabul edildi.
        
       
        gui.FindTextField.setText("Hello"); // Bulunması istenilen kelime
        Find_Replace instance = new Find_Replace(gui); // Find_Replace sınıfından nesne oluşturuldu.
        
        instance.find(); // find metodu çağrıldı.
        
        
        int x = instance.indexOf("Hello"); // Kontrol işlemi
        assertEquals(x,0);
        System.out.println("---------------------------");
    }

    /**
     * Test of replace method, of class Find_Replace.
     */
    @Test
    public void testReplace() {

        System.out.println("replace");  
        gui.textArea.setText("Hello World"); // text area metni yazildi.
        
        
        gui.FindTextField.setText("Hello"); // degistirilmek istenilen kelime
        gui.ReplaceTextField.setText("Bye"); // degisim sonucu olusan yeni kelime
        
        Find_Replace instance = new Find_Replace(gui); // Find_Replace sinifindan kontrol amacli nesne olusturma islemi
        instance.replace(); // replace metodu cagrildi.
        int x = instance.indexOf("Bye"); // replace islemi
        assertEquals(x,0); // kontrol islemi
        System.out.println("---------------------------");
    }
    /**
     * Test of replace method, of class Find_Replace.
     */
    @Test
    public void testReplaceAll(){
        System.out.println("Replace All");
        
        gui.textArea.setText("hey hey hey hello hey");  //text area metni ayarlandi.
        String firstState =  gui.textArea.getText(); // text area metni stringe atandi.
        System.out.println("First State: " + firstState); // ilk durum kontrolü
        gui.FindTextField.setText("hey"); // degistirilmesi gereken kelime
        gui.ReplaceTextField.setText("Replaced"); // degistirilip yerine yazilan kelime
        
        
        Find_Replace instance = new Find_Replace(gui);
        instance.ReplaceAll(); // replaceAll metodu cagrildi.
        
        String lastState = gui.textArea.getText(); // textin son durumu yazildi.
   
        System.out.println("Last State: " + lastState);
        
        assertEquals("Replaced Replaced Replaced hello Replaced", lastState); // kontrol etme islemi
        System.out.println("---------------------------------------------------");
    }
    
    
  
}
