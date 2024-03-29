package az.nicat.tourtelegrambot.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OfferDto {
    private long offerId;
    private String price;
    private String dateRange;
    private String additionalInfo;
    private UUID sessionId;
    private String companyName;
}
