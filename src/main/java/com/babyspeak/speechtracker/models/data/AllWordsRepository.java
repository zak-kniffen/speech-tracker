package com.babyspeak.speechtracker.models.data;
import com.babyspeak.speechtracker.models.AllWords;
import com.babyspeak.speechtracker.models.bWordsFinal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AllWordsRepository extends CrudRepository<AllWords, Integer>{
}