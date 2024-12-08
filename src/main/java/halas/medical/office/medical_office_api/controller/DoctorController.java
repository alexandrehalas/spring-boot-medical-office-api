package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.doctor.DoctorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctors")
@RequiredArgsConstructor
public class DoctorController {

    @PostMapping
    public void register(@RequestBody DoctorDto doctorDto) {
        System.out.println(doctorDto);
    }

}
