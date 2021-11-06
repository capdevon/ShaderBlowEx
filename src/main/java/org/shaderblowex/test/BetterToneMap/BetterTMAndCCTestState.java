/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shaderblowex.test.BetterToneMap;

import org.shaderblowex.filter.BetterColorCorrectionFilter;
import org.shaderblowex.filter.BetterToneMapFilter;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.math.ColorRGBA;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Node;

/**
 *
 * @author xxx
 */
public class BetterTMAndCCTestState extends BaseAppState implements ActionListener {

    BetterToneMapFilter betterToneMapFilter;
    BetterColorCorrectionFilter betterColorCorrectionFilter;
    BitmapText hintText;
    BitmapText debugText;
    BitmapText hintText2;
    BitmapText debugText2;

    int currentType = BetterToneMapFilter.TYPE_LINEAR;
    float currentExposure = 1.0f;
    float currentGamma = 1.0f;

    float currentContrast = 1.0f;
    float currentBrightness = 0.0f;
    float currentHue = 0.0f;
    float currentInvert = 0.0f;
    float currentRed = 1.0f;
    float currentGreen = 1.0f;
    float currentBlue = 1.0f;
    float currentGamma2 = 1.0f;
    float currentSaturation = 0.0f;

    Integer[] types = new Integer[]{BetterToneMapFilter.TYPE_LINEAR, BetterToneMapFilter.TYPE_SIMPLE_REINHARD,
        BetterToneMapFilter.TYPE_LUMA_BASED_REINHARD, BetterToneMapFilter.TYPE_WHITE_PRESERVING_REINHARD,
        BetterToneMapFilter.TYPE_RONBIN_DAHOUSE, BetterToneMapFilter.TYPE_ACES_FILM,
        BetterToneMapFilter.TYPE_ACES_ABSOLUTE_FILM, BetterToneMapFilter.TYPE_FILMIC,
        BetterToneMapFilter.TYPE_UNCHARTED2, BetterToneMapFilter.TYPE_DX11DSK, BetterToneMapFilter.TYPE_TIMOTHY,
        BetterToneMapFilter.TYPE_EXPONENTIAL, BetterToneMapFilter.TYPE_UNREAL, BetterToneMapFilter.TYPE_AMD_LOTTES,
        BetterToneMapFilter.TYPE_REINHARD2, BetterToneMapFilter.TYPE_UCHIMURA, BetterToneMapFilter.TYPE_FERWERDA,
        BetterToneMapFilter.TYPE_HAARNPIETERDUIKER, BetterToneMapFilter.TYPE_WARD,
        BetterToneMapFilter.TYPE_TUMBLIN_RESHMEIER, BetterToneMapFilter.TYPE_SCHICK,
        BetterToneMapFilter.TYPE_REINHARD_EXTENDED, BetterToneMapFilter.TYPE_REINHARD_DEVLIN,
        BetterToneMapFilter.TYPE_MEAN_VALUE, BetterToneMapFilter.TYPE_MAX_DIVISION,
        BetterToneMapFilter.TYPE_LOGARITMIC, BetterToneMapFilter.TYPE_INSOMNIAC,
        BetterToneMapFilter.TYPE_GRAHAM_ALDRIDGE_FILMIC, BetterToneMapFilter.TYPE_EXPONENTIATION,
        BetterToneMapFilter.TYPE_EXPONENTIAL2, BetterToneMapFilter.TYPE_DROGO, BetterToneMapFilter.TYPE_CLAMPING,
        BetterToneMapFilter.TYPE_JODIE_ROBO, BetterToneMapFilter.TYPE_JODIE_REINHARD,
        BetterToneMapFilter.TYPE_BARTEROPE, BetterToneMapFilter.TYPE_GIFFORD,};

    String[] names = new String[]{"Linear", "SimpleReinhard", "LumaBasedReinhard", "WhitePreservingLumaBasedReinhard",
        "RomBinDaHouse", "ACESFilm", "ACESAbsoluteFilm", "Filmic", "Uncharted2", "DX11DSK", "Timothy",
        "Exponential", "Unreal", "AMDLottes", "Reinhard2", "Uchimura", "Ferwerda", "HaarmPieterDuiker", "Ward",
        "TumblinRushmeier", "Schlick", "ReinhardExtended", "ReinhardDevlin", "MeanValue", "MaximumDivision",
        "Logarithmic", "Insomniac", "GrahamAldridgeFilmic", "Exponentiation", "Exponential2", "Drogo", "Clamping",
        "JodieRobo", "JodieReinhard", "Barterope", "GiffordTonemap"
    };
    
    private InputManager inputManager;

