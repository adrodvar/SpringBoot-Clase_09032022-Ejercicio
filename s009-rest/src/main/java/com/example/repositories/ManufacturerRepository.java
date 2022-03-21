package com.example.repositories;

import com.example.entities.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {


    @Transactional
    void deleteByDirectionCountry(String country);

    @Transactional
    @Query("Select m from Manufacturer m where m.year = :year")
    List<Manufacturer> findByYear(Integer year);


    @Transactional
    @Modifying
    @Query("delete from Manufacturer m where m.direction.country = :country")
    void deleteByDirectionCountryQuery(String country);


}