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

public class BleachFilter extends Filter {

    private static final float DEFAULT_STRENGTH = 0.1f;

    private float strength = DEFAULT_STRENGTH;

    public BleachFilter() {
        super("BleachMapFilter");
    }

    /**
     * Creates a bleach filter with the specified strength. Bleach - washed out,
     * desaturated look from Saving Private Ryan or Minority Report
     *
     * @param strength Strength. Default 0.1. Max 5.0. Min 0.0
     */
    public BleachFilter(float strength) {
        this();
        this.strength = FastMath.clamp(strength, 0f, 5f);
    }

    @Override
    protected void initFilter(AssetManager manager, RenderManager renderManager, ViewPort vp, int w, int h) {
        material = new Material(manager, "ShaderBlowEx/MatDefs/Bleach/Bleach.j3md");
        material.setFloat("Strength", strength);
    }

    @Override
    protected Material getMaterial() {
        return material;
    }

    /**
     * Set the strength of the effect.
     *
     * @param The strength of the effect.
     */
    public void setStrength(float strength) {
        this.strength = FastMath.clamp(strength, 0f, 5f);
        if (material != null) {
            material.setFloat("Strength", strength);
        }
    }

    /**
     * Get strength.
     *
     * @return The strength of the effect.
     */
    public float getStrength() {
        return strength;
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule oc = ex.getCapsule(this);
        oc.write(strength, "strength", DEFAULT_STRENGTH);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule ic = im.getCapsule(this);
        strength = ic.readFloat("strength", DEFAULT_STRENGTH);
    }

}
