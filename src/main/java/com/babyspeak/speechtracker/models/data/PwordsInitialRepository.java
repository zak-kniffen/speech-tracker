package com.babyspeak.speechtracker.models.data;

import com.babyspeak.speechtracker.models.pWordsInitial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PwordsInitialRepository extends CrudRepository<pWordsInitial, Integer> {
}
