package az.nicat.tourtelegrambot.repository;

import az.nicat.tourtelegrambot.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
