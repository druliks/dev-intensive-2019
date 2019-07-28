package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils

data class Profile(

    val firstName:String,
    val lastName:String,
    val about:String,
    val repository:String,
    val rating:Int=0,
    val respect:Int=0
) {
    val nickName:String=Utils.transliteration("$firstName $lastName")
    val rank:String="Junior Android Developer"
    val initials = Utils.toInitials(firstName, lastName) ?: ""

    fun toMap():Map<String,Any> = mapOf(
        "nickName" to nickName,
        "initials" to initials,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "nickName" to nickName,
        "about" to about,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect
    )
}