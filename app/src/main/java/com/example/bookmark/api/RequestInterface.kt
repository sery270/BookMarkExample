package com.example.bookmark.api

import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {

    // 예상한대로 주어진 API는 GET 방식 통신인 것을 postman 실행 후 알게 되었음
    // 과제의 요구사항인 paging 처리를 하려면, 아래 통신함수를 페이지 개수 만큼 만들거나, 동적으로 경로를 수정할 수 있어야한다고 생각했음
    // 위 방법 중 후자의 방법으로, @Path를 사용하였음
    // @Path 는 동적으로 경로를 사용하기 위한 어노테이션임 (retrofit2)
    // 따라서 recyclerView의 스크롤 동작을 통해, 현재 페이지를 카운트하고, 카운트한 값을 아래 함수의 매개변수로 넣어서
    // paging 처리를 구현하려고 함.
    // 이와 관련하여 나중에, jetPack Paging 라이브러리를 활용하는 방법을 공부해보면 좋을 듯

    // @Path("paging") page: String 으로 해야할지 해봐야겠다.

    @GET("/App/json/{paging}.json")
    fun requestAccommodationInfo(
            @Path("paging") page: String
    ) : Call<ResponseProductInfo>
}