package org.shaderblowex.filter;

import com.jme3.asset.AssetManager;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.post.Filter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import java.io.IOException;

public class BetterColorCorrectionFilter extends Filter {

    private static final float DEFAULT_BRIGHTNESS = 0.0f;
    private static final float DEFAULT_CONTRAST = 1.0f;
    private static final float DEFAULT_HUE = 0.0f;
    private static final float DEFAULT_SATURATION = .0f;
    private static final float DEFAULT_INVERT = 0.0f;
    private static final float DEFAULT_RED = 1.0f;
    private static final float DEFAULT_GREEN = 1.0f;
    private static final float DEFAULT_BLUE = 1.0f;
    private static final float DEFAULT_GAMMA = 1.0f;

    private float brightness = DEFAULT_BRIGHTNESS;
    private float contrast = DEFAULT_CONTRAST;
    private float hue = DEFAULT_HUE;
    private float saturation = DEFAULT_SATURATION;
    private float invert = DEFAULT_INVERT;
    private float red = DEFAULT_RED;
    private float green = DEFAULT_GREEN;
    private float blue = DEFAULT_BLUE;
    private float gamma = DEFAULT_GAMMA;

    /**
     * Creates a FFilter with default settings.
     */
    public BetterColorCorrectionFilter() {
        super("BetterColorCorrectionFilter");
    }

    /**
     * @param contrast default is 1,ranges from 0 to 10.
     * @param brightness default is 0. ranges from 0 to 1.
     * @param hue default 0, ranges from -1 to 1.
     * @param saturation default 0. ranges from -1 to 1.
     * @param invert default 0. ranges from 0 to 1.
     * @param red default 1. ranges from 0 to 10.
     * @param green default 1. ranges from 0 to 10.
     * @param blue default 1. ranges from 0 to 10.
     * @param gamma default 1. ranges from 0 to 10.
     */
    public BetterColorCorrectionFilter(float contrast, float brightness, float hue, float saturation, float invert, float red, float green, float blue, float gamma) {
        this();
        // 
        checkFloatArgument(contrast, 0f, 10f, "Contrast");
        checkFloatArgument(brightness, 0f, 1f, "Brightness");
        checkFloatArgument(hue, -1f, 1f, "Hue");
        checkFloatArgument(saturation, -1f, 1f, "Saturation");
        checkFloatArgument(invert, 0f, 1f, "Invert");
        checkFloatArgument(red, 0f, 10f, "Red");
        checkFloatArgument(green, 0f, 10f, "Green");
        checkFloatArgument(blue, 0f, 10f, "Blue");
        checkFloatArgument(gamma, 0f, 10f, "Gamma");
        //
        this.brightness = brightness;
        this.contrast = contrast;
        this.hue = hue;
        this.saturation = saturation;
        this.invert = invert;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.gamma = gamma;
    }

    @Override
    protected void initFilter(AssetManager manager, RenderManager renderManager, ViewPort vp, int w, int h) {
        material = new Material(manager, "ShaderBlowEx/MatDefs/BetterColorCorrection/BetterColorCorrection.j3md");
        material.setFloat("Brightness", brightness);
        material.setFloat("Contrast", contrast);
        material.setFloat("Hue", hue);
        material.setFloat("Saturation", saturation);
        material.setFloat("Invert", invert);
        material.setFloat("Red", red);
        material.setFloat("Green", green);
        material.setFloat("Blue", blue);
        material.setFloat("Gamma", gamma);
    }

    @Override
    protected Material getMaterial() {
        return material;
    }

    /**
     * Keep 0 to do nothing
     *
     * @param brightness the brightness.
     */
    public void setBrightness(float brightness) {
        this.brightness = FastMath.clamp(brightness, 0f, 1f);
        if (material != null) {
            material.setFloat("Brightness", brightness);
        }
    }

    /**
     * Default = 1
     *
     * @param saturation the saturation, needs to be between -1 and 1.
     */
    public void setSaturation(float saturation) {
        this.saturation = FastMath.clamp(saturation, -1.0f, 1.0f);
        if (material != null) {
            material.setFloat("Saturation", saturation);
        }
    }

