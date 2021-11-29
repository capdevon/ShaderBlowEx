package org.shaderblowex.filter;

import com.jme3.asset.AssetManager;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.material.Material;
import com.jme3.post.Filter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import java.io.IOException;

public class BetterToneMapFilter extends Filter {

    //TODO: Enum ???
    public static final int TYPE_LINEAR = 0;
    public static final int TYPE_SIMPLE_REINHARD = 1;
    public static final int TYPE_LUMA_BASED_REINHARD = 2;
    public static final int TYPE_WHITE_PRESERVING_REINHARD = 3;
    public static final int TYPE_RONBIN_DAHOUSE = 4;
    public static final int TYPE_ACES_FILM = 5;
    public static final int TYPE_ACES_ABSOLUTE_FILM = 6;
    public static final int TYPE_FILMIC = 7;
    public static final int TYPE_UNCHARTED2 = 8;
    public static final int TYPE_DX11DSK = 9;
    public static final int TYPE_TIMOTHY = 10;
    public static final int TYPE_EXPONENTIAL = 11;
    public static final int TYPE_UNREAL = 12;
    public static final int TYPE_AMD_LOTTES = 13;
    public static final int TYPE_REINHARD2 = 14;
    public static final int TYPE_UCHIMURA = 15;
    public static final int TYPE_FERWERDA = 16;
    public static final int TYPE_HAARNPIETERDUIKER = 17;
    public static final int TYPE_WARD = 18;
    public static final int TYPE_TUMBLIN_RESHMEIER = 19;
    public static final int TYPE_SCHICK = 20;
    public static final int TYPE_REINHARD_EXTENDED = 21;
    public static final int TYPE_REINHARD_DEVLIN = 22;
    public static final int TYPE_MEAN_VALUE = 23;
    public static final int TYPE_MAX_DIVISION = 24;
    public static final int TYPE_LOGARITMIC = 25;
    public static final int TYPE_INSOMNIAC = 26;
    public static final int TYPE_GRAHAM_ALDRIDGE_FILMIC = 27;
    public static final int TYPE_EXPONENTIATION = 28;
    public static final int TYPE_EXPONENTIAL2 = 29;
    public static final int TYPE_DROGO = 30;
    public static final int TYPE_CLAMPING = 31;
    public static final int TYPE_JODIE_ROBO = 32;
    public static final int TYPE_JODIE_REINHARD = 33;
    public static final int TYPE_BARTEROPE = 34;
    public static final int TYPE_GIFFORD = 35;

    private static final int DEFAULT_TONEMAP_TYPE = 0;
    private static final float DEFAULT_GAMMA = 1.0f;
    private static final float DEFAULT_EXPOSURE = 1.0f;

    private int tonemapType = DEFAULT_TONEMAP_TYPE;
    private float gamma = DEFAULT_GAMMA;
    private float exposure = DEFAULT_EXPOSURE;

    public BetterToneMapFilter() {
        super("BetterToneMapFilter");
    }

    /**
     * Creates a tone-mapping filter with the specified type, exposure and
     * gamma.
     *
     * @param type Tonemap type. Use internal variables
     * @param exposure Exposure. Default 1.0
     * @param gamma Gamma. Default 1.0
     */
    public BetterToneMapFilter(int tonemapType, float exposure, float gamma) {
        this();
        // 
        checkIntArgument(tonemapType, 0, 35, "TonemapType");
        checkFloatArgument(exposure, 0, 10f, "Exposure");
        checkFloatArgument(gamma, 0, 10f, "Gamma");
        //
        this.tonemapType = tonemapType;
        this.gamma = gamma;
        this.exposure = exposure;

    }

    @Override
    protected void initFilter(AssetManager manager, RenderManager renderManager, ViewPort vp, int w, int h) {
        material = new Material(manager, "ShaderBlowEx/MatDefs/BetterToneMap/BetterToneMap.j3md");
        material.setInt("ToneMapType", tonemapType);
        material.setFloat("Gamma", gamma);
        material.setFloat("Exposure", exposure);
    }

    @Override
    protected Material getMaterial() {
        return material;
    }

    /**
     * Set the type of the tonemapper. Use predefined types
     *
     * @param The type of the tonemapper.
     */
    public void setType(int tonemapType) {
        checkIntArgument(tonemapType, 0, 35, "TonemapType");
        if (material != null) {
            material.setInt("ToneMapType", tonemapType);
        }
        this.tonemapType = tonemapType;
    }

    /**
     * Get type.
     *
     * @return The type of the tonemapping.
     */
    public float getType() {
        return tonemapType;
    }

    /**
     * Set the gamma of the scen.
     *
     * @param The gamma of the scene.
     */
    public void setGamma(float gamma) {
        checkFloatArgument(gamma, 0, 10f, "Gamma");
        if (material != null) {
            material.setFloat("Gamma", gamma);
        }
        this.gamma = gamma;
    }

    /**
     * Get gamma.
     *
     * @return The gamma of the scene.
     */
    public float getGamma() {
        return gamma;
    }

    /**
     * Set the gamma of the scene.
     *
     * @param The gamma of the scene.
     */
    public void setExposure(float exposure) {
        checkFloatArgument(exposure, 0, 10f, "Exposure");
        if (material != null) {
            material.setFloat("Exposure", exposure);
        }
        this.exposure = exposure;
    }

    /**
     * Get exposure.
     *
     * @return The exposure of the scene.
     */
    public float getExposure() {
        return exposure;
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule oc = ex.getCapsule(this);
        oc.write(gamma, "gamma", DEFAULT_GAMMA);
        oc.write(exposure, "exposure", DEFAULT_EXPOSURE);
        oc.write(tonemapType, "tonemapType", DEFAULT_TONEMAP_TYPE);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule ic = im.getCapsule(this);
        gamma = ic.readFloat("gamma", DEFAULT_GAMMA);
        exposure = ic.readFloat("exposure", DEFAULT_EXPOSURE);
        tonemapType = ic.readInt("tonemapType", DEFAULT_TONEMAP_TYPE);
    }

    @Deprecated
    private void checkFloatArgument(float value, float min, float max, String name) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(name + " was " + value + " but should be between " + min + " and " + max);
        }
    }

    @Deprecated
    private void checkIntArgument(int value, int min, int max, String name) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(name + " was " + value + " but should be between " + min + " and " + max);
        }
    }
}
