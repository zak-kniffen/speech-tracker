package com.babyspeak.speechtracker.models.data;

import com.babyspeak.speechtracker.models.fWordsFinal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FwordsFinalRepository extends CrudRepository<fWordsFinal,Integer> {
}
