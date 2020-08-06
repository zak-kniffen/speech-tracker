package com.babyspeak.speechtracker.models.data;

import com.babyspeak.speechtracker.models.nWordsFinal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NwordsFinalRepository extends CrudRepository<nWordsFinal, Integer> {
}
