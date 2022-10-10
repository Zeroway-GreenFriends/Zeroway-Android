package com.greenfriends.zeroway.presentation.community

import com.greenfriends.zeroway.data.model.CommunityPostDetailResponse

interface OnCommunityPostDetailPostClickListener {

    fun deleteCommunityPost(communityPostDetailResponse: CommunityPostDetailResponse)
    fun setCommunityPostLike(communityPostDetailResponse: CommunityPostDetailResponse)
    fun setCommunityPostBookmark(communityPostDetailResponse: CommunityPostDetailResponse)
}