package stepic.vk.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.application
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.error.MissingPropertyException
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.KoinAppDeclaration
import org.koin.java.KoinJavaComponent

fun koinApp(
    exitProcessOnExit: Boolean = true,
    startKoin: KoinAppDeclaration,
    content: @Composable ApplicationScope.() -> Unit
) = application(exitProcessOnExit) {
    startKoin(startKoin)
    doContent(content)
}

fun koinApp(
    exitProcessOnExit: Boolean = true,
    startKoin: KoinApplication,
    content: @Composable ApplicationScope.() -> Unit
) = application(exitProcessOnExit) {
    startKoin(startKoin)
    doContent(content)
}

@Composable
private inline fun ApplicationScope.doContent(content: @Composable ApplicationScope.() -> Unit) {
    val koinAppScope = object : ApplicationScope {
        override fun exitApplication() {
            stopKoin()
            this@doContent.exitApplication()
        }
    }
    koinAppScope.content()
}

inline fun <reified T> di(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = KoinJavaComponent.inject<T>(T::class.java, qualifier, parameters)


inline fun <T : Any> param(key: String): Lazy<T> =
    lazy(LazyThreadSafetyMode.SYNCHRONIZED) { getParam(key) }

inline fun <T : Any> param(key: String, defaultValue: T): Lazy<T> =
    lazy(LazyThreadSafetyMode.SYNCHRONIZED) { getParam(key, defaultValue) }

inline fun <T : Any, R> param(key: String, crossinline modifier: (T) -> R): Lazy<R> =
    lazy(LazyThreadSafetyMode.SYNCHRONIZED) { getParam(key, modifier) }

inline fun <T : Any, R> param(key: String, defaultValue: R, crossinline modifier: (T) -> R): Lazy<R> =
    lazy(LazyThreadSafetyMode.SYNCHRONIZED) { getParam(key, defaultValue, modifier) }

inline fun <R : Any> getParam(key: String): R =
    KoinJavaComponent.getKoin().getProperty(key) ?: throw MissingPropertyException("Property '$key' not found")

inline fun <R : Any> getParam(key: String, defaultValue: R): R =
    KoinJavaComponent.getKoin().getProperty(key) ?: defaultValue

inline fun <T : Any, R> getParam(key: String, crossinline modifier: (T) -> R): R =
    KoinJavaComponent.getKoin().getProperty<T>(key)?.let(modifier)
        ?: throw MissingPropertyException("Property '$key' not found")

inline fun <T : Any, R> getParam(key: String, defaultValue: R, crossinline modifier: (T) -> R): R =
    KoinJavaComponent.getKoin().getProperty<T>(key)?.let(modifier) ?: defaultValue

