package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.domain.address.AddressDto;
import halas.medical.office.medical_office_api.domain.doctor.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DoctorDto> jacksonTesterDoctorDto;

    @Autowired
    private JacksonTester<DoctorResponseDetailDto> jsonTesterDoctorResponseDetailDto;

    @MockitoBean
    private DoctorRepository doctorRepository;

    @Test
    @DisplayName("Should return code 400 when the information is invalid")
    @WithMockUser
    void registerScenario1() throws Exception {
        var response = mockMvc.perform(post("/doctors")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return code 200 when the information is valid")
    @WithMockUser
    void registerScenario2() throws Exception {

        DoctorDto doctorDto = new DoctorDto("Doctor Name", "doctorname@doctor.com", "41999999999", "123456", DoctorSpecialtyEnum.DERMATOLOGY,
                new AddressDto("Street 123", "Happy neighborhood", "99999999", "Happy City", "HS", "012", "Happy complement"));
        var doctor = new Doctor(doctorDto);
        var doctorResponseDetailDto = new DoctorResponseDetailDto(doctor);
        var resultExpected = jsonTesterDoctorResponseDetailDto.write(doctorResponseDetailDto).getJson();

        when(doctorRepository.save(doctor)).thenReturn(doctor);

        var response = mockMvc.perform(
                post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterDoctorDto.write(
                                doctorDto
                        ).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(resultExpected);
    }
}