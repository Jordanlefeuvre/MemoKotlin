package com.formationandroid.memokotlin.MainViewModel


import androidx.lifecycle.ViewModel
import com.formationandroid.memokotlin.bdd.MemoDTO
import com.formationandroid.memokotlin.repository.MainRepository

class MainViewModel : ViewModel() {
    private lateinit var mainRepository: MainRepository

    fun init(mainRepository: MainRepository) {
        this.mainRepository = mainRepository
    }

    fun getLiveDataMemo(): List<MemoDTO> {
        return mainRepository.getLiveDataItem()
    }

    fun insertMemo(memo: MemoDTO) {
        mainRepository.insertMemo(memo)
    }

    fun deleteMemo(memo: MemoDTO) {
        mainRepository.deleteMemo(memo)
    }
}