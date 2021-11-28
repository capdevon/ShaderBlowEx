#import "Common/ShaderLib/GLSLCompat.glsllib"
//http://en.wikipedia.org/wiki/Bleach_bypass

uniform sampler2D m_Texture;
varying vec2 texCoord;

uniform float m_Strength;

vec4 val1 = vec4(1.0);
vec4 val2 = vec4(2.0);
vec4 lumiCoef = vec4(0.2124, 0.7153, 0.0722, 0.0);

vec4 bleach(vec4 myInput, vec4 prevVal, vec4 size) {
    float luminance = dot(prevVal, lumiCoef);
    float mixsize = clamp((luminance - 0.45) * 10.0, 0.0, 1.0);
    vec4 point1 = val2 * prevVal * myInput;
    vec4 point2 = val1 - (val2 * (val1 - prevVal) * (val1 - myInput));
    vec4 result = mix(point1, point2, vec4(mixsize));
    return mix(prevVal, result, size);
}

void main() {

    vec4 color = texture2D(m_Texture, texCoord);
    //
    vec4 luma = vec4(vec3(dot(color, lumiCoef)), color.a);
    gl_FragColor = bleach(luma, color, vec4(m_Strength));
}
