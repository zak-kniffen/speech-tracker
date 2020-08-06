package com.babyspeak.speechtracker.models.data;
import com.babyspeak.speechtracker.models.SnapshotWordProgress;
import com.babyspeak.speechtracker.models.bWordsFinal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnapshotWordProgressRepository extends CrudRepository<SnapshotWordProgress, Integer> {
}




