package stackoverflow.service;

import stackoverflow.model.User;
import stackoverflow.repository.UserRepository;

public class UserService {
    //repository only persist and do entity level thing, other checks add operation sits in Service layer
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findByUserIdOrElseThrow(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(()-> new IllegalArgumentException("User not found"+userId));
    }

    public void updateReputation(String userId, int delta) {
        User user = findByUserIdOrElseThrow(userId);
        user.updateReputation(delta);
        userRepository.save(user);
    }
}
