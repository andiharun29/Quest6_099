package com.example.navigationmultipledata.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigasiwithdata.ui.screen.MahasiswaFormView
import com.example.navigasiwithdata.ui.screen.RencanaStudyView
import com.example.navigasiwithdata.ui.screen.SplashView
import com.example.navigasiwithdata.ui.screen.TampilView
import com.example.navigasiwithdata.ui.viewmodel.MahasiswaViewModel
import com.example.navigasiwithdata.ui.viewmodel.RencanaStudyViewModel
import com.example.navigationmultipledata.ui.screen.SplashView
import com.example.navigationmultipledata.ui.viewmodel.MahasiswaviewModel
import com.example.navigationmultipledata.ui.viewmodel.RencanaStudyViewModel


enum class Halaman{
    Splash,
    Mahasiswa,
    Matakuliah,
    Tampil
}

@Composable
fun MahasiswaApp(
    modifier: Modifier = Modifier,
    mahasiswaviewModel: MahasiswaviewModel = viewModel(),
    RencanaStudyViewModel: RencanaStudyViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val mahasiswaUiState = MahasiswaviewModel.mahasiswaUiState.collectAsState().value
    NavHost(
        navController = navController,
        startDestination = Halaman.Splash.name,
        modifier = Modifier.padding()
    ) {
        composable(route = Halaman.Splash.name){
            SplashView (onMulaiButton = {
                navController.navigate(
                    Halaman.Mahasiswa.name
                )
            })
        }
        composable(route = Halaman.Mahasiswa.name){
            MahasiswaFormView(
                onSubmitButtonClicked = {
                    MahasiswaViewModel.saveDataMahasiswa(it)
                    navController.navigate(Halaman.Matakuliah.name)
                },
                onBackButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Halaman.Matakuliah.name){
            RencanaStudyView(
                mahasiswa = mahasiswaUiState,
                onSubmitButtonClicked = {
                    RencanaStudyViewModel.saveDataKRS(it)
                    navController.navigate(Halaman.Tampil.name)},
                onBackButtonClicked = { navController.popBackStack()}
            )
        }
        composable(route = Halaman.Tampil.name) {
            TampilView(
                mahasiswa = mahasiswaUiState,
                krs = RencanaStudyViewModel.krsStateUi.collectAsState().value,
                onBackButtonClicked = {
                    navController.popBackStack()
                },
                onResetButtonClicked = {
                    MahasiswaViewModel.resetData()
                    navController.popBackStack(Halaman.Splash.name, inclusive = false)
                }
            )
        }
    }
}