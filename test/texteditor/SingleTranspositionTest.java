
package texteditor;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SingleTranspositionTest {
    
    public SingleTranspositionTest() {
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
     * Test of findMistakes method, of class SingleTransposition.
     */
    @Test
    public void testFindMistakes() {
        
        System.out.println("Find Mistakes");
        textEditorGui gui = new textEditorGui();
        System.out.print("WRONG --> ");
        gui.textArea.setText("abandno"); // Kelime hatali yazildi.
        System.out.println(gui.textArea.getText());
  
        SingleTransposition instance = new SingleTransposition(gui);
        instance.findMistakes(); // find mistakes test ediliyor
        
        System.out.print("CORRECT--> ");
        assertEquals("abandon", gui.textArea.getText()); // karsilistirilma islemi
        System.out.println(gui.textArea.getText());
    }
    
}
