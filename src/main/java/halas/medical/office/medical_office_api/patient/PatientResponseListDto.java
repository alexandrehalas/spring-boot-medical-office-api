package halas.medical.office.medical_office_api.patient;

public record PatientResponseListDto(Long id, String name, String email, String cpf) {

    public PatientResponseListDto(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
