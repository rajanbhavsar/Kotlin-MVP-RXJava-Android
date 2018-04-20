package rajansinh.sttl.utils

import android.content.Context
import android.net.ConnectivityManager

public class Utils{

    companion object {
        fun CheckConnectivity(context: Context): Boolean? {
            var bNetwork: Boolean? = false
            try {

                val cm = context.getSystemService(Context
                        .CONNECTIVITY_SERVICE) as ConnectivityManager?
                val networkInfos = cm!!.allNetworkInfo
                for (tempNetworkInfo in networkInfos) {

                    if (tempNetworkInfo.isConnected) {
                        bNetwork = true
                        break
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return bNetwork

        }
    }
}