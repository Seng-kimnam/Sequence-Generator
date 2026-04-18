package _skn.sequencegenerator.repository;

import _skn.sequencegenerator.enumeration.SequenceType;
import _skn.sequencegenerator.model.Entity.Sequence;
import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence , Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Sequence s WHERE s.sequenceType = :type")
    Optional<Sequence> findSequenceBySequenceTypeForUpdate(@Param("type") SequenceType sequenceType);

    // this no query because it follows the prefix of the method's name
    Optional<Sequence> findSequenceBySequenceType(@Param("type") SequenceType sequenceType);

}
