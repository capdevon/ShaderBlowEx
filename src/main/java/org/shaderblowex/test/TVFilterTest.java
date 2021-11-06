package org.shaderblowex.test;

import org.shaderblowex.filter.TVFilter;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.system.AppSettings;

/**
 *
 * @author xxx
 */
public class TVFilterTest extends SimpleApplication implements ActionListener {

    TVFilter tVFilter;

    BitmapText hintText;
    BitmapText debugText;

    boolean currentVhs = true;
    boolean currentLine = true;
    boolean currentGrain = true;
    boolean currentScanline = true;
    boolean currentVignette = true;
    
    /**
     * Start the jMonkeyEngine application
     */
    public static void main(String[] args) {
        TVFilterTest app = new TVFilterTest();
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1280, 720);
        app.setSettings(settings);
        app.setPauseOnLostFocus(false);
        app.start();
    }

    @Override
    public void simpleInitApp() {

    	// No stats
        setDisplayStatView(false);
        setDisplayFps(false);
        
        // faster cam
        cam.setLocation(new Vector3f(0.0f, 6.0f, 15.0f));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
        flyCam.setMoveSpeed(5f);
        
        stateManager.attach(new SceneAppState());

        //Keys
        inputManager.addMapping("VhsEn", new KeyTrigger(KeyInput.KEY_1));
        inputManager.addMapping("LineEn", new KeyTrigger(KeyInput.KEY_2));
        inputManager.addMapping("GrainEn", new KeyTrigger(KeyInput.KEY_3));
        inputManager.addMapping("ScEn", new KeyTrigger(KeyInput.KEY_4));
        inputManager.addMapping("VinEn", new KeyTrigger(KeyInput.KEY_5));

        inputManager.addListener(this, new String[]{"VhsEn"});
        inputManager.addListener(this, new String[]{"LineEn"});
        inputManager.addListener(this, new String[]{"GrainEn"});
        inputManager.addListener(this, new String[]{"ScEn"});
        inputManager.addListener(this, new String[]{"VinEn"});

        //Text
        BitmapFont font = getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        //Hint
        hintText = new BitmapText(font);
        hintText.setSize(font.getCharSet().getRenderedSize() * 1.5f);
        hintText.setColor(ColorRGBA.Red);
        hintText.setText("VHS:1 Line:2 Grain:3 Scanline:4 Vignette: 5");
        hintText.setLocalTranslation(0, this.getCamera().getHeight() - 10, 1.0f);
        hintText.updateGeometricState();
        guiNode.attachChild(hintText);
        //Info
        debugText = hintText.clone();
        debugText.setColor(ColorRGBA.White);
        debugText.setText("VHS:" + currentVhs + " Line:" + currentLine + " Grain:" + currentGrain + " Scanline:" + currentScanline + " Vignette:" + currentVignette);
        debugText.setLocalTranslation(0, hintText.getLocalTranslation().y - 30, 1.0f);
        debugText.updateGeometricState();
        guiNode.attachChild(debugText);

        //////////////////Filter//////////////////////
        tVFilter = new TVFilter(currentVhs, currentLine, currentGrain, currentScanline, currentVignette);
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(tVFilter);
        viewPort.addProcessor(fpp);
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {

        if (!isPressed) {
            return;
        }

        if (name.equals("VhsEn")) {
            currentVhs = !currentVhs;
            refreshDisplay();
            tVFilter.setVhs(currentVhs);
        } else if (name.equals("LineEn")) {
            currentLine = !currentLine;
            refreshDisplay();
            tVFilter.setLine(currentLine);
        } else if (name.equals("GrainEn")) {
            currentGrain = !currentGrain;
            refreshDisplay();
            tVFilter.setGrain(currentGrain);
        } else if (name.equals("ScEn")) {
            currentScanline = !currentScanline;
            refreshDisplay();
            tVFilter.setScanline(currentScanline);
        } else if (name.equals("VinEn")) {
            currentVignette = !currentVignette;
            refreshDisplay();
            tVFilter.setVignette(currentVignette);
        }
    }

    void refreshDisplay() {
        debugText.setText("VHS:" + currentVhs + " Line:" + currentLine + " Grain:" + currentGrain + " Scanline:" + currentScanline + " Vignette:" + currentVignette);
    }

}
