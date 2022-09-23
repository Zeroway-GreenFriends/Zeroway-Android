package com.greenfriends.zeroway.ui.community

import com.greenfriends.zeroway.data.model.CommunityPost

interface OnCommunityItemClickListener {

    fun setCommunityPostLike(communityPost: CommunityPost)
    fun setCommunityPostBookmark(communityPost: CommunityPost)
}