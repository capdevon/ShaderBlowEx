# ShaderBlowEx
Extended filters library for JMonkey Game Engine

https://jmonkeyengine.org

https://jmonkeystore.com/471f0c67-7911-48c4-84e8-d1f523555400

 
# 1. BetterToneMap

## Usage: 
```java
//Init
int currentType=BetterToneMapFilter.BetterToneMapFilter.TYPE_LINEAR;
float currentExposure=1.0;
float currentGamma=1.0;
//
BetterToneMapFilter betterToneMapFilter=new BetterToneMapFilter(currentType,currentExposure,currentGamma);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(betterToneMapFilter);
viewPort.addProcessor(fpp);
//Modify
betterToneMapFilter.setType(BetterToneMapFilter.TYPE_WHITE_PRESERVING_REINHARD);
betterToneMapFilter.setExposure(1.1f);
betterToneMapFilter.setGamma(2.2f);
```

#### Info:

https://en.wikipedia.org/wiki/Tone_mapping

#### Credits:

https://github.com/tizian/tonemapper/

https://github.com/GPUOpen-Effects/FidelityFX

https://github.com/cansik/processing-postfx

#### Test:

![BetterToneMapFilter](../master/img/BetterToneMapFilter.jpg)


# 2. BetterColorCorrection

## Usage: 
```java
//Init
float currentContrast=1.0f;
float currentBrightness=0.0f;
float currentHue=0.0f;
float currentInvert=0.0f;
float currentRed=1.0f;
float currentGreen=1.0f;
float currentBlue=1.0f;
float currentGamma=1.0f;
float currentSaturation=0.0f;
//
BetterColorCorrectionFilter betterColorCorrectionFilter=new BetterColorCorrectionFilter(currentContrast, currentBrightness,currentHue, currentSaturation,currentInvert,currentRed, currentGreen, currentBlue, currentGamma); 
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(betterColorCorrectionFilter);
viewPort.addProcessor(fpp);
//Modify
betterColorCorrectionFilter.setContrast(currentContrast);
betterColorCorrectionFilter.setBrightness(currentBrightness);
betterColorCorrectionFilter.setHue(currentHue);
betterColorCorrectionFilter.setSaturation(currentSaturation);
betterColorCorrectionFilter.setGamma(currentGamma);
betterColorCorrectionFilter.setInvert(currentInvert);
betterColorCorrectionFilter.setRed(currentRed);
betterColorCorrectionFilter.setGreen(currentGreen);
betterColorCorrectionFilter.setBlue(currentBlue);
```
#### Info:

https://en.wikipedia.org/wiki/Color_correction

https://en.wikipedia.org/wiki/Color_grading

https://en.wikipedia.org/wiki/Color_balance

https://learning.linkedin.com/blog/design-tips/color-grading-vs--color-correction--what-s-the-difference-

#### Credits:

https://hub.jmonkeyengine.org/t/i-made-a-post-filter-color-grading/31626

#### Test:

![BetterColorCorrectionFilter](../master/img/BetterColorCorrectionFilter.jpg)


# 3. BetterDepthOfField

## Usage: 
```java
//Init
float currentFocalDepth=0.5f;
float currentFocalLength=75f;
float currentFStop=16.0f;
boolean currentAutoFocus=true;
boolean currentShowFocus=false;
//
betterDepthOfFieldFilter=new BetterDepthOfFieldFilter( );
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(betterDepthOfFieldFilter);
viewPort.addProcessor(fpp);
//
betterDepthOfFieldFilter.setFocalDepth(currentFocalDepth);
betterDepthOfFieldFilter.setFocalLength(currentFocalLength);
betterDepthOfFieldFilter.setfStop(currentFStop);
betterDepthOfFieldFilter.setAutoFocus(currentAutoFocus);
betterDepthOfFieldFilter.setShowFocus(currentShowFocus);
```
#### Info: 

https://en.wikipedia.org/wiki/Depth_of_field

#### Credits:

https://hub.jmonkeyengine.org/t/new-depth-of-field-effect/31036
 
http://devlog-martinsh.blogspot.com/2011/12/glsl-depth-of-field-with-bokeh-v24.html

#### Test:

![BetterDepthOfFieldFilter](../master/img/BetterDepthOfFieldFilter.jpg)

# 4. BetterGroundFogFilter

