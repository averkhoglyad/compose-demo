package stepic.vk.business

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.UserActor
import org.apache.http.client.utils.URIBuilder
import java.net.URI
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


class VkClient(private val vk: VkApiClient,
               private val clientId: Int,
               private val clientSecret: String,
               private val redirectUrl: String) {

    fun buildLoginUrl(): URI {
        return URIBuilder(vk.oAuthEndpoint)
            .setPath("authorize")
            .addParameter("client_id", clientId)
            .addParameter("display", "page")
            .addParameter("redirect_uri", redirectUrl)
            .addParameter("response_type", "code")
            .addParameter("v", vk.version)
            .build()
    }

    fun authenticate(url: String): UserActor {
        val params = parseVkRedirectUri(url)
        val authResponse = vk.oAuth()
            .userAuthorizationCodeFlow(clientId, clientSecret, redirectUrl, params["code"])
            .execute()
        return UserActor(authResponse.userId, authResponse.accessToken)
    }

    private fun parseVkRedirectUri(str: String) = str.split('#').last()
        .split('&')
        .asSequence()
        .map { it.split('=') }
        .map { it[0] to URLDecoder.decode(it[1], StandardCharsets.UTF_8) }
        .toMap()
}

private fun URIBuilder.addParameter(param: String, value: Any): URIBuilder = this.addParameter(param, value.toString())
