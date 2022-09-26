package mmierzwa.service;

import lombok.AllArgsConstructor;
import mmierzwa.model.ConfirmationToken;
import mmierzwa.model.User;
import mmierzwa.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.AuthProvider;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException{
        return userRepository.getReferenceById(id);
    }

    public String signUpUser(User user){
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if(userExists){
            // TODO check for expiration of an email and whether it is the same data of a person
            throw new IllegalStateException("The email you have chosen is already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
                );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void processOAuthPostLogin(String username) {
        Optional<User> existUser = userRepository.findByUsername(username);

        if (existUser.isEmpty()) {
            User newUser = new User();
            newUser.setUsername(username);
//            newUser.setAuthProvider(AuthProvider.GOOGLE);
            newUser.setEnabled(true);

            userRepository.save(newUser);
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) { userRepository.deleteAppUserById(id); }

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
