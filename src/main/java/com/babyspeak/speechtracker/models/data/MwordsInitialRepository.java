package com.babyspeak.speechtracker.models.data;

import com.babyspeak.speechtracker.models.mWordsInitial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MwordsInitialRepository extends CrudRepository<mWordsInitial, Integer> {
}
