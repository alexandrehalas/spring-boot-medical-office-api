package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.domain.appointment.AppointmentDto;
import halas.medical.office.medical_office_api.domain.appointment.AppointmentResponseDetailDto;
import halas.medical.office.medical_office_api.domain.appointment.AppointmentService;
import halas.medical.office.medical_office_api.domain.doctor.DoctorSpecialtyEnum;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AppointmentDto> jacksonTesterAppointmentDto;

    @Autowired
    private JacksonTester<AppointmentResponseDetailDto> jacksonTesterAppointmentResponseDetailDto;

    @MockitoBean
    private AppointmentService appointmentService;

    @Test
    @DisplayName("Should return code 400 when the information is invalid")
    @WithMockUser
    void scheduleScenario1() throws Exception {
        var response = mockMvc.perform(post("/appointments")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return code 200 when the information is valid")
    @WithMockUser
    void scheduleScenario2() throws Exception {

        var date = LocalDateTime.now().plusHours(1);
        var appointmentDto = new AppointmentDto(1L, 1L, date, DoctorSpecialtyEnum.CARDIOLOGY);
        var appointmentResponseDetailDto = new AppointmentResponseDetailDto(null, 1L, 1L, date);

        when(appointmentService.schedule(appointmentDto)).thenReturn(appointmentResponseDetailDto);

        var response = mockMvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterAppointmentDto.write(
                                appointmentDto
                        ).getJson())
        ).andReturn().getResponse();

        var resultExpected = jacksonTesterAppointmentResponseDetailDto.write(appointmentResponseDetailDto).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(resultExpected);
    }

}