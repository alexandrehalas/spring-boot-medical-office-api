package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.domain.user.AuthenticationDto;
import halas.medical.office.medical_office_api.domain.user.User;
import halas.medical.office.medical_office_api.infra.security.JWTTokenDto;
import halas.medical.office.medical_office_api.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDto.login(), authenticationDto.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var jwtToken = tokenService.generate((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTTokenDto(jwtToken));
    }
}
