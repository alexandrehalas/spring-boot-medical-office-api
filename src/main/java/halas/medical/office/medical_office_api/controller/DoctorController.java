package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.doctor.Doctor;
import halas.medical.office.medical_office_api.doctor.DoctorDto;
import halas.medical.office.medical_office_api.doctor.DoctorRepository;
import halas.medical.office.medical_office_api.doctor.DoctorResponseListDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorDto doctorDto) {
        doctorRepository.save(new Doctor(doctorDto));
    }

    @GetMapping
    public List<DoctorResponseListDto> list() {
        return doctorRepository.findAll().stream().map(DoctorResponseListDto::new).toList();
    }

}
