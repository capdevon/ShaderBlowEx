# ShaderBlowEx
Extended filters library for JMonkey Game Engine

https://jmonkeyengine.org

https://jmonkeystore.com/471f0c67-7911-48c4-84e8-d1f523555400

 
## 1. BetterToneMap
![BetterToneMapFilter](../master/img/BetterToneMapFilter.jpg)
 
```java
//Init
int currentType = BetterToneMapFilter.TYPE_LINEAR;
float currentExposure = 1.0;
float currentGamma = 1.0;

BetterToneMapFilter bToneMapFilter = new BetterToneMapFilter(currentType, currentExposure, currentGamma);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(bToneMapFilter);
viewPort.addProcessor(fpp);

//Modify
bToneMapFilter.setType(BetterToneMapFilter.TYPE_WHITE_PRESERVING_REINHARD);
bToneMapFilter.setExposure(1.1f);
bToneMapFilter.setGamma(2.2f);
```

#### Info:
https://en.wikipedia.org/wiki/Tone_mapping

#### Credits:
https://github.com/tizian/tonemapper/

https://github.com/GPUOpen-Effects/FidelityFX

https://github.com/cansik/processing-postfx


## 2. BetterColorCorrection
![BetterColorCorrectionFilter](../master/img/BetterColorCorrectionFilter.jpg)

```java
//Init
float contrast = 1.0f;
float brightness = 0.0f;
float hue = 0.0f;
float invert = 0.0f;
float red = 1.0f;
float green = 1.0f;
float blue = 1.0f;
float gamma = 1.0f;
float saturation = 0.0f;

BetterColorCorrectionFilter bColorFilter = new BetterColorCorrectionFilter(contrast, brightness, hue, saturation, invert, red, green, blue, gamma);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(bColorFilter);
viewPort.addProcessor(fpp);

//Modify
bColorFilter.setContrast(contrast);
bColorFilter.setBrightness(brightness);
bColorFilter.setHue(hue);
bColorFilter.setSaturation(saturation);
bColorFilter.setGamma(gamma);
bColorFilter.setInvert(invert);
bColorFilter.setRed(red);
bColorFilter.setGreen(green);
bColorFilter.setBlue(blue);
```
#### Info:
https://en.wikipedia.org/wiki/Color_correction

https://en.wikipedia.org/wiki/Color_grading

https://en.wikipedia.org/wiki/Color_balance

https://learning.linkedin.com/blog/design-tips/color-grading-vs--color-correction--what-s-the-difference-

#### Credits:
https://hub.jmonkeyengine.org/t/i-made-a-post-filter-color-grading/31626


## 3. BetterDepthOfField
![BetterDepthOfFieldFilter](../master/img/BetterDepthOfFieldFilter.jpg)

```java
//Init
float focalDepth = 0.5f;
float focalLength = 75f;
float fStop = 16.0f;
boolean autoFocus = true;
boolean showFocus = false;

BetterDepthOfFieldFilter bDofFilter = new BetterDepthOfFieldFilter(focalDepth, focalLength, fStop, autoFocus, showFocus);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(bDofFilter);
viewPort.addProcessor(fpp);

//Modify
bDofFilter.setFocalDepth(focalDepth);
bDofFilter.setFocalLength(focalLength);
bDofFilter.setfStop(fStop);
bDofFilter.setAutoFocus(autoFocus);
bDofFilter.setShowFocus(showFocus);
```
#### Info: 
https://en.wikipedia.org/wiki/Depth_of_field

#### Credits:
https://hub.jmonkeyengine.org/t/new-depth-of-field-effect/31036
 
http://devlog-martinsh.blogspot.com/2011/12/glsl-depth-of-field-with-bokeh-v24.html


## 4. BetterGroundFogFilter
![BetterGroundFogFilter](../master/img/BetterGroundFogFilter.jpg)

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


## 5. BleachFilter
![BleachFilter](../master/img/BleachFilter.jpg)

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


## 6. BokehDoFFilter
![BokehDoFFilter](../master/img/BokehDoFFilter.jpg)

