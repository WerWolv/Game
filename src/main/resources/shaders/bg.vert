#version 330 core

layout (location = 0) in vec4 position;
layout (location = 2) in vec2 tcoord;

uniform mat4 pr_matrix;

void main() {
    gl_Position = pr_matrix * position;
}