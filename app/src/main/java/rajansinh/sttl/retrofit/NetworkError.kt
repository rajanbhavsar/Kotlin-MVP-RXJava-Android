package rajansinh.sttl.retrofit

import android.text.TextUtils
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import com.google.gson.Gson
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result.response




/**
 * Created by rajan.bhavsar on 3/20/2018.
 */
public class NetworkError : Throwable {

    public val DEFAULT_ERROR_MESSAGE: String = "Something went wrong! Please try again."
    public val NETWORK_ERROR_MESSAGE: String = "No Internet Connection!"
    public val ERROR_MESSAGE_HEADER: String = "Error-Message"
    private var error: Throwable? = null

    constructor(e: Throwable) : super(e) {
        this.error = e;
    }

    fun isAuthFailure(): Boolean {
        return error is HttpException && (error as HttpException).code() === HTTP_UNAUTHORIZED
    }

    fun isResponseNull(): Boolean {

        return error is HttpException && (error as HttpException).response() == null
    }

    fun getAppErrorMessage(): String? {
        if (error is IOException) return NETWORK_ERROR_MESSAGE
        if (!(error is HttpException)) return DEFAULT_ERROR_MESSAGE
        var response: retrofit2.Response<*> = (this.error as HttpException).response();
        if (response != null) {
            var status: String? = getJsonStringFromResponse(response);
            if (!TextUtils.isEmpty(status)) return status

            var headers: Map<String, List<String>> = response.headers().toMultimap()
            if (headers.containsKey(ERROR_MESSAGE_HEADER)) {
                return headers.get(ERROR_MESSAGE_HEADER)?.get(0)
            }
        }
        return DEFAULT_ERROR_MESSAGE
    }

    protected fun getJsonStringFromResponse(response: retrofit2.Response<*>): String? {
        try {
            val jsonString = response.errorBody()?.string()
            val errorResponse = Gson().fromJson<Response<*>>(jsonString, Response::class.java!!)
            return errorResponse.message()
        } catch (e: Exception) {
            return null
        }

    }

    fun getError(): Throwable? {
        return error
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val that = o as NetworkError?

        return if (error != null) error!!.equals(that!!.error) else that!!.error == null

    }

    override fun hashCode(): Int {
        return if (error != null) error!!.hashCode() else 0
    }



}