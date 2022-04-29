package com.test.parkingslot.repository;

import com.test.parkingslot.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {
    List<Spot> findAllByAvailableStatusIsTrue();

    List<Spot> findAllByAvailableStatusIsFalse();
}
