package stackoverflow.repository;

import stackoverflow.model.User;

import java.util.Optional;

public interface UserRepository {
    void save(User use);
    Optional<User> findByUserId(String userId);

}
