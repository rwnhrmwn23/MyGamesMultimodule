package com.onedev.utils

import retrofit2.HttpException
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

object ErrorUtils {
    private const val MESSAGE_HTTP_UNAUTHORIZED = "(401) Tidak Dapat Mengakses Data"
    private const val MESSAGE_HTTP_NOT_FOUND = "(404) Data Tidak Ditemukan"
    private const val MESSAGE_HTTP_INTERNAL_ERROR = "(500) Terjadi Gangguan Pada Server"
    private const val MESSAGE_HTTP_BAD_REQUEST = "(400) Data Tidak Sesuai"
    private const val MESSAGE_HTTP_FORBIDDEN = "(403) Sesi Telah Berakhir"
    private const val MESSAGE_NO_INTERNET = "Tidak Ada Koneksi Internet"

    fun getErrorThrowableMsg(error: Throwable): String = when (error) {
        is HttpException ->
            when (error.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> MESSAGE_HTTP_UNAUTHORIZED
                HttpsURLConnection.HTTP_NOT_FOUND -> MESSAGE_HTTP_NOT_FOUND
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> MESSAGE_HTTP_INTERNAL_ERROR
                HttpsURLConnection.HTTP_BAD_REQUEST -> MESSAGE_HTTP_BAD_REQUEST
                HttpsURLConnection.HTTP_FORBIDDEN -> MESSAGE_HTTP_FORBIDDEN
                else -> "Oops, Terjadi Gangguan, Coba Lagi Beberapa Saat"
            }
        is UnknownHostException -> MESSAGE_NO_INTERNET
        else -> "Terjadi Kesalahan Pada Server"
    }
}