#version 410 core

in vec3 normal;
in vec4 fragPosEye;
in vec4 fragPosLightSpace;
in vec2 fragTexCoords;
in vec4 fragPosEyePointLight;

out vec4 fColor;

//lighting
uniform	mat3 normalMatrix;
uniform mat3 lightDirMatrix;
uniform	vec3 lightColor;
uniform	vec3 lightDir;
uniform sampler2D diffuseTexture;
uniform sampler2D specularTexture;
uniform sampler2D shadowMap;

uniform int activateFog;
uniform int activatePointLight;

vec3 ambient;
float ambientStrength = 0.7f;
vec3 diffuse;
vec3 specular;
float specularStrength = 0.9f;
float shininess = 64.0f;

	// For point light
	uniform vec3 positionP;
	float constant = 1.0f;
	float linear = 0.22f;
	float quadratic = 0.20f; 
	vec3 ambientP;
	vec3 diffuseP;

void computeLightComponents() {		
	vec3 cameraPosEye = vec3(0.0f);//in eye coordinates, the viewer is situated at the origin
	vec3 normalEye = normalize(normalMatrix * normal); //transform normal
	vec3 lightDirN = normalize(lightDirMatrix * lightDir); //compute light direction
	vec3 viewDirN = normalize(cameraPosEye - fragPosEye.xyz); //compute view direction 	
	vec3 halfVector = normalize(lightDirN + viewDirN); //compute half vector
	ambient = ambientStrength * lightColor; //compute ambient light
	diffuse = max(dot(normalEye, lightDirN), 0.0f) * lightColor; //compute diffuse light
	float specCoeff = pow(max(dot(halfVector, normalEye), 0.0f), shininess); //compute specular light
	specular = specularStrength * specCoeff * lightColor;
}

vec3 calcPointLight()
{
	ambientP = ambientStrength * lightColor;
	
	vec3 cameraPosEye = vec3(0.0f);//in eye coordinates, the viewer is situated at the origin
	//compute view direction 
	vec3 viewDir = normalize(cameraPosEye - fragPosEyePointLight.xyz);
	//compute normalized light direction
	vec3 lightDir = normalize(positionP - fragPosEyePointLight.xyz);
	//transform normal
	vec3 normalEye = normalize(normalMatrix * normal);	
	//compute half vector
	vec3 halfVector = normalize(lightDir + viewDir);
	
    // diffuse shading
    float diff = max(dot(normalEye, lightDir), 0.0);
    // specular shading
    float spec = pow(max(dot(normalEye, halfVector), 0.0f), shininess);
    // attenuation
    float distance    = length(positionP - fragPosEyePointLight.xyz);
    float attenuation = 1.0 / (constant + linear * distance + quadratic * (distance * distance));    
    // combine results
    vec3 ambient  = lightColor * ambientP * vec3(texture(diffuseTexture, fragTexCoords));
    vec3 diffuse  = lightColor * diff * vec3(texture(diffuseTexture, fragTexCoords));
    vec3 specular = lightColor * specularStrength * spec * vec3(texture(specularTexture, fragTexCoords));
    ambient  *= attenuation;
    diffuse  *= attenuation;
    specular *= attenuation;
	
	return ambient + diffuse + specular;
}

float computeShadow() {	
    vec3 normalizedCoords = fragPosLightSpace.xyz / fragPosLightSpace.w; // perform perspective divide
    if(normalizedCoords.z > 1.0f)
        return 0.0f;
    normalizedCoords = normalizedCoords * 0.5f + 0.5f; // Transform to [0,1] range
    // Get closest depth value from light's perspective (using [0,1] range fragPosLight as coords)
	if (normalizedCoords.z > 1.0f)
		return 0.0f;
    float closestDepth = texture(shadowMap, normalizedCoords.xy).r;    
    float currentDepth = normalizedCoords.z; // Get depth of current fragment from light's perspective
    float bias = 0.005f;
    float shadow = currentDepth - bias> closestDepth  ? 1.0f : 0.0f; // Check whether current frag pos is in shadow
	
	vec2 texelSize = 1.0 / textureSize(shadowMap, 0);
	for(int x = -1; x <= 1; ++x) {
		for(int y = -1; y <= 1; ++y) {
			float pcfDepth = texture(shadowMap, normalizedCoords.xy + vec2(x, y) * texelSize).r; 
			shadow += currentDepth - bias > pcfDepth ? 1.0 : 0.0;        
		}    
	}
	shadow /= 9.0;
	
    return shadow;	
}

float computeFog() {
	float fogDensity = 0.05f;
	float fragmentDistance = length(fragPosEye);
	float fogFactor = exp(-pow(fragmentDistance * fogDensity, 2));

	return clamp(fogFactor, 0.0f, 1.0f);
}

void main() {
	computeLightComponents();
	//cumputePointLight();
	float shadow = computeShadow();
	
	vec4 colorFromTexture = texture(diffuseTexture, fragTexCoords);
	if(colorFromTexture.a < 0.1)
		discard;
	ambient *= vec3(colorFromTexture); //modulate with diffuse map
	
	colorFromTexture = texture(diffuseTexture, fragTexCoords);
	if(colorFromTexture.a < 0.1)
		discard;
	diffuse *= vec3(colorFromTexture); //modulate with diffuse map
	
	colorFromTexture = texture(specularTexture, fragTexCoords);
	if(colorFromTexture.a < 0.1)
		discard;
	specular *= vec3(colorFromTexture); //modulate woth specular map
	
	/*
	ambient *= vec3(texture(diffuseTexture, fragTexCoords)); //modulate with diffuse map
	diffuse *= vec3(texture(diffuseTexture, fragTexCoords)); //modulate with diffuse map
	specular *= vec3(texture(specularTexture, fragTexCoords)); //modulate woth specular map
	*/
	
	vec3 color = min((ambient + (1.0f - shadow)*diffuse) + (1.0f - shadow)*specular, 1.0f); //modulate with shadow
	
	if(activatePointLight==1){
		color =calcPointLight();
	} else {
		color += calcPointLight();
	}
	
	if(activateFog==1){
		float fogFactor = computeFog();
		vec4 fogColor = vec4(0.5f, 0.5f, 0.5f, 1.0f);
		fColor = fogColor*(1-fogColor) + vec4(color*fogFactor, 0.0f);
	} else {
		fColor = vec4(color, 1.0f);
	}
	
}