```java
//Init
float focusPoint = 8.0f;
float radiusScale = 0.5f;
float blurSize = 3.0f;
float focusScale = 5.0f;

BokehDoFFilter bokehDoFFilter = new BokehDoFFilter();
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(bokehDoFFilter);
viewPort.addProcessor(fpp);

//Modify
bokehDoFFilter.setFocusPoint(focusPoint);
bokehDoFFilter.setRadiusScale(radiusScale);
bokehDoFFilter.setBlurSize(blurSize);
bokehDoFFilter.setFocusScale(focusScale);
```

### Info: 
https://en.wikipedia.org/wiki/Depth_of_field

#### Credits:
http://tuxedolabs.blogspot.com/2018/05/bokeh-depth-of-field-in-single-pass.html

https://mynameismjp.wordpress.com/the-museum/samples-tutorials-tools/depth-of-field-sample/

https://github.com/TheRealMJP/BakingLab/blob/master/BakingLab/PostProcessing.hlsl#L160


## 7. RadialHaloGlow
![RadialHaloGlowFilter](../master/img/RadialHaloGlowFilter.jpg)

```java
//Init
float strength = 30.0f;
float brightness = 0.5f;

RadialHaloGlowFilter radialHaloGlow = new RadialHaloGlowFilter(strength, brightness);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(radialHaloGlow);
viewPort.addProcessor(fpp);

//Modify
radialHaloGlow.setStrength(strength);
radialHaloGlow.setBrightness(brightness);
```

#### Credits:
https://www.geeks3d.com/20140204/glsl-volumetric-light-post-processing-filter-for-webcam-video/


## 8. BetterVignette
![BetterVignetteFilter](../master/img/BetterVignetteFilter.jpg)

```java
//Init
float strength = 0.5f;
float extent = 50.0f;
float blurSize = 0.1f;
float blurQuality = 20.0f;
float grayPower = 2.0f;
float grayMargin = 4.0f;

BetterVignetteFilter bVignetteFilter = new BetterVignetteFilter();
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(bVignetteFilter);
viewPort.addProcessor(fpp);

//Modify
bVignetteFilter.setVignetteStrength(strength);
bVignetteFilter.setVignetteExtent(extent);
bVignetteFilter.setBlurSize(blurSize);
bVignetteFilter.setBlurQuality(blurQuality);
bVignetteFilter.setGrayPower(grayPower);
bVignetteFilter.setGrayMargin(grayMargin);
```

#### Info:
https://en.wikipedia.org/wiki/Vignetting


## 9. PixelArt
![PixeArtFilter](../master/img/PixeArtFilter.jpg)
 
```java
//Init
float currentEdgeWidth = 1.0f;
float currentColorSize = 4f;
float currentColorCount = 5.0f;
float currentPixelResolution = 250.0f;
int currentpPaletteType = PixelArtFilter.PALETTE_TYPE_LINEAR;
//
PixelArtFilter pixelArtFilter = new PixelArtFilter();
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


## 10. Posterization
![PosterizationFilter](../master/img/PosterizationFilter.jpg)

```java
//Init
float currentStep = 10.0f;
//
PosterizationFilter posterizationFilter = new PosterizationFilter(currentStep);
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


## 11. SimpleBloom
![SimpleBloomFilter](../master/img/SimpleBloomFilter.jpg)

```java
//Init
float currentStrength = 0.5f;
float currentSize = 3.0f;
int currentSamples = 15;
//
SimpleBloomFilter simpleBloomFilter = new SimpleBloomFilter(currentStrength, currentSize, currentSamples);
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


## 12. WhiteBloom
![WhiteBloomFilter](../master/img/WhiteBloomFilter.jpg)

```java
//Init
float currentStrength = 1.0f;
float currentScale = 256f;
//
whiteBloomFilter = new WhiteBloomFilter(currentStrength);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(whiteBloomFilter);
viewPort.addProcessor(fpp);

