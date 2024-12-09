package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.patient.Patient;
import halas.medical.office.medical_office_api.patient.PatientDto;
import halas.medical.office.medical_office_api.patient.PatientRepository;
import halas.medical.office.medical_office_api.patient.PatientResponseListDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<PatientResponseListDto> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return patientRepository.findAllByActiveTrue(pageable).map(PatientResponseListDto::new);
    }

    @PutMapping
    @Transactional
    public void register(@RequestBody @Valid PatientRequestUpdateDto patientRequestUpdateDto) {
        var patient = patientRepository.getReferenceById(patientRequestUpdateDto.id());
        patient.updateData(patientRequestUpdateDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.delete();
    }
}