    /**
     * Default = 1
     *
     * @param contrast the contrast value.
     */
    public void setContrast(float contrast) {
        this.contrast = FastMath.clamp(contrast, 0f, 10f);
        if (material != null) {
            material.setFloat("Contrast", contrast);
        }
    }

    /**
     * Default = 1. From 0;
     *
     * @param invert the invert value by which colors get inverted.
     */
    public void setInvert(float invert) {
        this.invert = FastMath.clamp(invert, 0f, 1f);
        if (material != null) {
            material.setFloat("Invert", invert);
        }
    }

    /**
     * Default = 1. From 0 to 10;
     *
     * @param red - red color to enhance.
     */
    public void setRed(float red) {
        this.red = FastMath.clamp(red, 0f, 10f);
        if (material != null) {
            material.setFloat("Red", red);
        }
    }

    /**
     * Default = 1. From 0 to 10;
     *
     * @param green - green color to enhance.
     */
    public void setGreen(float green) {
        this.green = FastMath.clamp(green, 0f, 10f);
        if (material != null) {
            material.setFloat("Green", green);
        }
    }

    /**
     * Default = 1. From 0 to 10;
     *
     * @param blue - blue color to enhance.
     */
    public void setBlue(float blue) {
        this.blue = FastMath.clamp(blue, 0f, 10f);
        if (material != null) {
            material.setFloat("Blue", blue);
        }
    }

    /**
     * Sets the hue value, range from -1 to 1, any other value doesn't work.
     */
    public void setHue(float hue) {
        this.hue = FastMath.clamp(hue, -1.0f, 1.0f);
        if (material != null) {
            material.setFloat("Hue", hue);
        }
    }

    /**
     * Set the gamma of the scene.
     *
     * @param The gamma of the scene.
     */
    public void setGamma(float gamma) {
        this.gamma = FastMath.clamp(gamma, 0f, 10f);
        if (material != null) {
            material.setFloat("Gamma", gamma);
        }
    }

    /**
     * @return the extra brightness
     */
    public float getBrightness() {
        return brightness;
    }

    /**
     * @return the extra saturation
     */
    public float getSaturation() {
        return saturation;
    }

    /**
     * @return the extra contrast
     */
    public float getContrast() {
        return contrast;
    }

    /**
     * @return The gamma of the scene.
     */
    public float getGamma() {
        return gamma;
    }

    /**
     * @return The red of the scene.
     */
    public float getRed() {
        return red;
    }

    /**
     * @return The green of the scene.
     */
    public float getGreen() {
        return green;
    }

    /**
     * @return The blue of the scene.
     */
    public float getBlue() {
        return blue;
    }

    /**
     * @return the invert value
     */
    public float getInvert() {
        return invert;
    }

    public float getHue() {
        return hue;
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule oc = ex.getCapsule(this);
        oc.write(contrast, "contrast", 1.0f);
        oc.write(brightness, "brightness", 1.0f);
        oc.write(hue, "hue", 1.0f);
        oc.write(saturation, "saturation", 1.0f);
        oc.write(invert, "invert", 1.0f);
        oc.write(red, "red", 1.0f);
        oc.write(blue, "blue", 1.0f);
        oc.write(green, "green", 1.0f);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule ic = im.getCapsule(this);
        contrast = ic.readFloat("contrast", 1.0f);
        brightness = ic.readFloat("brightness", 1.0f);
        hue = ic.readFloat("hue", 1.0f);
        saturation = ic.readFloat("saturation", 1.0f);
        invert = ic.readFloat("invert", 1.0f);
        red = ic.readFloat("red", 1.0f);
        blue = ic.readFloat("blue", 1.0f);
        green = ic.readFloat("green", 1.0f);
    }

    private void checkFloatArgument(float value, float min, float max, String name) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(name + " was " + value + " but should be between " + min + " and " + max);
        }
    }

}
