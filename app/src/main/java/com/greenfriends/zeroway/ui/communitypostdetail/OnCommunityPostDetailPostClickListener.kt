package com.greenfriends.zeroway.ui.communitypostdetail

import com.greenfriends.zeroway.model.CommunityPostDetailResponse

interface OnCommunityPostDetailPostClickListener {

    fun deleteCommunityPost(communityPostDetailResponse: CommunityPostDetailResponse)
    fun setCommunityPostLike(communityPostDetailResponse: CommunityPostDetailResponse)
    fun setCommunityPostBookmark(communityPostDetailResponse: CommunityPostDetailResponse)
}