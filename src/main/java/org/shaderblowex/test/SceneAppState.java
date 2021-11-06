package org.shaderblowex.test;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;

public class SceneAppState extends BaseAppState {

    @Override
    protected void initialize(Application app) {

        SimpleApplication sapp = ((SimpleApplication) app);
        Camera cam = app.getCamera();
        ViewPort viewPort = app.getViewPort();
        Node rootNode = sapp.getRootNode();
        Node guiNode = sapp.getGuiNode();

        // Background color
        viewPort.setBackgroundColor(ColorRGBA.Gray);

        // 2D reference image
        Quad quad = new Quad(cam.getWidth() / 3, cam.getHeight() / 3);
        Geometry geo = new Geometry("ToneMapFilterGeo", quad);
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", app.getAssetManager().loadTexture("ShaderBlowEx/Textures/test.png"));
        geo.setMaterial(mat);
        guiNode.attachChild(geo);

        // Scene
        Spatial scene = app.getAssetManager().loadModel("ShaderBlowEx/Models/testScene.j3o");
        Node sceneAsNode = ((Node) ((Node) scene).getChild("Scene"));
        rootNode.attachChild(sceneAsNode);
        // Tex for the ground for comparison
        sceneAsNode.getChild("Plane").setMaterial(mat);

        // Light
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-.5f, -.5f, -.5f).normalizeLocal());
        sceneAsNode.addLight(sun);

        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.1f));
        sceneAsNode.addLight(al);
    }

    @Override
    protected void cleanup(Application app) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void onEnable() {
        // TODO Auto-generated method stub
    }

    @Override
    protected void onDisable() {
        // TODO Auto-generated method stub
    }

}
