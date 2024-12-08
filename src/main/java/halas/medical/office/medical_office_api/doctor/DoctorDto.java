package halas.medical.office.medical_office_api.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import halas.medical.office.medical_office_api.address.AddressDto;

public record DoctorDto(String name, String email, String crm,
                        @JsonProperty("specialty") DoctorSpecialtyEnum doctorSpecialtyEnum,
                        @JsonProperty("address") AddressDto addressDto) {
}
