package stepic.vk.representation.view.main

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.UserActor
import com.vk.api.sdk.httpclient.HttpTransportClient
import kotlinx.coroutines.launch
import org.apache.http.client.utils.URIBuilder
import stepic.vk.business.VkClient
import java.awt.Desktop
import java.net.URI
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


class MainViewModel : StateScreenModel<AuthState>(AuthState.Unauthorized) {

    // TODO: Replace instantiation with DI
    private val vkClient = VkClient()

    fun requestLogin() {
        val loginUrl = vkClient.buildLoginUrl()
        println(loginUrl)
        Desktop.getDesktop().browse(loginUrl)
    }

    fun performCallbackParams(url: String) {
        coroutineScope.launch {
            try {
                val actor = vkClient.authenticate(url)
                mutableState.value = AuthState.Authorized(actor)
            } catch (e: Exception) {
                println(e.message)
                e.printStackTrace()
                mutableState.value = AuthState.Forbidden
            }
        }
    }
}

sealed class AuthState {
    object Unauthorized : AuthState()
    object Forbidden : AuthState()
    data class Authorized(val actor : UserActor) : AuthState()
}
