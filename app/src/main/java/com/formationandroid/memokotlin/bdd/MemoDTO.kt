package com.formationandroid.memokotlin.bdd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memos")
class MemoDTO {
    @PrimaryKey(autoGenerate = true)
    var memoId: Long = 0
    var intitule: String? = null

    constructor() {}

    constructor(intitule: String?) {
        this.intitule = intitule
    }
}