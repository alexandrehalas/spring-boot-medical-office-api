package halas.medical.office.medical_office_api.domain.user;

public record AuthenticationDto(
        String login,
        String password) {
}
