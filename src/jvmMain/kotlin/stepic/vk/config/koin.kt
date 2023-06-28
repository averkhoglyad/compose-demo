package stepic.vk.config

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.httpclient.HttpTransportClient
import org.apache.http.client.utils.URIBuilder
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.fileProperties
import stepic.vk.business.VkClient
import stepic.vk.util.getParam

fun KoinApplication.initKoin() {
    fileProperties("/vk/application.properties")
    modules(
        module {
            single { VkApiClient(HttpTransportClient()) }
            single {
                VkClient(
                    get(),
                    getParam("vk.clientId", String::toInt),
                    getParam("vk.clientSecret"),
                    URIBuilder(get<VkApiClient>().oAuthEndpoint)
                        .setPath("blank.html")
                        .build()
                        .toString()
                )
            }
        }
    )
}