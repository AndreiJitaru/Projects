#ifndef Camera_hpp
#define Camera_hpp

#include <stdio.h>
#include "glm/glm.hpp"
#include "glm/gtx/transform.hpp"

namespace gps {    
    enum MOVE_DIRECTION {MOVE_FORWARD, MOVE_BACKWARD, MOVE_RIGHT, MOVE_LEFT};
    
	const float YAW = -90.0f;
	const float PITCH = 0.0f;
	const float SPEED = 2.5f;
	const float SENSITIVITY = 0.1f;
	const float ZOOM = 45.0f;	

    class Camera {
    public:
        Camera(glm::vec3 cameraPosition, glm::vec3 cameraTarget);
		glm::vec3 getCameraPosition();
		glm::vec3 getCameraTarget();
		glm::mat4 getViewMatrix();
		float getCameraZoom();
		void updateCameraVectors();
		void move(MOVE_DIRECTION direction, float speed);
		void processMouseMovement(float xoffset, float yoffset);
		void processMouseScroll(float yoffset);
		void moveCamera(glm::vec3 newPosition, float deltaTime);

	private:
        glm::vec3 cameraPosition;
        glm::vec3 cameraTarget;
        glm::vec3 cameraDirection;
        glm::vec3 cameraRightDirection;
		glm::vec3 cameraUpDirection;
		float yaw;
		float pitch;
		float movementSpeed;
		float mouseSensitivity;
		float zoom;
	};
    
}

#endif /* Camera_hpp */
