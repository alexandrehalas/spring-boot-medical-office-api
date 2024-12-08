package halas.medical.office.medical_office_api.address;

public record AddressDto(String streetAddress, String neighborhood, String postalCode, String city, String state,
                         String number, String complement) {
}
