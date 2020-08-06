package com.babyspeak.speechtracker.models.data;

import com.babyspeak.speechtracker.models.nWordsInitial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NwordsInitialRepository extends CrudRepository<nWordsInitial, Integer> {
}
