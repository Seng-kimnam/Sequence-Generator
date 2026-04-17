package _skn.sequencegenerator.repository;

import _skn.sequencegenerator.model.Entity.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence , Long> {
}
