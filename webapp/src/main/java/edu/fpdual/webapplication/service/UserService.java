package edu.fpdual.webapplication.service;

import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.client.UserClient;
import edu.fpdual.webapplication.dto.User;

import java.util.List;

@Model(type = "Service", version = "1.0", date = "01/06/2023")
public class UserService {

    private final UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    /**
     * Proves if the service is available.
     *
     * @return the following string : "Service online"
     */
    public String ping() {
        return userClient.ping();
    }

    public List<User> getAllUsers() {
        return userClient.get();
    }

    public User getUserById(int userId) {
        return userClient.get(userId);
    }

    public User getUserByName(String userName) {
        return userClient.get(userName);
    }

    public User getUserByPassword(String userPassword) {
        return userClient.getPassword(userPassword);
    }

    public boolean updateUser(User user) {
        return userClient.put(user);
    }

    public boolean createUser(User user) {
        return userClient.post(user);
    }

    public boolean deleteUser(User user) {
        return userClient.delete(user);
    }
}
