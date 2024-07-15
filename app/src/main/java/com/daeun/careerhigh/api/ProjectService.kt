package com.daeun.careerhigh.api

import com.daeun.careerhigh.vo.request.ProjectCreateRequest
import com.daeun.careerhigh.vo.response.ProjectCreateResponse
import com.daeun.careerhigh.vo.response.ProjectInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProjectService {

    @Headers("Content-Type: application/json")
    @POST("api/projects")
    fun createProject(@Body projectCreateRequest: ProjectCreateRequest): Call<ProjectCreateResponse>

    @GET("api/clients/{clientId}/projects")
    fun createProjectList(@Path("clientId") clientId: Long, @Query("status") status:String): Call<List<ProjectInfo>>
}