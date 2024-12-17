package halas.medical.office.medical_office_api.domain;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
