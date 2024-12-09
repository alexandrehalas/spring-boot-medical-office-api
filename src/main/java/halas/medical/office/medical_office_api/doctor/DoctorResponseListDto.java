package halas.medical.office.medical_office_api.doctor;

public record DoctorResponseListDto(Long id, String name, String email, String crm, DoctorSpecialtyEnum doctorSpecialtyEnum) {

    public DoctorResponseListDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getDoctorSpecialtyEnum());
    }

}
