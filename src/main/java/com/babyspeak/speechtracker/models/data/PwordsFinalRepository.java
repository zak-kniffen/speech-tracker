package com.babyspeak.speechtracker.models.data;

import com.babyspeak.speechtracker.models.pWordsFinal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PwordsFinalRepository extends CrudRepository<pWordsFinal, Integer> {
}
