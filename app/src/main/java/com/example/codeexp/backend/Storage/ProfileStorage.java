package com.example.codeexp.backend.Storage;

import com.example.codeexp.backend.Exception.WrongEntityException;
import com.example.codeexp.backend.Model.Profile;

public interface ProfileStorage {

    public void fetchUserProfile(String emailUid);

    public void writeUserProfile(Profile user) throws WrongEntityException;

}

interface ProfileStorageSync {
    /*--- SYNC FROM STORAGE ---*/
    public void hasLoadedUserProfile(Profile user);
}
