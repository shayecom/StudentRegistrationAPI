package studentsRegistrationAPI.studentRegistrationAPI.services;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {
    private final Set<String> loggedOutTokens = ConcurrentHashMap.newKeySet();

    public void blacklist(String token) {
        loggedOutTokens.add(token);
    }

    public boolean isBlacklisted(String token) {
        return loggedOutTokens.contains(token);
    }
}