    @Override
    protected void initialize(Application app) {
    	
    	SimpleApplication sapp = (SimpleApplication) app;
        this.inputManager = app.getInputManager();
        Node rootNode = sapp.getRootNode();
        Node guiNode = sapp.getGuiNode();

        // Text
        BitmapFont font = app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        // Hint
        hintText = new BitmapText(font);
        hintText.setSize(font.getCharSet().getRenderedSize() * 1.5f);
        hintText.setColor(ColorRGBA.Red);
        hintText.setText("Exposure:O/P Gamma:K/L Type:Space/Backspace");
        hintText.setLocalTranslation(0, app.getCamera().getHeight() - 10, 1.0f);
        hintText.updateGeometricState();
        guiNode.attachChild(hintText);
        // Info
        debugText = hintText.clone();
        debugText.setColor(ColorRGBA.White);
        debugText.setText("Exp:" + currentExposure + " Gam:" + currentGamma + " Type:" + names[currentType]);
        debugText.setLocalTranslation(0, hintText.getLocalTranslation().y - 30, 1.0f);
        debugText.updateGeometricState();
        guiNode.attachChild(debugText);

        // CC
        // Hint
        hintText2 = new BitmapText(font);
        hintText2.setSize(font.getCharSet().getRenderedSize() * 1.5f);
        hintText2.setColor(ColorRGBA.Red);
        hintText2.setText("Cntr:1/2 Brgh:3/4 Hue:5/6 Sat:7/8 Inv: 9/0 Gam:-/+ R:[/] G:;/' B:</>");
        hintText2.setLocalTranslation(0, debugText.getLocalTranslation().y - 30, 1.0f);
        hintText2.updateGeometricState();
        guiNode.attachChild(hintText2);
        // Info
        debugText2 = hintText2.clone();
        debugText2.setColor(ColorRGBA.White);
        debugText2.setText("Cntr:" + currentContrast + " Brgh:" + currentBrightness + " Hue:" + currentHue + " Sat:"
                + currentSaturation + " Inv:" + currentInvert + " Gam:" + currentGamma2 + " R:" + currentRed + " G:"
                + currentGreen + " B:" + currentBlue);
        debugText2.setLocalTranslation(0, hintText2.getLocalTranslation().y - 30, 1.0f);
        debugText2.updateGeometricState();
        guiNode.attachChild(debugText2);

        ////////////////// Filter//////////////////////
        betterToneMapFilter = new BetterToneMapFilter(currentType, currentExposure, currentGamma);
        betterColorCorrectionFilter = new BetterColorCorrectionFilter(currentContrast, currentBrightness, currentHue,
                currentSaturation, currentInvert, currentRed, currentGreen, currentBlue, currentGamma);

        FilterPostProcessor fpp = new FilterPostProcessor(app.getAssetManager());
        fpp.addFilter(betterToneMapFilter);
        fpp.addFilter(betterColorCorrectionFilter);
        app.getViewPort().addProcessor(fpp);
        
        configureInputs();
    }

    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
    }
    
    private void configureInputs() {
    	// Keys for BetterToneMapFilter
    	addMapping("NextType", new KeyTrigger(KeyInput.KEY_SPACE));
        addMapping("PrevType", new KeyTrigger(KeyInput.KEY_BACK));
        addMapping("ExpInc", new KeyTrigger(KeyInput.KEY_P));
        addMapping("ExpDec", new KeyTrigger(KeyInput.KEY_O));
        addMapping("GammaDec", new KeyTrigger(KeyInput.KEY_K));
        addMapping("GammaInc", new KeyTrigger(KeyInput.KEY_L));

        // Keys for BetterColorCorrectionFilter
        addMapping("CntrstDec", new KeyTrigger(KeyInput.KEY_1));
        addMapping("CntrstInc", new KeyTrigger(KeyInput.KEY_2));
        addMapping("BrghtnssDec", new KeyTrigger(KeyInput.KEY_3));
        addMapping("BrghtnssInc", new KeyTrigger(KeyInput.KEY_4));
        addMapping("HueDec", new KeyTrigger(KeyInput.KEY_5));
        addMapping("HueInc", new KeyTrigger(KeyInput.KEY_6));
        addMapping("SaturDec", new KeyTrigger(KeyInput.KEY_7));
        addMapping("SaturInc", new KeyTrigger(KeyInput.KEY_8));
        addMapping("InvrtDec", new KeyTrigger(KeyInput.KEY_9));
        addMapping("InvrtInc", new KeyTrigger(KeyInput.KEY_0));
        addMapping("RedDec", new KeyTrigger(KeyInput.KEY_LBRACKET));
        addMapping("RedInc", new KeyTrigger(KeyInput.KEY_RBRACKET));
        addMapping("GreenDec", new KeyTrigger(KeyInput.KEY_SEMICOLON));
        addMapping("GreenInc", new KeyTrigger(KeyInput.KEY_APOSTROPHE));
        addMapping("BlueDec", new KeyTrigger(KeyInput.KEY_COMMA));
        addMapping("BlueInc", new KeyTrigger(KeyInput.KEY_PERIOD));
        addMapping("GammaDec2", new KeyTrigger(KeyInput.KEY_MINUS));
        addMapping("GammaInc2", new KeyTrigger(KeyInput.KEY_EQUALS));
    }
    
    private void addMapping(String mappingName, Trigger... triggers) {
    	inputManager.addMapping(mappingName, triggers);
    	inputManager.addListener(this, mappingName);
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {

        if (!isPressed) {
            return;
        }

        if (name.equals("NextType")) {
            currentType++;
            currentType = currentType % names.length;
            refreshDisplay();
            //
            betterToneMapFilter.setType(types[currentType]);
        } else if (name.equals("PrevType")) {
            currentType--;
            if (currentType < 0) {
                currentType = (names.length - 1);
            }
            refreshDisplay();
            //
            betterToneMapFilter.setType(types[currentType]);
        } else if (name.equals("ExpInc")) {
            currentExposure += 0.1;
            refreshDisplay();
            //
            betterToneMapFilter.setExposure(currentExposure);
        } else if (name.equals("ExpDec")) {
            currentExposure -= 0.1;
            if (currentExposure < 0) {
                currentExposure = 0;
            }
            refreshDisplay();
            //
            betterToneMapFilter.setExposure(currentExposure);
        } else if (name.equals("GammaInc")) {
            currentGamma += 0.1;
            refreshDisplay();
            //
            betterToneMapFilter.setGamma(currentGamma);
        } else if (name.equals("GammaDec")) {
            currentGamma -= 0.1;
            if (currentGamma < 0) {
                currentGamma = 0;
            }
            refreshDisplay();
            //
            betterToneMapFilter.setGamma(currentGamma);
        } else if (name.equals("CntrstInc")) {
            currentContrast += 0.1;
            if (currentContrast > 10) {
                currentContrast = 10;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setContrast(currentContrast);
        } else if (name.equals("CntrstDec")) {
            currentContrast -= 0.1;
            if (currentContrast < 0) {
                currentContrast = 0;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setContrast(currentContrast);
        } else if (name.equals("BrghtnssInc")) {
            currentBrightness += 0.1;
            if (currentBrightness > 1) {
                currentBrightness = 1;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setBrightness(currentBrightness);
        } else if (name.equals("BrghtnssDec")) {
            currentBrightness -= 0.1;
            if (currentBrightness < 0) {
                currentBrightness = 0;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setBrightness(currentBrightness);
        } else if (name.equals("HueInc")) {
            currentHue += 0.1;
            if (currentHue > 1) {
                currentHue = 1f;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setHue(currentHue);
        } else if (name.equals("HueDec")) {
            currentHue -= 0.1;
            if (currentHue < -1) {
                currentHue = -1;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setHue(currentHue);
        } else if (name.equals("SaturInc")) {
            currentSaturation += 0.1;
            if (currentSaturation > 1.0f) {
                currentSaturation = 1f;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setSaturation(currentSaturation);
        } else if (name.equals("SaturDec")) {
            currentSaturation -= 0.1;
            if (currentSaturation < -1) {
                currentSaturation = -1;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setSaturation(currentSaturation);
        } else if (name.equals("GammaInc2")) {
            currentGamma2 += 0.1;
            refreshDisplay();
            //
            betterColorCorrectionFilter.setGamma(currentGamma2);
        } else if (name.equals("GammaDec2")) {
            currentGamma2 -= 0.1;
            if (currentGamma2 < 0) {
                currentGamma2 = 0;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setGamma(currentGamma2);
        } else if (name.equals("InvrtInc")) {
            currentInvert += 0.1;
            if (currentInvert > 1) {
                currentInvert = 1;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setInvert(currentInvert);
        } else if (name.equals("InvrtDec")) {
            currentInvert -= 0.1;
            if (currentInvert < 0) {
                currentInvert = 0;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setInvert(currentInvert);
        } else if (name.equals("RedInc")) {
            currentRed += 0.1;
            if (currentRed > 10) {
                currentRed = 10;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setRed(currentRed);
        } else if (name.equals("RedDec")) {
            currentRed -= 0.1;
            if (currentRed < 0) {
                currentRed = 0;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setRed(currentRed);
        } else if (name.equals("GreenInc")) {
            currentGreen += 0.1;
            if (currentGreen > 10) {
                currentGreen = 10;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setGreen(currentGreen);
        } else if (name.equals("GreenDec")) {
            currentGreen -= 0.1;
            if (currentGreen < 0) {
                currentGreen = 0;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setGreen(currentGreen);
        } else if (name.equals("BlueInc")) {
            currentBlue += 0.1;
            if (currentBlue > 10) {
                currentBlue = 10;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setBlue(currentBlue);
        } else if (name.equals("BlueInc")) {
            currentBlue -= 0.1;
            if (currentBlue < 0) {
                currentBlue = 0;
            }
            refreshDisplay();
            //
            betterColorCorrectionFilter.setBlue(currentBlue);
        }

    }

    void refreshDisplay() {
        debugText.setText("Exp:" + currentExposure + " Gamma:" + currentGamma + " Type:" + names[currentType]);
        debugText2.setText("Cntr:" + currentContrast + " Brgh:" + currentBrightness + " Hue:" + currentHue + " Sat:"
                + currentSaturation + " Inv:" + currentInvert + " Gam:" + currentGamma2 + " R:" + currentRed + " G:"
                + currentGreen + " B:" + currentBlue);

    }

}
