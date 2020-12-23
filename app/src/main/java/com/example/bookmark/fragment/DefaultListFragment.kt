package com.example.bookmark.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmark.R
import com.example.bookmark.adapter.BookMarkedListAdapter
import com.example.bookmark.adapter.DefaultListAdapter
import com.example.bookmark.api.Product
import com.example.bookmark.api.RequestToServer
import com.example.bookmark.api.ResponseProductInfo
import com.example.bookmark.util.ItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DefaultListFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: DefaultListAdapter
    private var page = 1
    private var init = true
    var pageData = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_default_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerView init
        recyclerView = view.findViewById<RecyclerView>(R.id.list_fg_rv)
        adapter = DefaultListAdapter(view.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(ItemDecoration())

        // recyclerView data init
        if(init){
            init = false
            getDefaultList(page)
        }else{
            adapter.datas = pageData
            adapter.notifyDataSetChanged()
//            Log.e("pageData ! ",
//                "${pageData[0].name}" )
        }



        // recyclerView paging
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                var lastVisibleItemPosition =  (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                var itemTotalCount = (recyclerView.layoutManager as LinearLayoutManager).itemCount - 1
//                Log.e("스크롤 중 ! ",
//                    "lastVisibleItemPosition: ${lastVisibleItemPosition.toString()} " +
//                            "itemTotalCount : ${itemTotalCount.toString()} " +
//                            "page : ${page.toString()} " )
                if (lastVisibleItemPosition == itemTotalCount && page < 3){
                    page++
                    getDefaultList(page)
                }
            }

        })

        // item click listener -> navigate to detailFG
        adapter.setItemClickListener(object : DefaultListAdapter.ItemClickListener{
            override fun onClick(
                view: View,
                position: Int,
                data: Product,
                datas: MutableList<Product>
            ) {
                Log.d("SSS","${position}번 리스트 선택")
//                                    title = view.findViewById<TextView>(R.id.item_writing_sentence_book_result_tv_title).text.toString()
//                                    author = view.findViewById<TextView>(R.id.item_writing_sentence_book_result_tv_author).text.toString()
//                                    publisher = view.findViewById<TextView>(R.id.item_writing_sentence_book_result_tv_publisher).text.toString()

                // 아이템을 선택했다면 step2로 이동
                val action = R.id.action_view_pager_fragment_to_detail_fragment
                view.findNavController().navigate(action)
            }
        })

    }

    private fun getDefaultList(page: Int){
        val call: Call<ResponseProductInfo> = RequestToServer.service.requestAccommodationInfo(page)
        call.enqueue(object : Callback<ResponseProductInfo>{
            // 통신 자체 실패 -> 클라 잘못
            override fun onFailure(
                call: Call<ResponseProductInfo>,
                t: Throwable
            ) {
                Log.e("통신실패", t.toString())
            }

            // 통신 성공
            @SuppressLint("LongLogTag")
            override fun onResponse(
                call: Call<ResponseProductInfo>,
                response: Response<ResponseProductInfo>
            ) {
                if (response.isSuccessful) {
                    response.body().let { body ->
                        Log.e(
                            "통신응답바디",
                            "status: ${body!!.code} data : ${body.msg}"
                        )

                        if (body.data.product.isNullOrEmpty()) {
                            //if 서버 통신 성공 && 결과 없음

                        } else {
                            //if 서버 통신 성공 && 결과 있음
                            // rv 동작 게시

                            // paging 처리
                            // Integer.parseInt(page)
                            if( page == 1){
                                pageData = body.data.product
                                adapter.datas = pageData
                                adapter.notifyDataSetChanged()
                            }else{
                                pageData.addAll(body.data.product)
                                adapter.datas = pageData
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                } else {
                    //if 서버 통신 실패
                    Log.d("서버 통신", "서버 통신 실패")
                }

            }

        })

    }


}