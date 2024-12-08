package halas.medical.office.medical_office_api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String streetAddress;
    private String neighborhood;
    private String postalCode;
    private String city;
    private String state;
    private String number;
    private String complement;

    public Address(AddressDto addressDto) {
        this.streetAddress = addressDto.streetAddress();
        this.neighborhood = addressDto.neighborhood();
        this.postalCode = addressDto.postalCode();
        this.city = addressDto.city();
        this.state = addressDto.state();
        this.number = addressDto.number();
        this.complement = addressDto.complement();
    }
}
