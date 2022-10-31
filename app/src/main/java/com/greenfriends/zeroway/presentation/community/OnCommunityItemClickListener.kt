package com.greenfriends.zeroway.presentation.community

import com.greenfriends.zeroway.data.model.CommunityPost

interface OnCommunityItemClickListener {

    fun setCommunityPostLike(communityPost: CommunityPost)
    fun setCommunityPostBookmark(communityPost: CommunityPost)
    fun reportCommunityPost(communityPost: CommunityPost)
}