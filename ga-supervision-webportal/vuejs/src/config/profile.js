let profile = {};

import http from './http';

function setProfile(p) {
    profile = p;
}

function getStoredProfile() {
    return profile;
}

function getProfile(force) {

    let self = this;
    return new Promise((resolve, reject) => {
        let storedProfile = self.getStoredProfile();

        if (!force && storedProfile.user)
            resolve(storedProfile);
        else {
            http.jsonPost('/profile').then((res) => {
                let result = res.data;
                if (result.resultCode === 200) {
                    self.setProfile(result);
                    resolve(result);
                } else {
                    reject({resultCode: 401, message: 'get profile faild'});
                }
            });
        }
    });
}

export default {
    setProfile,
    getProfile,
    getStoredProfile
};
