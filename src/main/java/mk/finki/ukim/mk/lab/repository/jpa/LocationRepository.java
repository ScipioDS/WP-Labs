package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findById(long id);
}
