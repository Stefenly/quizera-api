package co.istad.quizera.project.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthResponse {
    private String accessToken;
//    private String tokenType;

    @Builder.Default
    private String tokenType = "Bearer";
}