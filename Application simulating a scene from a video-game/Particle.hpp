#ifndef PARTICLE_GENERATOR_H
#define PARTICLE_GENERATOR_H
#include <vector>

#include "GLEW/glew.h"
#include "glm/glm.hpp"
#include "Shader.hpp"
#include "texture.hpp"


// Represents a single particle and its state
struct Particle {
	glm::vec2 Position, Velocity;
	glm::vec4 Color;
	GLfloat Life;

	Particle() : Position(0.0f), Velocity(0.0f), Color(1.0f), Life(0.0f) { }
};


// ParticleGenerator acts as a container for rendering a large number of 
// particles by repeatedly spawning and updating particles and killing 
// them after a given amount of time.
class ParticleGenerator
{
public:
	// Constructor
	ParticleGenerator(gps::Shader shader, Texture2D texture, GLuint amount);
	// Update all particles
	void Update(GLfloat dt, glm::vec2 objPosition, glm::vec2 objVelocity, GLuint newParticles, float deltaTime);
	// Render all particles
	void Draw();
	std::vector<Particle> particles;
private:
	// State	
	GLuint amount;
	// Render state
	gps::Shader shader;
	Texture2D texture;
	GLuint VAO;
	// Initializes buffer and vertex attributes
	void init();
	// Returns the first Particle index that's currently unused e.g. Life <= 0.0f or 0 if no particle is currently inactive
	GLuint firstUnusedParticle();
	// Respawns particle
	void respawnParticle(Particle &particle, glm::vec2 objPosition, glm::vec2 objVelocity, float deltaTime);
};

#endif