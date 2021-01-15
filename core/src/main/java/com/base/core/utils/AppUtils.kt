package com.base.core.utils

object AppUtils {
    fun getTeamPhoto(
        teamId: Int,
        sportType: String = "soccer",
        height: Int = 76,
        width: Int = 76
    ): String {
        return "https://img-canliskor.mncdn.com/mnresize/${height}x${width}/Logos/Broadage/teams/${sportType}/${teamId}.png"
    }

    fun getLeaguePhoto(
        leagueId: Int,
        sportType: String?,
        height: Int = 0,
        width: Int = 0
    ): String {
        return "https://iapp.fanatik.com.tr/resize/${height}x${width}/Logos/Broadage/tournaments/${sportType}/${leagueId}.png"
    }

    fun getFanatikTeamPhoto(
        teamId: Int?,
        sportType: String? = "soccer",
        height: Int = 0,
        width: Int = 0
    ): String {
        return "https://iapp.fanatik.com.tr/resize/${height}x${width}/Logos/Broadage/teams/${sportType}/${teamId}.png"
    }

    fun getFanatikLeaguePhoto(
        leagueId: Int?,
        sportType: String? = "soccer",
        height: Int = 0,
        width: Int = 0
    ): String {
        return "https://iapp.fanatik.com.tr/resize/${height}x${width}/Logos/Broadage/tournaments/${sportType}/${leagueId}.png"
    }

    fun getFanatikCountryPhoto(
        leagueId: Int?,
        width: Int = 63,
        height: Int = 63

    ): String {
        return "http://iapp.fanatik.com.tr/resize/${height}x${width}/images/flags/byid/circle/${leagueId}.png"
    }
}


