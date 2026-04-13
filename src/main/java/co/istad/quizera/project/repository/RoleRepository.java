package co.istad.quizera.project.repository;

import co.istad.quizera.project.entity.Role;
import co.istad.quizera.project.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);

}