## Usage: 
```java
//Init
float currentSunShininess = 8.0f;
float currentFogDensity = 0.08f;
float currentGroundLevel = -10f;
float currentFogBoundaryX=3;
float currentFogBoundaryY=100;
float currentFogBoundaryZ=50;
float currentFogBoundaryW=0;
Vector4f currentFogBoundary = new Vector4f(currentFogBoundaryX, currentFogBoundaryY, currentFogBoundaryZ, currentFogBoundaryW);
//
BetterGroundFogState betterGroundFogState = new BetterGroundFogState();
betterGroundFogState.setSun( sun); //put reference to sunlight 
betterGroundFogState.getFilter().setGroundLevel(currentGroundLevel); 
betterGroundFogState.getFilter().setFogDensity(currentFogDensity);
betterGroundFogState.getFilter().setSunShininess(currentSunShininess);
betterGroundFogState.getFilter().setFogBoundary(currentFogBoundary); 
betterGroundFogState.setIsNight(true);              
betterGroundFogState.setSunColorNight(new ColorRGBA(0.6f,0.6f, 0.6f, 1f));
betterGroundFogState.setFogColorNight(new ColorRGBA(0.5f, 0.5f, 0.5f, 1f));
stateManager.attach(betterGroundFogState);
//
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(betterGroundFogState.getFilter());
viewPort.addProcessor(fpp);
//Modify
betterGroundFogState.getFilter().setGroundLevel(currentGroundLevel);
betterGroundFogState.getFilter().setFogDensity(currentFogDensity);
betterGroundFogState.getFilter().setSunShininess(currentSunShininess);
betterGroundFogState.getFilter().getFogBoundary().setX(currentFogBoundaryX);
betterGroundFogState.getFilter().getFogBoundary().setY(currentFogBoundaryY);
betterGroundFogState.getFilter().getFogBoundary().setZ(currentFogBoundaryZ);
betterGroundFogState.getFilter().getFogBoundary().setW(currentFogBoundaryW);
```

#### Credits:

https://hub.jmonkeyengine.org/t/bettergroundfogfilter/41452

#### Test:

![BetterGroundFogFilter](../master/img/BetterGroundFogFilter.jpg)


# 5. BleachFilter

## Usage: 
```java
//Init
float currentStrength=1.0f;
//
BleachMapFilter bleachMapFilter=new BleachMapFilter(currentStrength);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(bleachMapFilter);
viewPort.addProcessor(fpp);
//Modify
bleachMapFilter.setStrength(currentStrength);
```

#### Info:

http://en.wikipedia.org/wiki/Bleach_bypass

#### Test:

![BleachFilter](../master/img/BleachFilter.jpg)



# 6. BokehDoFFilter

## Usage: 
```java
//Init
float currentFocusPoint=8.0f;
float currentRadiusScale=0.5f;
float currentBlurSize=3.0f;
float currentFocusScale=5.0f;
//
BokehDoFFilter  bokehDoFFilter=new BokehDoFFilter( );
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(bokehDoFFilter);
viewPort.addProcessor(fpp);
//Modify
bokehDoFFilter.setFocusPoint(currentFocusPoint);
bokehDoFFilter.setRadiusScale(currentRadiusScale);
bokehDoFFilter.setBlurSize(currentBlurSize);
bokehDoFFilter.setFocusScale(currentFocusScale); 
```

### Info: 

https://en.wikipedia.org/wiki/Depth_of_field

#### Credits:

http://tuxedolabs.blogspot.com/2018/05/bokeh-depth-of-field-in-single-pass.html

https://mynameismjp.wordpress.com/the-museum/samples-tutorials-tools/depth-of-field-sample/

https://github.com/TheRealMJP/BakingLab/blob/master/BakingLab/PostProcessing.hlsl#L160

#### Test:

![BokehDoFFilter](../master/img/BokehDoFFilter.jpg)


# 7. RadialHaloGlow

## Usage: 
```java
//Init
float currentStrength=30.0f;
float currentBrightness=0.5f;
//
RadialHaloGlowFilter radialHaloGlow=new RadialHaloGlowFilter( );
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(radialHaloGlow);
viewPort.addProcessor(fpp);   
//Modify
radialHaloGlow.setStrength(currentStrength);
radialHaloGlow.setBrightness(currentBrightness); 
```

#### Credits:

https://www.geeks3d.com/20140204/glsl-volumetric-light-post-processing-filter-for-webcam-video/

#### Test:

![RadialHaloGlowFilter](../master/img/RadialHaloGlowFilter.jpg)


# 8. BetterVignette

## Usage: 
```java
//Init
float currentStrength=0.5f;
float currentExtent=50.0f;
float currentBlurSize=0.1f;
float currentBlurQuality=20.0f;
float grayPower=2.0f;
float grayMargin=4.0f;
//
BetterVignetteFilter betterVignetteFilter=new BetterVignetteFilter( );
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(betterVignetteFilter);
viewPort.addProcessor(fpp);
//Modify
betterVignetteFilter.setVignetteStrength(currentStrength);
betterVignetteFilter.setVignetteExtent(currentExtent);
betterVignetteFilter.setBlurSize(currentBlurSize);
betterVignetteFilter.setBlurQuality(currentBlurQuality);
betterVignetteFilter.setGrayPower(grayPower);
betterVignetteFilter.setGrayMargin(grayMargin);
        
```
#### Info:

