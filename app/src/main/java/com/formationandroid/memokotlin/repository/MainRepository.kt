package com.formationandroid.memokotlin.repository

import com.formationandroid.memokotlin.bdd.MemoDAO
import com.formationandroid.memokotlin.bdd.MemoDTO
import javax.inject.Inject

class MainRepository {
    @Inject lateinit var MemoDAO: MemoDAO

    fun getLiveDataItem(): List<MemoDTO> {
        return MemoDAO.getListeMemos()
    }

    fun insertMemo(memo: MemoDTO) {
        MemoDAO.insert(memo)
    }

    fun deleteMemo(memo: MemoDTO) {
        MemoDAO.delete(memo)
    }

    init {
        DIApplication.getAppComponent()?.inject(this)
    }
}