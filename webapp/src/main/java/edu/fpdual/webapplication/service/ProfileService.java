package edu.fpdual.webapplication.service;

import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.client.ProfileClient;
import edu.fpdual.webapplication.dto.Profile;

import java.util.List;

@Model(type = "Service", version = "1.0", date = "01/06/2023")
public class ProfileService {

    private final ProfileClient profileClient;

    public ProfileService(ProfileClient profileClient) {
        this.profileClient = profileClient;
    }

    /**
     * Proves if the service is available.
     *
     * @return the following string : "Service online"
     */
    public String ping() {
        return profileClient.ping();
    }

    public List<Profile> getAllProfiles() {
        return profileClient.get();
    }

    public Profile getProfileById(int userId) {
        return profileClient.get(userId);
    }

    public Profile getProfileByEmail(String email) {
        return profileClient.get(email);
    }

    public boolean updateProfile(Profile profile) {
        return profileClient.put(profile);
    }

    public boolean createProfile(Profile profile) {
        return profileClient.post(profile);
    }

    public boolean deleteProfile(Profile profile) {
        return profileClient.delete(profile);
    }
}
