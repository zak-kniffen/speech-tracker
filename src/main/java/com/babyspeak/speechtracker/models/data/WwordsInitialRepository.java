package com.babyspeak.speechtracker.models.data;

import com.babyspeak.speechtracker.models.wWordsInitial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WwordsInitialRepository extends CrudRepository<wWordsInitial, Integer> {
}
