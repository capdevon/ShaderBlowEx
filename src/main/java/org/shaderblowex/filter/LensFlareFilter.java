package org.shaderblowex.filter;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.post.Filter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image.Format;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.MagFilter;
import com.jme3.texture.Texture.MinFilter;
import com.jme3.texture.Texture.WrapMode;
import java.util.ArrayList;

public class LensFlareFilter extends Filter {

    private RenderManager rm;
    private ViewPort vp;
    private AssetManager assetManager;
    private int w, h;
    private Pass lightMap;
    private Material matLightMap;
    private Texture tex_LensDirt;
    private String dirtTexture = null;
    private float ghostSpacing = 0.18f;
    private float haloDistance = 0.45f;
    private float threshold = 0.9f;

    /**
     * Creates a new Lens Flare Filter
     */
    public LensFlareFilter() {
        super("LensFlareFilter");
    }

    /**
     * Creates a new Lens Flare Filter
     *
     * @param lensDirt String asset key for the lens dirt texture. Default is
     * null
     */
    public LensFlareFilter(String lensDirt) {
        this();
        this.dirtTexture = lensDirt;
    }

    @Override
    public boolean isRequiresDepthTexture() {
        return false;
    }

    @Override
    public void initFilter(AssetManager manager, RenderManager renderManager, ViewPort vp, int w, int h) {
        this.assetManager = manager;
        this.rm = renderManager;
        this.vp = vp;
        this.w = w;
        this.h = h;

        if (dirtTexture != null) {
            tex_LensDirt = manager.loadTexture(dirtTexture);
            tex_LensDirt.setMinFilter(MinFilter.BilinearNearestMipMap);
            tex_LensDirt.setMagFilter(MagFilter.Bilinear);
            tex_LensDirt.setWrap(WrapMode.Repeat);
        }

        postRenderPasses = new ArrayList<Pass>();
        matLightMap = new Material(assetManager, "ShaderBlowEx/MatDefs/LensFlare/LensFlareLightMap.j3md");
        matLightMap.setFloat("Threshold", threshold);

        lightMap = new Pass() {
            @Override
            public boolean requiresDepthAsTexture() {
                return false;
            }

            @Override
            public boolean requiresSceneAsTexture() {
                return true;
            }
        };

        lightMap.init(rm.getRenderer(), w, h, Format.RGBA8, Format.Depth, 1, matLightMap);
        lightMap.getRenderedTexture().setMinFilter(Texture.MinFilter.BilinearNearestMipMap);
        lightMap.getRenderedTexture().setMagFilter(Texture.MagFilter.Bilinear);
        postRenderPasses.add(lightMap);

        material = new Material(manager, "ShaderBlowEx/MatDefs/LensFlare/LensFlare.j3md");

        if (dirtTexture != null) {
            material.setTexture("LensDirt", tex_LensDirt);
        }
        material.setFloat("Ghost", ghostSpacing);
        material.setFloat("Halo", haloDistance);
        material.setTexture("LightMap", lightMap.getRenderedTexture());
    }

    /**
     * Sets the distance between light ghost images.
     *
     * @param ghostSpacing Values 0.0f to 0.5f. Default is 0.18f
     */
    public void setGhostSpacing(float ghostSpacing) {
        this.ghostSpacing = FastMath.clamp(ghostSpacing, 0.0f, 0.5f);
        if (material != null) {
            material.setFloat("Ghost", ghostSpacing);
        }
    }

    /**
     * Gets the current distance between light ghost images.
     *
     * @return ghostSpacing Default is 0.18f
     */
    public float getGhostSpacing() {
        return this.ghostSpacing;
    }

    /**
     * Sets the distance from center screen to halo. Values 0.0f to 0.5f.
     *
     * @param haloDistance Default is 0.45f
     */
    public void setHaloDistance(float haloDistance) {
        this.haloDistance = FastMath.clamp(haloDistance, 0.0f, 0.5f);
        if (material != null) {
            material.setFloat("Halo", haloDistance);
        }
    }

    /**
     * Gets the current distance from center screen to halo. Values 0.0f to
     * 0.5f.
     *
     * @return haloDistance Default is 0.45f
     */
    public float getHaloDistance() {
        return this.haloDistance;
    }

    /**
     * Sets the rgb threshold value used to create the lens flare light map. 
     * r < threshold || g < threshold || b < threshold is discarded.
     *
     * @param threshold Default is 0.9f
     */
    public void setLightMapThreshold(float threshold) {
        this.threshold = FastMath.clamp(threshold, 0.0f, 0.5f);
        if (matLightMap != null) {
            matLightMap.setFloat("Threshold", threshold);
        }
    }

    /**
     * Gets the current rgb threshold value used to create the lens flare light map.
     *
     * @return threshold Default is 0.9f
     */
    public float getLightMapThreshold() {
        return this.threshold;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public void preFrame(float tpf) {
    }

    @Override
    protected void postFrame(RenderManager renderManager, ViewPort viewPort, FrameBuffer prevFilterBuffer, FrameBuffer sceneBuffer) {
    }

}