https://en.wikipedia.org/wiki/Vignetting


#### Test:

![BetterVignetteFilter](../master/img/BetterVignetteFilter.jpg)



# 9. PixelArt

## Usage: 
```java
//Init
float currentEdgeWidth=1.0f;
float currentColorSize=4f;
float currentColorCount=5.0f;
float currentPixelResolution=250.0f;
int currentpPaletteType=PixelArtFilter.PALETTE_TYPE_LINEAR;
//
PixelArtFilter  pixelArtFilter=new PixelArtFilter( );
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(pixelArtFilter);
viewPort.addProcessor(fpp);
//Modify
pixelArtFilter.setPaletteType(currentpPaletteType);
pixelArtFilter.setEdgeWidth(currentEdgeWidth);
pixelArtFilter.setColorSize(currentColorSize);
pixelArtFilter.setColorCount(currentColorCount);
pixelArtFilter.setPixelResolution(currentPixelResolution);
 
        
```
#### Info:

https://en.wikipedia.org/wiki/Pixel_art

#### Credits:

https://github.com/cansik/processing-postfx

https://www.shadertoy.com/view/XsjSzR

http://unusedino.de/ec64/technical/misc/vic656x/colors/

#### Test:

![PixeArtFilter](../master/img/PixeArtFilter.jpg)




# 10. Posterization

## Usage: 
```java
//Init
float currentStep=10.0f;
//
PosterizationFilter posterizationFilter=new PosterizationFilter(currentStep);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(posterizationFilter);
viewPort.addProcessor(fpp);
//Modify
posterizationFilter.setStep(currentStep);
 
        
```
#### Info:

https://en.wikipedia.org/wiki/Posterization

#### Credits:

https://rosenzweig.io/blog/monotone-portraits-with-glsl.html

https://www.geeks3d.com/20091027/shader-library-posterization-post-processing-effect-glsl/

#### Test:

![PosterizationFilter](../master/img/PosterizationFilter.jpg)




# 11. SimpleBloom

## Usage: 
```java
//Init
float currentStrength=0.5f;
float currentSize=3.0f;
int currentSamples=15;
//
SimpleBloomFilter simpleBloomFilter=new SimpleBloomFilter( currentStrength,currentSize,currentSamples);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(simpleBloomFilter);
viewPort.addProcessor(fpp);
//Modify
simpleBloomFilter.setStrength(currentStrength);
simpleBloomFilter.setSize(currentSize);
simpleBloomFilter.setSamples(currentSamples); 
```

#### Credits:

http://rastergrid.com/blog/2010/09/efficient-gaussian-blur-with-linear-sampling/

https://github.com/kiwipxl/GLSL-shaders

#### Test:

![SimpleBloomFilter](../master/img/SimpleBloomFilter.jpg)




# 12. WhiteBloom

## Usage: 
```java
//Init
float currentStrength=1.0f;
float currentScale=256f;
//
whiteBloomFilter=new WhiteBloomFilter(currentStrength);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(whiteBloomFilter);
viewPort.addProcessor(fpp);
//Modify
whiteBloomFilter.setStrength(currentStrength);
whiteBloomFilter.setScale(currentScale);
```
 

#### Test:

![WhiteBloomFilter](../master/img/WhiteBloomFilter.jpg)
 

# 13. DLAA

## Usage: 
```java
//Init
DLAAFilter dLAAFilter=new DLAAFilter();
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(dLAAFilter);
viewPort.addProcessor(fpp);
```

#### Info:

https://en.wikipedia.org/wiki/Spatial_anti-aliasing

#### Credits:

 https://github.com/microsoft/Imagine_fudge-roll
 
 https://github.uconn.edu/eec09006/breakout
 
#### Test:

![DLAAFilter](../master/img/DLAAFilter.jpg)

# 14. NFAA

## Usage: 
```java
//Init
NFAAFilter nFAAFilter=new NFAAFilter();
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(nFAAFilter);
viewPort.addProcessor(fpp);
```

#### Info:

https://en.wikipedia.org/wiki/Spatial_anti-aliasing

#### Credits:

 https://github.com/manuelbua/libgdx-contribs
 
 http://blenderartists.org/forum/showthread.php?209574-Full-Screen-Anti-Aliasing-(NFAA-DLAA-SSAA)
 
 http://www.gamedev.net/topic/580517-nfaa---a-post-process-anti-aliasing-filter-results-implementation-details/
 
 
#### Test:

![NFAAFilter](../master/img/NFAAFilter.jpg)


# 15. Air

