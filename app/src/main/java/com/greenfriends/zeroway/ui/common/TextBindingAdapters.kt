package com.greenfriends.zeroway.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("communityPostLikeCount")
fun communityPostLikeCount(view: TextView, likeCount: Int) {
    view.text = "$likeCount" + "개"
}

@BindingAdapter("communityPostCommentCount")
fun communityPostCommentCount(view: TextView, commentCount: Int) {
    view.text = "댓글" + "$commentCount" + "개 모두 보기"
}

@BindingAdapter("communityPostDate")
fun communityPostDate(view: TextView, weeksAgo: Int) {
    view.text = "$weeksAgo" + "주"
}

@BindingAdapter("convertIntToString")
fun convertIntToString(view: TextView, text: Int) {
    view.text = text.toString()
}

@BindingAdapter("levelToString")
fun levelToString(view: TextView, level: Int) {
    view.text = "Level "+"$level"
}

@BindingAdapter("levelToLvString")
fun levelToLvString(view: TextView, level: Int) {
    view.text = "Lv."+"$level"
}