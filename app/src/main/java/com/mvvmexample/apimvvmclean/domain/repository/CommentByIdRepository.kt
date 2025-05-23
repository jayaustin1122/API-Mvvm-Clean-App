package com.mvvmexample.apimvvmclean.domain.repository

import com.mvvmexample.apimvvmclean.domain.model.Comment
import com.mvvmexample.apimvvmclean.common.util.Response
import kotlinx.coroutines.flow.Flow

interface CommentByIdRepository {

    fun getCommentById(id: Int):  Flow<Response<List<Comment>>>

}

