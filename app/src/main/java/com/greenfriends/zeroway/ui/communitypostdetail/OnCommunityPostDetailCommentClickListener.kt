package com.greenfriends.zeroway.ui.communitypostdetail

import com.greenfriends.zeroway.model.CommunityPostDetailComment

interface OnCommunityPostDetailCommentClickListener {

    fun deleteCommunityPostComment(communityPostDetailComment: CommunityPostDetailComment)
    fun setCommunityPostCommentLike(communityPostDetailComment: CommunityPostDetailComment)
}