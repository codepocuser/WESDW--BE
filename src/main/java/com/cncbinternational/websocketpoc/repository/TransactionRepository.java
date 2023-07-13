package com.cncbinternational.websocketpoc.repository;
import com.cncbinternational.websocketpoc.entity.QRCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends CrudRepository<QRCode, Long> {
    Optional<QRCode> findById(UUID id);
}
