package com.example.codeexp.backend.storage;

import com.example.codeexp.backend.exceptions.WrongEntityException;
import com.example.codeexp.backend.model.Profile;

public interface ProfileStorage {

    public void fetchUserProfile(String emailUid);

    public void writeUserProfile(Profile user) throws WrongEntityException;

}

interface ProfileStorageSync {
    /*--- SYNC FROM STORAGE ---*/
    public void hasLoadedUserProfile(Profile user);
}
