package com.greenfriends.zeroway.ui.community

import com.greenfriends.zeroway.model.CommunityPost

interface OnCommunityItemClickListener {

    fun deleteCommunityPost()
    fun setCommunityPostLike(communityPost: CommunityPost)
    fun setCommunityPostBookmark(communityPost: CommunityPost)
}