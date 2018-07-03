package com.fursa.task2.authservice;

import com.fursa.task2.authservice.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuthService {
    private static volatile AuthService instance;
    private final Map<String, User> usersMap = new ConcurrentHashMap<>();
    private final Map<String, User> registeredMap = new ConcurrentHashMap<>();

  //  private static final Logger logger = Logger.getLogger(AuthService.class);

    private AuthService() {}

    public static AuthService getInstance() {
        AuthService localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AuthService();
                }
            }
        }
        return localInstance;
    }

    public boolean isAuthorized(String sessionId) {
        return registeredMap.containsKey(sessionId);
    }

    public User registerNewUser(String sessionId, String login, String password) {
        if(login == null || password == null) {
            return null;
        }

        registeredMap.put(sessionId, new User(login, password));
        return registeredMap.get(sessionId);
    }

    public User authorize(String sessionId, String login, String password) {
        if (isAuthorized(sessionId)) {
            return registeredMap.get(sessionId);
        }

        User user = usersMap.get(sessionId);
        if (user != null && user.getUserPassword().equals(password) && user.getUserName().equals(login)) {
            registeredMap.put(sessionId, user);
            return user;
        }

        return null;
    }


}
