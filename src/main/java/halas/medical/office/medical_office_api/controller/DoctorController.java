package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.doctor.Doctor;
import halas.medical.office.medical_office_api.doctor.DoctorDto;
import halas.medical.office.medical_office_api.doctor.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody DoctorDto doctorDto) {
        doctorRepository.save(new Doctor(doctorDto));
    }

}
