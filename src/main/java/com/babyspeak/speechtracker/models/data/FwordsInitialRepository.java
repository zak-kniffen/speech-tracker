package com.babyspeak.speechtracker.models.data;

import com.babyspeak.speechtracker.models.fWordsInitial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FwordsInitialRepository extends CrudRepository<fWordsInitial, Integer> {
}
