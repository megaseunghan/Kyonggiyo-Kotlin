package kyonggiyo.persistence.candidate;

import kyonggiyo.application.port.out.candidate.DeleteCandidatePort;
import kyonggiyo.application.port.out.candidate.LoadCandidatePort;
import kyonggiyo.application.port.out.candidate.SaveCandidatePort;
import kyonggiyo.domain.candidate.Candidate;
import kyonggiyo.domain.candidate.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CandidatePersistenceAdapter implements LoadCandidatePort, SaveCandidatePort, DeleteCandidatePort {

    private final CandidateRepository repository;

    @Override
    public Candidate getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Slice<Candidate> findAllByStatus(Status status, Pageable pageable) {
        return repository.findAllByStatus(status, pageable);
    }

    @Override
    public Candidate save(Candidate candidate) {
        return repository.save(candidate);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
