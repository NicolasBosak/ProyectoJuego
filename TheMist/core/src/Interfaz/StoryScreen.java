package Interfaz;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static Interfaz.BaseGame.setActiveScreen;

public class StoryScreen extends BaseScreen {

    Scene scene;
    Background background;
    DialogBox dialogBox;
    BaseActor continueKey;
    Table buttonTable;

    public void initialize(){
        background = new Background(0,0, mainStage);
        background.setOpacity(0);
        BaseActor.setWorldBounds(background);

        dialogBox = new DialogBox(0,0, uiStage);
        dialogBox.setDialogSize(600, 150);
        dialogBox.setBackgroundColor( new Color(0.2f, 0.2f, 0.2f, 1) );
        dialogBox.setVisible(false);

        continueKey = new BaseActor(0,0,uiStage);
        continueKey.loadTexture("key-C.png");
        continueKey.setSize(32,32);
        continueKey.setVisible(false);

        dialogBox.addActor(continueKey);
        continueKey.setPosition( dialogBox.getWidth() - continueKey.getWidth(), 0 );

        buttonTable = new Table();
        buttonTable.setVisible(false);

        uiTable.add().expandY();
        uiTable.row();
        uiTable.add(buttonTable);
        uiTable.row();
        uiTable.add(dialogBox);

        scene = new Scene();
        mainStage.addActor(scene);
        wakeFromDream();
    }

    public void addTextSequence(String s){

        scene.addSegment( new SceneSegment( dialogBox, SceneActions.typewriter(s) ));
        scene.addSegment( new SceneSegment( continueKey, Actions.show() ));
        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( continueKey, Actions.hide() ));
    }

    // This is the first part of the story

    /**
     * Within each method of the story you will find a collection of sequential scenes that are set up strategically
     * to show up on screen in a specific order. These scenes are added to a array found in scenes
     * class and will generate each scene onto the interface one by one till the array is finished.
     * Each scene can dictate a specific background fading in and out of the interface, a dialog box fading in and out
     * of the interface, what text will show up on the interface, and more
     */

    public void wakeFromDream(){
        ArrayList<String> wakeFromDreamText = textFileReader("wakeFromDream.txt");
        ArrayList<String> wakeFromDreamTextpt2 = textFileReader("wakeFromDreampt2.txt");

        background.setAnimation( background.wakeFromDream );
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        readArray(wakeFromDreamText);
        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        readArray(wakeFromDreamTextpt2);
        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.start();
    }


    public void update(float dt){ }
    public boolean keyDown(int keyCode) {
        if (keyCode == Keys.C)
            scene.loadNextSegment();
        return false;
    }

    // This is the method that will convert each buffered line into a string element inside of a array
    public ArrayList<String> textFileReader (String fileName){
        ArrayList<String> sentences  = new ArrayList<>();

        String pathExtension = "";

        // Gets project path.
        String userDir = System.getProperty("user.dir");

        // This if-Else loop will check if the path directory has a forward slash or a back slash to help avoid the method crashing.
        if(userDir.contains("\\")){
            pathExtension = "Story_Text_Files\\";
        }else if (userDir.contains("/")){
            pathExtension = "Story_Text_Files/";
        }

        // Try catch statement to catch any errors with reading the text files
        try {


            // initializing the path name and textfile
            FileReader fr = new FileReader(pathExtension + fileName);

            // initializes and assigns the the file reader to the file buffer
            BufferedReader br = new BufferedReader(fr);

            // creates a string variable that will store the readline that bufferline finds
            String line;

            // A while loop that will keep looping until there is no more lines to assign to the line variable
            while ((line = br.readLine()) != null){

                // adds the lines to the sentence array
                sentences.add(line);
            }
            // closes the buffered reader
            br.close();

            // Catches any errors with finding the files
        }catch (IOException ioe){

            System.out.println("File not found");
        }
        // returns the sentence variable.
        return sentences;

    }

    // This method will collect each array and send each element to the addtextsequence method
    public void  readArray(ArrayList<String> text){
        for (String s : text){
            addTextSequence(s);
        }
    }
}
