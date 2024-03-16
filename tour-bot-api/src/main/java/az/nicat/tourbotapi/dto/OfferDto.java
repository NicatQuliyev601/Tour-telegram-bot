package az.nicat.tourbotapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OfferDto {
    private long offerId;
    private UUID sessionId;
    private String price;
    private String dateRange;
    private String additionalInfo;
    private String companyName;
}
