#version 420 core

uniform sampler2D sampler;

in vec3 positionFrag;
in vec2 textureCoordsFrag;

void main() {
        gl_FragColor = texture2D(sampler, textureCoordsFrag);
}