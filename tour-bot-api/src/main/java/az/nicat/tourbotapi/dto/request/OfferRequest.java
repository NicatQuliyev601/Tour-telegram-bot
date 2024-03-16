package az.nicat.tourbotapi.dto.request;

import lombok.Data;

@Data
public class OfferRequest {
    String price;
    String dateRange;
    String additionalInfo;
}
