package Interfaz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.utils.Align;

public class MenuScreen extends BaseScreen{

    public void initialize(){
        BaseActor background = new BaseActor(0,0, mainStage); // Initializing the background image actor;
        background.loadTexture( "themist.jpg" ); // setting the image
        background.setSize(1000,600);
        BaseActor title = new BaseActor(0,0, mainStage);
        title.loadTexture( "titulo.png" );

        TextButton btnJugar = new TextButton( "Jugar", BaseGame.textButtonStyle );

        btnJugar.addListener(
                e -> {
                    if (!(e instanceof InputEvent) ||
                            !((InputEvent) e).getType().equals(Type.touchDown))
                        return false;
                    BaseGame.setActiveScreen(new StoryScreen());
                    return false;
                }
        );
        TextButton btnCargar = new TextButton( "Cargar", BaseGame.textButtonStyle );
        btnCargar.addListener(
                e -> {
                    if (!(e instanceof InputEvent) ||
                            !((InputEvent) e).getType().equals(Type.touchDown))
                        return false;
                    BaseGame.setActiveScreen(new StoryScreen()); // Opens the StoryScreen class
                    return false;
                }
        );

        // Creates a quit button from the LibGdx text button library
        TextButton btnSalir = new TextButton( "Salir", BaseGame.textButtonStyle );


        // An event listener for the generated button to close the application.
        btnSalir.addListener(
                e -> {
                    if (!(e instanceof InputEvent) ||
                            !((InputEvent) e).getType().equals(Type.touchDown))
                        return false;
                    Gdx.app.exit(); // closes the app
                    return false;
                }
        );

        // Alineando el título y los botones a la derecha
        uiTable.add().expandX();  // Celda vacía para empujar a la izquierda
        uiTable.add(title).expand().top().left();
        uiTable.add().expand();
        uiTable.row();

        // Alineando los botones a la derecha debajo del título
        uiTable.add(btnJugar).expand().right(); // Alinea el botón Jugar a la derecha debajo del título
        uiTable.row();
        uiTable.add(btnCargar).expand().right(); // Alinea el botón Salir a la derecha debajo del título
        uiTable.row();
        uiTable.add(btnSalir).expand().right(); // Alinea el botón Cargar a la derecha debajo del título
        uiTable.row();

    }
    public void update(float dt) // Changes the game world and entities
    { }
    public boolean keyDown(int keyCode) // Method to check if the button was clicked.
    {
        if (Gdx.input.isKeyPressed(Keys.ENTER))
            BaseGame.setActiveScreen( new StoryScreen() );
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            Gdx.app.exit();
        return false;
    }

}
