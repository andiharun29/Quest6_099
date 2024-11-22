package com.example.navigationmultipledata.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.navigationmultipledata.Model.Mahasiswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MahasiswaviewModel : ViewModel() {

    private val _mahasiswaStateUI =
        MutableStateFlow(Mahasiswa())

    val mahasiswaUIState:
            StateFlow<Mahasiswa> =
        _mahasiswaStateUI.asStateFlow()

    fun saveDataMahasiswa(ls: MutableList<String>) {
        _mahasiswaStateUI.update { statusSaatIni ->
            statusSaatIni.copy(
                nim = ls[0],
                nama = ls[1],
                email = ls[2],)
        }
    }
}