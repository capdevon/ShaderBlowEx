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

public class RadialHaloGlowFilter extends Filter {

    private Material material;
    private float strength = 30;
    private float brightness = 0.5f;

    public RadialHaloGlowFilter() {
        super("RadialHaloGlowFilter");
    }

    public RadialHaloGlowFilter(float strength, float brightness) {
        this();
        this.strength = strength;
        this.brightness = brightness;
    }

    @Override
    protected void initFilter(AssetManager assetManager, RenderManager arg1, ViewPort arg2, int w, int h) {
        material = new Material(assetManager, "ShaderBlowEx/MatDefs/RadialHaloGlow/RadialHaloGlow.j3md");
        material.setFloat("Brightness", brightness);
        material.setFloat("Strength", strength);
    }

    @Override
    protected Material getMaterial() {
        return material;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = FastMath.clamp(strength, 0.0f, 100.0f);
        if (material != null) {
            material.setFloat("Strength", strength);
        }
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = FastMath.clamp(brightness, 0.0f, 1.0f);
        if (material != null) {
            material.setFloat("Brightness", brightness);
        }
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule oc = ex.getCapsule(this);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule ic = im.getCapsule(this);
    }
}
