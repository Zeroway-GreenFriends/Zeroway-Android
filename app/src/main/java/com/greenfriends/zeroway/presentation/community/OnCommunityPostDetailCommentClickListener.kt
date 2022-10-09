package com.greenfriends.zeroway.presentation.community

import com.greenfriends.zeroway.data.model.CommunityPostDetailComment

interface OnCommunityPostDetailCommentClickListener {

    fun deleteCommunityPostComment(communityPostDetailComment: CommunityPostDetailComment)
    fun setCommunityPostCommentLike(communityPostDetailComment: CommunityPostDetailComment)
}