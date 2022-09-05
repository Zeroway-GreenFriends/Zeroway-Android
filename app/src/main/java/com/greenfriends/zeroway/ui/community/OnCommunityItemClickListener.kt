package com.greenfriends.zeroway.ui.community

import com.greenfriends.zeroway.data.model.CommunityPost

interface OnCommunityItemClickListener {

    fun deleteCommunityPost(communityPost: CommunityPost)
    fun setCommunityPostLike(communityPost: CommunityPost)
    fun setCommunityPostBookmark(communityPost: CommunityPost)
}