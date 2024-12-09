package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.doctor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public Page<DoctorResponseListDto> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return doctorRepository.findAllByActiveTrue(pageable).map(DoctorResponseListDto::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorRequestUpdateDto doctorRequestUpdateDto) {
        var doctor = doctorRepository.getReferenceById(doctorRequestUpdateDto.id());
        doctor.updateData(doctorRequestUpdateDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.delete();
    }
}
