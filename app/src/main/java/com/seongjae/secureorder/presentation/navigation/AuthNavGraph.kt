package com.seongjae.secureorder.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.seongjae.secureorder.presentation.feature.signin.SignInScreen
import com.seongjae.secureorder.presentation.feature.verification.face.FaceVerificationScreen
import com.seongjae.secureorder.presentation.feature.verification.id.IDVerificationScreen
import com.seongjae.secureorder.presentation.feature.verification.otp.OtpScreen
import com.seongjae.secureorder.presentation.navigation.AuthNavRoute.FACE_VERIFICATION
import com.seongjae.secureorder.presentation.navigation.AuthNavRoute.ID_VERIFICATION
import com.seongjae.secureorder.presentation.navigation.AuthNavRoute.LOGIN
import com.seongjae.secureorder.presentation.navigation.AuthNavRoute.OTP
import com.seongjae.secureorder.presentation.navigation.MainNavigationRoute.MENU_GRAPH

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(startDestination = LOGIN, route = MainNavigationRoute.AUTH_GRAPH) {
        composable(LOGIN) {
            SignInScreen(
                onLoginSuccess = {
                    navController.navigate(OTP)
                }
            )
        }
        composable(OTP) {
            OtpScreen(
                onOtpVerified = {
                    navController.navigate(ID_VERIFICATION)
                }
            )
        }
        composable(ID_VERIFICATION) {
            IDVerificationScreen(
                onIdVerified = {
                    navController.navigate(FACE_VERIFICATION)
                }
            )
        }
        composable(FACE_VERIFICATION) {
            FaceVerificationScreen(
                onVerificationSuccess = {
                    navController.navigate(MENU_GRAPH) {
                        popUpTo(MainNavigationRoute.AUTH_GRAPH) { inclusive = true }
                    }
                }
            )
        }
    }
}

object AuthNavRoute {
    const val LOGIN = "login"
    const val OTP = "otp"
    const val ID_VERIFICATION = "id_verification"
    const val FACE_VERIFICATION = "face_verification"
}