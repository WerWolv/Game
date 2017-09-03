#version 420 core

uniform sampler2D sampler;

uniform vec3 lightColor;

uniform vec4 drawRegion;

in vec3 positionFrag;
in vec2 textureCoordsFrag;

void main() {
    vec2 textureCoordsTemp = textureCoordsFrag;

    textureCoordsTemp.x += drawRegion.x;
    textureCoordsTemp.y += drawRegion.y;

    gl_FragColor = textureCoordsTemp.x > 0 && textureCoordsTemp.y > 0 && textureCoordsTemp.x < drawRegion.z && textureCoordsTemp.y < drawRegion.w ? texture2D(sampler, textureCoordsTemp) : vec4(0.0F, 0.0F, 0.0F, 0.0F);
}