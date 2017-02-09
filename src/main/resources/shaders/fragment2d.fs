#version 330

in vec2 outTexCoord;
in vec3 mvVertexNormal;
in vec3 mvVertexPos;

out vec4 fragColor;

struct Material
{
    vec3 colour;
    int useColour;
    float reflectance;
};

uniform sampler2D texture_sampler;
uniform Material material;

void main()
{
    vec4 baseColour; 
    if ( material.useColour == 1 )
    {
        baseColour = vec4(material.colour, 1);
    }
    else
    {
        baseColour = texture(texture_sampler, outTexCoord);
    }
    
    fragColor = baseColour;
    if (fragColor.w < 0.2)
        discard;
}