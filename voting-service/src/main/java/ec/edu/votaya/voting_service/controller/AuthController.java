package ec.edu.votaya.voting_service.controller;

import ec.edu.votaya.voting_service.dto.VoterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @GetMapping("/validate-cedula")
    public ResponseEntity<VoterDTO> validateCedula(@RequestParam String cedula) {
        // Simple mock validation: cedula must be 10 digits numeric
        if (cedula == null || cedula.length() != 10 || !cedula.chars().allMatch(Character::isDigit)) {
            VoterDTO error = new VoterDTO();
            error.setMessage("Número de cédula inválido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        // Mock lookup: return sample names derived from cedula for demo purposes
        String firstName = "Ciudadano";
        String lastName = "Ejemplo";

        // For demonstration, vary name based on last digit
        char last = cedula.charAt(cedula.length() - 1);
        if ((last - '0') % 2 == 0) {
            firstName = "Juan";
            lastName = "Pérez";
        } else {
            firstName = "María";
            lastName = "González";
        }

        VoterDTO voter = new VoterDTO(firstName, lastName);
        return ResponseEntity.ok(voter);
    }
}
