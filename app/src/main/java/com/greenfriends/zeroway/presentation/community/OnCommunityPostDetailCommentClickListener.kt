package com.greenfriends.zeroway.presentation.community

import com.greenfriends.zeroway.data.model.CommunityPostDetailComment

interface OnCommunityPostDetailCommentClickListener {

    fun setCommunityPostCommentLike(communityPostDetailComment: CommunityPostDetailComment)
    fun deleteCommunityPostComment(communityPostDetailComment: CommunityPostDetailComment)
    fun reportCommunityPostComment(communityPostDetailComment: CommunityPostDetailComment)
}