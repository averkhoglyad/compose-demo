package stepic.vk.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import stepic.vk.representation.view.main.MainLayout
import stepic.vk.representation.view.login.LoginView
import stepic.vk.representation.view.main.AuthState
import stepic.vk.representation.view.main.MainViewModel

object MainScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { MainViewModel() }
        val screenState by viewModel.state.collectAsState()
        when (screenState) {
            is AuthState.Authorized -> MainLayout()
            else -> LoginView(
                onLoginClick = { viewModel.requestLogin() },
                onOauth = { viewModel.performCallbackParams(it) }
            )
        }
    }
}
