package pl.darsonn.odoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.darsonn.odoapi.model.entity.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
