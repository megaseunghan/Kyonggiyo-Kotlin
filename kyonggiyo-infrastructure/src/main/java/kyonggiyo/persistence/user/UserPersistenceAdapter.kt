package kyonggiyo.persistence.user

import kyonggiyo.application.port.out.user.ExistUserPort
import kyonggiyo.application.port.out.user.LoadUserPort
import kyonggiyo.application.port.out.user.SaveUserPort
import kyonggiyo.domain.user.User
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val repository: UserRepository,
) : LoadUserPort, ExistUserPort, SaveUserPort {

    override fun existByNickname(nickname: String): Boolean = repository.existByNickname(nickname)

    override fun getById(id: Long): User = repository.getById(id)

    override fun save(user: User): User = repository.save(user)

}