//Modify
whiteBloomFilter.setStrength(currentStrength);
whiteBloomFilter.setScale(currentScale);
```
 

## 13. DLAA
![DLAAFilter](../master/img/DLAAFilter.jpg)

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


## 14. NFAA
![NFAAFilter](../master/img/NFAAFilter.jpg)

```java
//Init
NFAAFilter nfaa = new NFAAFilter();
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(nfaa);
viewPort.addProcessor(fpp);
```

#### Info:
https://en.wikipedia.org/wiki/Spatial_anti-aliasing

#### Credits:
https://github.com/manuelbua/libgdx-contribs
 
http://blenderartists.org/forum/showthread.php?209574-Full-Screen-Anti-Aliasing-(NFAA-DLAA-SSAA)
 
http://www.gamedev.net/topic/580517-nfaa---a-post-process-anti-aliasing-filter-results-implementation-details/


## 15. Air
![AirFilter](../master/img/AirFilter.jpg)

```java
//Init
float currentAirDensity = 0.4f;
float currentAirDistance = 10;
float currentAirDesaturation = 1f;
//
AirFilter airFilter = new AirFilter();
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(airFilter);
viewPort.addProcessor(fpp);
//
airFilter.setAirDensity(currentAirDensity);
airFilter.setAirDistance(currentAirDistance);
airFilter.setAirDesaturation(currentAirDesaturation);
```


## 16. TV
![TVFilter](../master/img/TVFilter.jpg)

```java
//Init
boolean enableVHS = true;
boolean enableLine = true;
boolean enableGrain = true;
boolean enableScanline = true;
boolean enableVignette = true;

TVFilter tvFilter = new TVFilter(enableVHS, enableLine, enableGrain, enableScanline, enableVignette);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(tvFilter);
viewPort.addProcessor(fpp);

//Modify
tvFilter.setVhs(enableVHS);
tvFilter.setLine(enableLine);
tvFilter.setGrain(enableGrain);
tvFilter.setScanline(enableScanline);
tvFilter.setVignette(enableVignette);
```


## 17. MipmapBloom
![MipmapBloomFilter](../master/img/MipmapBloomFilter.jpg)

```java
//Init
float exposurePower = 5.0f;
float exposureCutOff = 0.0f;
float bloomFactor = 1.5f;
float bloomPower = 0.5f;
float downSamplingCoef = 2.0f;

MipmapBloomFilter mipmapBloomFilter = new MipmapBloomFilter();
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(mipmapBloomFilter);
viewPort.addProcessor(fpp);

//Modify
mipmapBloomFilter.setExposurePower(exposurePower);
mipmapBloomFilter.setExposureCutOff(exposureCutOff);
mipmapBloomFilter.setBloomIntensity(bloomFactor, bloomPower);
mipmapBloomFilter.setDownSamplingCoef(downSamplingCoef);
```

#### Credits:
https://hub.jmonkeyengine.org/t/mipmapbloomfilter-on-github/36360


## 18. SimpleSSR
![SimpleSSRFilter](../master/img/SimpleSSRFilter.jpg)

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


## 19. OilPaint
![OilPaintFilter](../master/img/OilPaintFilter.jpg)

```java
//Init
int currentStrength = 5;
//
OilPaintFilter oilPaintFilter = new OilPaintFilter();
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(oilPaintFilter);
viewPort.addProcessor(fpp);
//Modify
oilPaintFilter.setStrength(currentStrength);
```

#### Credits:
https://www.reddit.com/r/shaders/comments/5e7026/help_making_an_oil_paint_post_processing_shader/


## 20. LensFlare
![LensFlareFilter](../master/img/LensFlareFilter.jpg)

```java
//Init 
float currentSpacing = 0.125f;
float currentDistance = 0.5f;
float currentThreshold = 0.2f;

LensFlareFilter lensFlareFilter = new LensFlareFilter();
lensFlareFilter.setGhostSpacing(currentSpacing);
lensFlareFilter.setHaloDistance(currentDistance);
lensFlareFilter.setLightMapThreshold(currentThreshold);
FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
fpp.addFilter(lensFlareFilter);
viewPort.addProcessor(fpp);
```

#### Credits:
https://hub.jmonkeyengine.org/t/lens-flare-code-small-update-screens/23796
