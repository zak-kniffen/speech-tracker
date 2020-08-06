package com.babyspeak.speechtracker.models.data;

import com.babyspeak.speechtracker.models.mWordsFinal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MwordsFinalRepository extends CrudRepository<mWordsFinal, Integer> {
}
