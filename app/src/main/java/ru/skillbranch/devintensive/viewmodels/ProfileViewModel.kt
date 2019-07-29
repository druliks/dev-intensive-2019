package ru.skillbranch.devintensive.viewmodels

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

class ProfileViewModel :ViewModel(){
    private val repository:PreferencesRepository=PreferencesRepository
    private val profileData=MutableLiveData<Profile>()
    private val appTheme=MutableLiveData<Int>()
    private val repositoryError = MutableLiveData<Boolean>()
    private val isRepoError = MutableLiveData<Boolean>()
    init {
        profileData.value=repository.getProfile()
        appTheme.value=repository.getAppTheme()
    }

    fun getIsRepoError():LiveData<Boolean> = isRepoError

    fun getRepositoryError(): LiveData<Boolean> = repositoryError

    fun onRepositoryChanged(repository: String) {
        repositoryError.value = isValidateRepository(repository)
    }

    fun onRepoEditCompleted(isError: Boolean) {
        isRepoError.value = isError
    }

    fun getProfileData():LiveData<Profile> = profileData

    fun getTheme():LiveData<Int> = appTheme

    fun saveProfileDAta(profile: Profile){
        repository.saveProfile(profile)
        profileData.value=profile
    }

    fun switchTheme() {
        if (appTheme.value==AppCompatDelegate.MODE_NIGHT_YES){
            appTheme.value=AppCompatDelegate.MODE_NIGHT_NO
        } else {
            appTheme.value=AppCompatDelegate.MODE_NIGHT_YES
        }
        repository.saveAppTheme(appTheme.value!!)
    }

    private fun isValidateRepository(repoText: String): Boolean {
        val regexStr = "^(?:https://)?(?:www.)?(?:github.com/)[^/|\\s]+(?<!${getRegexExceptions()})(?:/)?$"
        val regex = Regex(regexStr)

        return (repoText.isNotEmpty() && !regex.matches(repoText))
    }

    private fun getRegexExceptions(): String {
        val exceptions = arrayOf(
            "enterprise", "features", "topics", "collections", "trending", "events", "marketplace", "pricing",
            "nonprofit", "customer-stories", "security", "login", "join"
        )
        return exceptions.joinToString("|\\b","\\b")
    }
}