package com.obsidi.yearbook.repository;

import com.obsidi.yearbook.jpa.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface ProfileRepository extends JpaRepository<Profile, UUID>  {
    @EntityGraph(attributePaths = "user") // Fetch associated User eagerly
    Page<Profile> findAll( Pageable pageable);

}
