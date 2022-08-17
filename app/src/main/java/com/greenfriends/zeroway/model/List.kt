package com.greenfriends.zeroway.model

data class WordList(
    val title: String,
    val engTitle: String,
    val content: String
)

data class ShopList(
    val title: String,
    val imgUrl: String
//    val score: String,
//    val count: String,
)

data class TipList(
    val number:String,
    val content: String
)

data class UseList(
    val count: String,
    val title: String
)

data class AlarmList(
    val title: String,
    val time: String,
    val content: String
)

data class WordSearchList(
    val content: String
)
