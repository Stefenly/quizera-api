//package co.istad.quizera.project.service;
//
//import co.istad.quizera.project.dto.auth.AuthResponse;
//import co.istad.quizera.project.dto.auth.LoginRequest;
//import co.istad.quizera.project.dto.auth.RegisterRequest;
//
//public interface AuthService {
//    AuthResponse register(RegisterRequest request);
//    AuthResponse login(LoginRequest request);
//}

package co.istad.quizera.project.service;

import co.istad.quizera.project.dto.auth.AuthResponse;
import co.istad.quizera.project.dto.auth.LoginRequest;
import co.istad.quizera.project.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}