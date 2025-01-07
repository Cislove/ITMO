//package org.ifmo.laba4.repositories;
//
//import org.ifmo.laba4.domain.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//public class InMemoryUserRepository implements UserRepository {
//    private final List<User> users = new ArrayList<>();
//
//    @Override
//    public Optional<User> findByUsername(String username) {
//        return users.stream()
//                .filter(user -> user.getUsername().equals(username))
//                .findFirst();
//    }
//
//    public User saveUser(User user) {
//        users.add(user);
//        return user;
//    }
//}
