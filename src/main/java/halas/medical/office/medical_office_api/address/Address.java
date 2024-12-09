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

    public void updateData(Address address) {
        if (address.streetAddress != null) {
            this.streetAddress = address.getStreetAddress();
        }
        if (address.neighborhood != null) {
            this.neighborhood = address.getNeighborhood();
        }
        if (address.postalCode != null) {
            this.postalCode = address.getPostalCode();
        }
        if (address.city != null) {
            this.city = address.getCity();
        }
        if (address.state != null) {
            this.state = address.getState();
        }
        if (address.number != null) {
            this.number = address.getNumber();
        }
        if (address.complement != null) {
            this.complement = address.getComplement();
        }
    }

}
