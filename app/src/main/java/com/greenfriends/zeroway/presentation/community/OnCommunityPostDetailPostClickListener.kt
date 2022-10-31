package com.greenfriends.zeroway.presentation.community

import com.greenfriends.zeroway.data.model.CommunityPostDetailResponse

interface OnCommunityPostDetailPostClickListener {

    fun setCommunityPostLike(communityPostDetailResponse: CommunityPostDetailResponse)
    fun setCommunityPostBookmark(communityPostDetailResponse: CommunityPostDetailResponse)
    fun deleteCommunityPost(communityPostDetailResponse: CommunityPostDetailResponse)
    fun reportCommunityPost(communityPostDetailResponse: CommunityPostDetailResponse)
}