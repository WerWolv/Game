#version 420 core

uniform sampler2D sampler;

uniform vec3 color = vec3(-1, -1, -1);

in vec3 positionFrag;
in vec2 textureCoordsFrag;

void main() {
    if(color.x >= 0 && color.y >= 0 && color.z >= 0)
        gl_FragColor = vec4(color, 1.0F);
    else
        gl_FragColor = texture2D(sampler, textureCoordsFrag);
}