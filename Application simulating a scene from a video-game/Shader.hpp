//
//  Shader.hpp
//  Lab3
//
//  Created by CGIS on 05/10/2016.
//  Copyright © 2016 CGIS. All rights reserved.
//

#ifndef Shader_hpp
#define Shader_hpp

#include <stdio.h>
#include <iostream>
#include "GLEW/glew.h"
#include <fstream>
#include <sstream>
#include <iostream>
#include <string>
#include <iostream>
#include <vector>

namespace gps {

class Shader
{
public:
    GLuint shaderProgram;
	Shader();
    void loadShader(std::string vertexShaderFileName, std::string fragmentShaderFileName);
    void useShaderProgram();

private:
    std::string readShaderFile(std::string fileName);
    void shaderCompileLog(GLuint shaderId);
    void shaderLinkLog(GLuint shaderProgramId);
};

}

#endif /* Shader_hpp */
