#version 420 core

uniform sampler2D sampler;

uniform vec4 color = vec4(-1, -1, -1, -1);
uniform vec3 lightColor;

in vec3 positionFrag;
in vec2 textureCoordsFrag;

void main() {
    if(color.x >= 0 && color.y >= 0 && color.z >= 0)
        gl_FragColor = color;
    else
        gl_FragColor = texture2D(sampler, textureCoordsFrag);
}