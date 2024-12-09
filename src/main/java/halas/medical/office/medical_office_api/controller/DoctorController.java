package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.doctor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorResponseDetailDto> register(@RequestBody @Valid DoctorDto doctorDto, UriComponentsBuilder uriComponentsBuilder) {
        var doctor = doctorRepository.save(new Doctor(doctorDto));
        var uri = uriComponentsBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorResponseDetailDto(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorResponseListDto>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var doctorResponseListDtos = doctorRepository.findAllByActiveTrue(pageable).map(DoctorResponseListDto::new);
        return ResponseEntity.ok(doctorResponseListDtos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorResponseDetailDto> update(@RequestBody @Valid DoctorRequestUpdateDto doctorRequestUpdateDto) {
        var doctor = doctorRepository.getReferenceById(doctorRequestUpdateDto.id());
        doctor.updateData(doctorRequestUpdateDto);
        return ResponseEntity.ok(new DoctorResponseDetailDto(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }
}
