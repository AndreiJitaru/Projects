#include "Camera.hpp"
#include "glm/glm.hpp"

namespace gps {    
    Camera::Camera(glm::vec3 cameraPosition, glm::vec3 cameraTarget) {
        this->cameraPosition = cameraPosition;
        this->cameraTarget = cameraTarget;
		yaw = YAW;
		pitch = PITCH;
		mouseSensitivity = SENSITIVITY;
		zoom = ZOOM;
		movementSpeed = SPEED;
		updateCameraVectors();
    }
    
    glm::mat4 Camera::getViewMatrix() {
		return glm::lookAt(cameraPosition, cameraPosition + cameraDirection, glm::vec3(0.0f, 1.0f, 0.0f));
    }

	void Camera::updateCameraVectors() {
		glm::vec3 direction;
		direction.x = cos(glm::radians(yaw)) * cos(glm::radians(pitch));
		direction.y = sin(glm::radians(pitch));
		direction.z = sin(glm::radians(yaw)) * cos(glm::radians(pitch));
		cameraDirection = glm::normalize(direction);
		cameraRightDirection = glm::normalize(glm::cross(cameraDirection, glm::vec3(0.0f, 1.0f, 0.0f)));
		cameraUpDirection = glm::cross(cameraDirection, cameraRightDirection);
	}
    
	void Camera::processMouseMovement(float xoffset, float yoffset) {
		xoffset *= mouseSensitivity;
		yoffset *= mouseSensitivity;
		yaw += xoffset;
		pitch += yoffset;
		if (pitch > 89.0f)
			pitch = 89.0f;
		if (pitch < -89.0f)
			pitch = -89.0f;
		updateCameraVectors();
	}

	void Camera::processMouseScroll(float yoffset) {
		if (zoom >= 1.0f && zoom <= 45.0f)
			zoom -= yoffset;
		if (zoom <= 1.0f)
			zoom = 1.0f;
		if (zoom >= 45.0f)
			zoom = 45.0f;
	}

	glm::vec3 Camera::getCameraPosition() {
		return cameraPosition;
	}

	glm::vec3 Camera::getCameraTarget() {
		return cameraTarget;
	}

	float Camera::getCameraZoom() {
		return zoom;
	}

	void Camera::moveCamera(glm::vec3 newPosition, float deltaTime) {
		float velocity = movementSpeed * deltaTime;
		cameraPosition = newPosition;
	}

    void Camera::move(MOVE_DIRECTION direction, float deltaTime) {
		float velocity = movementSpeed * deltaTime;
        switch (direction) {
            case MOVE_FORWARD:
                cameraPosition += cameraDirection * velocity;
                break;
            case MOVE_BACKWARD:
                cameraPosition -= cameraDirection * velocity;
                break;
            case MOVE_RIGHT:
                cameraPosition += cameraRightDirection * velocity;
                break;
            case MOVE_LEFT:
                cameraPosition -= cameraRightDirection * velocity;
                break;
        }
		cameraPosition.y = 0.0f;
    }
    
}
