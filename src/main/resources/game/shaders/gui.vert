#version 420 core

in layout(location = 0) vec3 position;
in layout(location = 1) vec2 textureCoords;

out vec3 positionFrag;
out vec2 textureCoordsFrag;

uniform mat4 projMatrix;

uniform vec3 lightPosition;

uniform vec2 drawPosition;

void main() {
    positionFrag = position - vec3(drawPosition, 0.0F);
    textureCoordsFrag = textureCoords;
    gl_Position = projMatrix * vec4(positionFrag, 1.0F);

}

