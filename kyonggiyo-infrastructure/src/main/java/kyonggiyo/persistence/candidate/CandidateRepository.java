package kyonggiyo.persistence.candidate;

import kyonggiyo.domain.candidate.Candidate;
import kyonggiyo.domain.candidate.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CandidateRepository {

    Candidate getById(Long id);

    Slice<Candidate> findAllByStatus(Status status, Pageable pageable);

    Candidate save(Candidate candidate);

    void deleteById(Long id);

}
