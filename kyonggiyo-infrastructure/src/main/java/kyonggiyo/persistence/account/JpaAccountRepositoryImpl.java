package kyonggiyo.persistence.account;

import kyonggiyo.domain.auth.Account;
import kyonggiyo.domain.auth.Platform;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaAccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository jpaRepository;

    @Override
    public Optional<Account> findById(Long accountId) {
        return jpaRepository.findById(accountId);
    }

    @Override
    public Optional<Account> findByPlatformAndPlatformId(Platform platform, String platformId) {
        return jpaRepository.findByPlatformAndPlatformId(platform, platformId);
    }

    @Override
    public Account save(Account account) {
        return jpaRepository.save(account);
    }

}
