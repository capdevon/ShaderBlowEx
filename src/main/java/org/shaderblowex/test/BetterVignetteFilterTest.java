package org.shaderblowex.test;

import org.shaderblowex.filter.BetterVignetteFilter;

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
public class BetterVignetteFilterTest extends SimpleApplication implements ActionListener {

    BetterVignetteFilter betterVignetteFilter;

    BitmapText hintText;
    BitmapText debugText;

    float currentStrength = 0.5f;
    float currentExtent = 50.0f;
    float currentBlurSize = 0.1f;
    float currentBlurQuality = 20.0f;
    float grayPower = 2.0f;
    float grayMargin = 4.0f;
    
    /**
     * Start the jMonkeyEngine application
     */
    public static void main(String[] args) {
        BetterVignetteFilterTest app = new BetterVignetteFilterTest();
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

        // Keys
        inputManager.addMapping("StrDec", new KeyTrigger(KeyInput.KEY_1));
        inputManager.addMapping("StrInc", new KeyTrigger(KeyInput.KEY_2));
        inputManager.addMapping("ExDec", new KeyTrigger(KeyInput.KEY_3));
        inputManager.addMapping("ExInc", new KeyTrigger(KeyInput.KEY_4));
        inputManager.addMapping("BSizDec", new KeyTrigger(KeyInput.KEY_5));
        inputManager.addMapping("BSizInc", new KeyTrigger(KeyInput.KEY_6));
        inputManager.addMapping("BQuDec", new KeyTrigger(KeyInput.KEY_7));
        inputManager.addMapping("BQuInc", new KeyTrigger(KeyInput.KEY_8));
        inputManager.addMapping("GrPwDec", new KeyTrigger(KeyInput.KEY_9));
        inputManager.addMapping("GrPwInc", new KeyTrigger(KeyInput.KEY_0));
        inputManager.addMapping("GrMarDec", new KeyTrigger(KeyInput.KEY_MINUS));
        inputManager.addMapping("GrMarInc", new KeyTrigger(KeyInput.KEY_EQUALS));
        inputManager.addListener(this, new String[]{"StrInc"});
        inputManager.addListener(this, new String[]{"StrDec"});
        inputManager.addListener(this, new String[]{"ExInc"});
        inputManager.addListener(this, new String[]{"ExDec"});
        inputManager.addListener(this, new String[]{"BSizDec"});
        inputManager.addListener(this, new String[]{"BSizInc"});
        inputManager.addListener(this, new String[]{"BQuDec"});
        inputManager.addListener(this, new String[]{"BQuInc"});
        inputManager.addListener(this, new String[]{"GrPwDec"});
        inputManager.addListener(this, new String[]{"GrPwInc"});
        inputManager.addListener(this, new String[]{"GrMarDec"});
        inputManager.addListener(this, new String[]{"GrMarInc"});

        // Text
        BitmapFont font = getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        // Hint
        hintText = new BitmapText(font);
        hintText.setSize(font.getCharSet().getRenderedSize() * 1.5f);
        hintText.setColor(ColorRGBA.White);
        hintText.setText("VinStr:1/2 VinExt:3/4 BlrSize:5/6 BlrQual:7/8 GryPwr:9/0 GryMar:-/+ ");
        hintText.setLocalTranslation(0, this.getCamera().getHeight() - 10, 1.0f);
        hintText.updateGeometricState();
        guiNode.attachChild(hintText);
        // Info
        debugText = hintText.clone();
        debugText.setColor(ColorRGBA.White);
        debugText.setText("VinStr:" + currentStrength + " VinExt:" + currentExtent + " BlrSize:" + currentBlurSize
                + " BlrQual:" + currentBlurQuality + " GryPwr:" + grayPower + " GryMar:" + grayMargin);
        debugText.setLocalTranslation(0, hintText.getLocalTranslation().y - 30, 1.0f);
        debugText.updateGeometricState();
        guiNode.attachChild(debugText);

        ////////////////// Filter//////////////////////
        betterVignetteFilter = new BetterVignetteFilter();

        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(betterVignetteFilter);
        viewPort.addProcessor(fpp);

    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {

        if (!isPressed) {
            return;
        }

        if (name.equals("StrInc")) {
            currentStrength += 0.1f;
            if (currentStrength > 1.0) {
                currentStrength = 1.0f;
            }
            refreshDisplay();
            //
            betterVignetteFilter.setVignetteStrength(currentStrength);
        } else if (name.equals("StrDec")) {
            currentStrength -= 0.1f;
            if (currentStrength < 0) {
                currentStrength = 0;
            }
            refreshDisplay();
            //
            betterVignetteFilter.setVignetteStrength(currentStrength);
        } else if (name.equals("ExInc")) {
            currentExtent += 1.0f;
            if (currentExtent > 100.0) {
                currentExtent = 100.0f;
            }
            refreshDisplay();
            //
            betterVignetteFilter.setVignetteExtent(currentExtent);
        } else if (name.equals("ExDec")) {
            currentExtent -= 1.0f;
            if (currentExtent < 0) {
                currentExtent = 0;
            }
            refreshDisplay();
            //
            betterVignetteFilter.setVignetteExtent(currentExtent);
        } else if (name.equals("BSizInc")) {
            currentBlurSize += 0.02f;
            if (currentBlurSize > 1.0) {
                currentBlurSize = 1.0f;
            }
            refreshDisplay();
            //
            betterVignetteFilter.setBlurSize(currentBlurSize);
        } else if (name.equals("BSizDec")) {
            currentBlurSize -= 0.02f;
            if (currentBlurSize < 0) {
                currentBlurSize = 0;
            }
            refreshDisplay();
            //
            betterVignetteFilter.setBlurSize(currentBlurSize);
        } else if (name.equals("BQuInc")) {
            currentBlurQuality += 1.0f;
            if (currentBlurQuality > 50.0) {
                currentBlurQuality = 50.0f;
            }
            refreshDisplay();
            //
            betterVignetteFilter.setBlurQuality(currentBlurQuality);
        } else if (name.equals("BQuDec")) {
            currentBlurQuality -= 1.0f;
            if (currentBlurQuality < 0) {
                currentBlurQuality = 0;
            }
            refreshDisplay();
            //
            betterVignetteFilter.setBlurQuality(currentBlurQuality);
        } else if (name.equals("GrPwInc")) {
            grayPower += 0.1f;
            if (grayPower > 5.0) {
                grayPower = 5.0f;
            }
            refreshDisplay();
            //
            betterVignetteFilter.setGrayPower(grayPower);
        } else if (name.equals("GrPwDec")) {
            grayPower -= 0.1f;
            if (grayPower < 0) {
                grayPower = 0;
            }
            refreshDisplay();
            //
            betterVignetteFilter.setGrayPower(grayPower);
        } else if (name.equals("GrMarInc")) {
            grayMargin += 0.1f;
            if (grayMargin > 10.0) {
                grayMargin = 10.0f;
            }
            refreshDisplay();
            //
            betterVignetteFilter.setGrayMargin(grayMargin);
        } else if (name.equals("GrMarDec")) {
            grayMargin -= 0.1f;
            if (grayMargin < 0) {
                grayMargin = 0;
            }
            refreshDisplay();
            //
            betterVignetteFilter.setGrayMargin(grayMargin);
        }
    }

    void refreshDisplay() {
        debugText.setText("VinStr:" + currentStrength + " VinExt:" + currentExtent + " BlrSize:" + currentBlurSize
                + " BlrQual:" + currentBlurQuality + " GryPwr:" + grayPower + " GryMar:" + grayMargin);

    }

}
