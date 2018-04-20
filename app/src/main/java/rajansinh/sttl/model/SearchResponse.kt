package rajansinh.sttl.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by rajan.bhavsar on 3/21/2018.
 */
data class User(
        @SerializedName("login") val login: String,
        @SerializedName("id") val id: Long,
        @SerializedName("url") val url: String,
        @SerializedName("html_url") val htmlUrl: String,
        @SerializedName("followers_url") val followersUrl: String,
        @SerializedName("following_url") val followingUrl: String,
        @SerializedName("starred_url") val starredUrl: String,
        @SerializedName("gists_url") val gistsUrl: String,
        @SerializedName("type") val type: String,
        @SerializedName("score") val score: String
)

data class CustomUser(
        @SerializedName("id") val login: Int,
        @SerializedName("name") val name: String,
        @SerializedName("email") val email: String,
        @SerializedName("phone") val phone: String,
        @SerializedName("website") val website: String
)

/**
 * Entire search result data class
 */
data class Result(
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: List<User>
)

data class CustomResult(
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: List<CustomUser>
)

data class Github(
        val login: String,
        val String_blog: String,
        val public_repos: Int
)

data class MovieResponse(
        @SerializedName("page") val page: Int,
        @SerializedName("total_results") val total_results: Int,
        @SerializedName("total_pages") val total_pages: Int,
        @SerializedName("results") var results: MutableList<ResultData>
)

@Entity(tableName = "videoList")
data class ResultData(
        @ColumnInfo(name = "vote_count") @SerializedName("vote_count") val vote_count: Int,
        @PrimaryKey(autoGenerate = false) @SerializedName("id") val id: Int,
        @ColumnInfo(name = "vote_average") @SerializedName("vote_average") val vote_average: Float,
        @ColumnInfo(name = "video") @SerializedName("video") val video: Boolean,
        @ColumnInfo(name = "title") @SerializedName("title") val title: String,
        @ColumnInfo(name = "popularity") @SerializedName("popularity") val popularity: Float,
        @ColumnInfo(name = "poster_path") @SerializedName("poster_path") val poster_path: String,
        @ColumnInfo(name = "original_language") @SerializedName("original_language") val original_language: String,
        @ColumnInfo(name = "original_title") @SerializedName("original_title") val original_title: String,
        @ColumnInfo(name = "backdrop_path") @SerializedName("backdrop_path") val backdrop_path: String,
        @ColumnInfo(name = "adult") @SerializedName("adult") val adult: Boolean,
        @ColumnInfo(name = "overview") @SerializedName("overview") val overview: String,
        @ColumnInfo(name = "release_date") @SerializedName("release_date") val release_date: String
) {
    companion object TO {
        val MASTER = 0
        val ROBOT = 1
    }
}
