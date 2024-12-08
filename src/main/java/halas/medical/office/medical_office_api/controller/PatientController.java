package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.patient.Patient;
import halas.medical.office.medical_office_api.patient.PatientDto;
import halas.medical.office.medical_office_api.patient.PatientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PatientDto patientDto) {
        patientRepository.save(new Patient(patientDto));
    }

}