## Usage: 
```java
//Init
float currentAirDensity = 0.4f;
float currentAirDistance = 10;
float currentAirDesaturation = 1f;
//
AirFilter airFilter=new AirFilter( );
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(airFilter);
viewPort.addProcessor(fpp);
//
airFilter.setAirDensity(currentAirDensity);
airFilter.setAirDistance(currentAirDistance);
airFilter.setAirDesaturation(currentAirDesaturation);
```
  
#### Test:

![AirFilter](../master/img/AirFilter.jpg)


# 16. TV

## Usage: 
```java
//Init
boolean currentVhs=true;
boolean currentLine=true;
boolean currentGrain=true;
boolean currentScanline=true;
boolean currentVignette=true;    
//
TVFilter tVFilter=new TVFilter(currentVhs, currentLine,currentGrain, currentScanline,currentVignette ); 
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(tVFilter);
viewPort.addProcessor(fpp);
//
tVFilter.setVhs(currentVhs);
tVFilter.setLine(currentLine);
tVFilter.setGrain(currentGrain);
```
  
#### Test:

![TVFilter](../master/img/TVFilter.jpg)



# 17. MipmapBloom

## Usage: 
```java
//Init
float currentExposurePower=5.0f;
float currentExposureCutOff=0.0f;
float currentBloomFactor=1.5f;
float currentBloomPower=0.5f;
float currentDownSamplingCoef=2.0f; 
//
MipmapBloomFilter mipmapBloomFilter=new MipmapBloomFilter(  );
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(mipmapBloomFilter);
viewPort.addProcessor(fpp);
//Modify
mipmapBloomFilter.setExposurePower(currentExposurePower);
mipmapBloomFilter.setExposureCutOff(currentExposureCutOff);
mipmapBloomFilter.setBloomIntensity(currentBloomFactor,currentBloomPower );
mipmapBloomFilter.setDownSamplingCoef(currentDownSamplingCoef);
```
#### Credits:

https://hub.jmonkeyengine.org/t/mipmapbloomfilter-on-github/36360
  
#### Test:

![MipmapBloomFilter](../master/img/MipmapBloomFilter.jpg)



# 18. SimpleSSR

## Usage: 
```java
//Init
float currentDownSample=2f;
float currentStepLength=0.5f;
int currentRayStepLength=32;
float currentSigma= 5f;
float currentReflectionFactor=0.5f;
//
SimpleSSRFilter simpleSSRFilter=new SimpleSSRFilter();
simpleSSRFilter.setDownSampleFactor(currentDownSample);
simpleSSRFilter.setApproximateNormals(false);
simpleSSRFilter.setFastBlur(true);
simpleSSRFilter.setStepLength(currentStepLength);
simpleSSRFilter.setRaySteps(currentRayStepLength);
simpleSSRFilter.setSigma(currentSigma);
simpleSSRFilter.setSampleNearby(false);
simpleSSRFilter.setReflectionFactor(currentReflectionFactor);

FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(simpleSSRFilter);
viewPort.addProcessor(fpp);
//Modify
simpleSSRFilter.setStepLength(currentStepLength);

```

#### Info

https://jove.fandom.com/wiki/Screen_Space_Reflection_(SSR)

#### Credits:

https://github.com/riccardobl/SimpleSSRShader

https://hub.jmonkeyengine.org/t/shader-screen-space-reflections-for-jme/41366/3

https://github.com/jMonkeyEngine/jmonkeyengine/pull/1144
  
#### Test:

![SimpleSSRFilter](../master/img/SimpleSSRFilter.jpg)



# 19. OilPaint

## Usage: 
```java
//Init
int currentStrength=5;
//
OilPaintFilter oilPaintFilter=new OilPaintFilter();
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(oilPaintFilter);
viewPort.addProcessor(fpp);
//Modify
oilPaintFilter.setStrength(currentStrength);
```
#### Credits:

https://www.reddit.com/r/shaders/comments/5e7026/help_making_an_oil_paint_post_processing_shader/
  
#### Test:

![OilPaintFilter](../master/img/OilPaintFilter.jpg)


# 20. LensFlare

## Usage: 
```java
//Init 
float currentSpacing=0.125f;
float currentDistance=0.5f;
float currentThreshold=0.2f;
//
LensFlareFilter lensFlareFilter = new LensFlareFilter( ); 
lensFlareFilter.setGhostSpacing(currentSpacing);
lensFlareFilter.setHaloDistance(currentDistance);
lensFlareFilter.setLightMapThreshold(currentThreshold);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(lensFlareFilter);
viewPort.addProcessor(fpp);
//Modify
lensFlareFilter.setGhostSpacing(currentSpacing);
lensFlareFilter.setHaloDistance(currentDistance);
lensFlareFilter.setLightMapThreshold(currentThreshold);
```
#### Credits:

https://hub.jmonkeyengine.org/t/lens-flare-code-small-update-screens/23796
  
#### Test:

![LensFlareFilter](../master/img/LensFlareFilter.jpg)


