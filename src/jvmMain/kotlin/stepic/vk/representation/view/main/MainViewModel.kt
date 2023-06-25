package stepic.vk.representation.view.main

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.UserActor
import com.vk.api.sdk.httpclient.HttpTransportClient
import kotlinx.coroutines.launch
import org.apache.http.client.utils.URIBuilder
import java.awt.Desktop
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

private const val clientId = 51684530
private const val clientSecret = "uEVWS9BRvMa0ZrnfCCfn"
private const val redirectUrl = "https://oauth.vk.com/blank.html"

class MainViewModel : StateScreenModel<AuthState>(AuthState.Unauthorized) {

    private val vk = VkApiClient(HttpTransportClient())

    fun requestLogin() {
        val loginUri = URIBuilder(vk.oAuthEndpoint)
            .setPath("authorize")
            .addParameter("client_id", clientId.toString())
            .addParameter("display", "page")
            .addParameter("redirect_uri", redirectUrl)
            .addParameter("response_type", "code")
            .addParameter("v", vk.version)
            .build()
        println(loginUri)
        Desktop.getDesktop().browse(loginUri)
    }

    fun performCallbackParams(url: String) {
        coroutineScope.launch {
            try {
                val params = parseVkRedirectUri(url)
                val authResponse = vk.oAuth()
                    .userAuthorizationCodeFlow(clientId, clientSecret, redirectUrl, params["code"])
                    .execute()
                val actor = UserActor(authResponse.userId, authResponse.accessToken)
                mutableState.value = AuthState.Authorized(actor)
            } catch (e: Exception) {
                println(e.message)
                e.printStackTrace()
                mutableState.value = AuthState.Forbidden
            }
        }
    }

    private fun parseVkRedirectUri(str: String) = str.split('#').last()
        .split('&')
        .asSequence()
        .map { it.split('=') }
        .map { it[0] to URLDecoder.decode(it[1], StandardCharsets.UTF_8) }
        .toMap()
}

sealed class AuthState {
    object Unauthorized : AuthState()
    object Forbidden : AuthState()
    data class Authorized(val actor : UserActor) : AuthState()
}