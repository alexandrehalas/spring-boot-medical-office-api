package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.patient.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientResponseDetailDto> register(@RequestBody @Valid PatientDto patientDto, UriComponentsBuilder uriComponentsBuilder) {
        var patient = patientRepository.save(new Patient(patientDto));
        var uri = uriComponentsBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientResponseDetailDto(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientResponseListDto>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var patientResponseListDtos = patientRepository.findAllByActiveTrue(pageable).map(PatientResponseListDto::new);
        return ResponseEntity.ok(patientResponseListDtos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientResponseDetailDto> register(@RequestBody @Valid PatientRequestUpdateDto patientRequestUpdateDto) {
        var patient = patientRepository.getReferenceById(patientRequestUpdateDto.id());
        patient.updateData(patientRequestUpdateDto);

        return ResponseEntity.ok(new PatientResponseDetailDto(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDetailDto> detail(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);

        return ResponseEntity.ok(new PatientResponseDetailDto(patient));
    }
}
