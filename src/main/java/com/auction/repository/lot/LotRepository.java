package com.auction.repository.lot;

import com.auction.entity.lot.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepository extends JpaRepository<Lot, Long> {
}
