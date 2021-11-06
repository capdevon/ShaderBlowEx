package org.shaderblowex.filter;

import java.io.IOException;

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

public class BetterVignetteFilter extends Filter {

    /**
     * Default values
     */
    private static final float DEFAULT_VIGNETTING_STRENGTH = 0.5f;
    private static final float DEFAULT_VIGNETTING_EXTENT = 50f;
    private static final float DEFAULT_BLUR_SIZE = 0.1f;
    private static final float DEFAULT_BLUR_QUALITY = 20f;
    private static final float DEFAULT_GRAY_POWER = 2.0f;
    private static final float DEFAULT_GRAY_MARGIN = 4f;

    private float vignetteStrength = DEFAULT_VIGNETTING_STRENGTH;
    private float vignetteExtent = DEFAULT_VIGNETTING_EXTENT;
    private float blurQuality = DEFAULT_BLUR_QUALITY;
    private float blurSize = DEFAULT_BLUR_SIZE;
    private float grayPower = DEFAULT_GRAY_POWER;
    private float grayMargin = DEFAULT_GRAY_MARGIN;

    /**
     * Default Constructor.
     */
    public BetterVignetteFilter() {
        super("BetterVignetteFilter");
    }

    public BetterVignetteFilter(float vignetteStrength, float vignetteExtent, float blurQuality, float blurSize, float grayPower, float grayMargin) {
        this();
        this.vignetteStrength = vignetteStrength;
        this.vignetteExtent = vignetteExtent;
        this.blurQuality = blurQuality;
        this.blurSize = blurSize;
        this.grayPower = grayPower;
        this.grayMargin = grayPower;
    }

    @Override
    protected Material getMaterial() {
        return this.material;
    }

    @Override
    protected void initFilter(AssetManager manager, RenderManager renderManager, ViewPort vp, int w, int h) {
        material = new Material(manager, "ShaderBlowEx/MatDefs/BetterVignette/BetterVignette.j3md");
        material.setFloat("VignetteStrength", vignetteStrength);
        material.setFloat("VignetteExtent", vignetteExtent);
        material.setFloat("BlurQuality", blurQuality);
        material.setFloat("BlurSize", blurSize);
        material.setFloat("GrayPower", grayPower);
        material.setFloat("GrayMargin", grayMargin);
    }

    public void setVignetteStrength(float vignetteStrength) {
        //checkFloatArgument(vignetteStrength, 0, 1.0f, "VignetteStrength");
        this.vignetteStrength = FastMath.clamp(vignetteStrength, 0, 1.0f);
        if (material != null) {
            material.setFloat("VignetteStrength", vignetteStrength);
        }
    }

    public float getVignetteStrength() {
        return vignetteStrength;
    }

    public void setVignetteExtent(float vignetteExtent) {
        //checkFloatArgument(vignetteExtent, 0, 100.0f, "VignetteExtent");
        this.vignetteExtent = FastMath.clamp(vignetteExtent, 0, 100.0f);
        if (material != null) {
            material.setFloat("VignetteExtent", vignetteExtent);
        }
    }

    public float getVignetteExtent() {
        return vignetteExtent;
    }

    public void setBlurSize(float blurSize) {
        //checkFloatArgument(blurSize, 0, 1.0f, "BlurSize");
        this.blurSize = FastMath.clamp(blurSize, 0, 1.0f);
        if (material != null) {
            material.setFloat("BlurSize", blurSize);
        }
    }

    public float getBlurSize() {
        return blurSize;
    }

    public void setBlurQuality(float blurQuality) {
        //checkFloatArgument(blurQuality, 0, 50.0f, "BlurQuality");
        this.blurQuality = FastMath.clamp(blurQuality, 0, 50.0f);
        if (material != null) {
            material.setFloat("BlurQuality", blurQuality);
        }
    }

    public float getBlurQuality() {
        return blurQuality;
    }

    public void setGrayPower(float grayPower) {
        //checkFloatArgument(grayPower, 0, 5.0f, "GrayPower");
        this.grayPower = FastMath.clamp(grayPower, 0, 5.0f);
        if (material != null) {
            material.setFloat("GrayPower", grayPower);
        }
    }

    public float getGrayPower() {
        return grayPower;
    }

    public void setGrayMargin(float grayMargin) {
        //checkFloatArgument(grayMargin, 0, 10.0f, "GrayMargin");
        this.grayMargin = FastMath.clamp(grayMargin, 0, 10f);
        if (material != null) {
            material.setFloat("GrayMargin", grayMargin);
        }
    }

    public float getGrayMargin() {
        return grayMargin;
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule oc = ex.getCapsule(this);
        oc.write(vignetteStrength, "vignetteStrength", DEFAULT_VIGNETTING_STRENGTH);
        oc.write(vignetteExtent, "vignetteExtent", DEFAULT_VIGNETTING_EXTENT);
        oc.write(blurQuality, "blurQuality", DEFAULT_BLUR_QUALITY);
        oc.write(blurSize, "blurSize", DEFAULT_BLUR_SIZE);
        oc.write(grayPower, "grayPower", DEFAULT_GRAY_POWER);
        oc.write(grayMargin, "grayMargin", DEFAULT_GRAY_MARGIN);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule ic = im.getCapsule(this);
        vignetteStrength = ic.readFloat("vignetteStrength", DEFAULT_VIGNETTING_STRENGTH);
        vignetteExtent = ic.readFloat("vignetteExtent", DEFAULT_VIGNETTING_EXTENT);
        blurQuality = ic.readFloat("blurQuality", DEFAULT_BLUR_QUALITY);
        blurSize = ic.readFloat("blurSize", DEFAULT_BLUR_SIZE);
        grayPower = ic.readFloat("grayPower", DEFAULT_GRAY_POWER);
        grayMargin = ic.readFloat("grayMargin", DEFAULT_GRAY_MARGIN);
    }

    @Deprecated
    private void checkFloatArgument(float value, float min, float max, String name) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(name + " was " + value + " but should be between " + min + " and " + max);
        }
    }
}
