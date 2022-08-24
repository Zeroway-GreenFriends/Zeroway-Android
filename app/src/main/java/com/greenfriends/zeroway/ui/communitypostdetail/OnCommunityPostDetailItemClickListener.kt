package com.greenfriends.zeroway.ui.communitypostdetail

import com.greenfriends.zeroway.model.CommunityPostDetailResponse

interface OnCommunityPostDetailItemClickListener {

    fun deleteCommunityPost(communityPostDetail: CommunityPostDetailResponse)
    fun setCommunityPostLike(communityPostDetail: CommunityPostDetailResponse)
    fun setCommunityPostBookmark(communityPostDetail: CommunityPostDetailResponse)
}