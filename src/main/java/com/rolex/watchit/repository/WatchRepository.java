package com.rolex.watchit.repository;


import com.rolex.watchit.model.Watch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchRepository extends JpaRepository<Watch,Long> {


    @Query(value = "SELECT * FROM watch " +
            "WHERE user_id = :userId",
        nativeQuery = true)
    Page<Watch> findAllByUserId(Long userId, Pageable pageable);
}
