package com.young.animeapp_with_volley_glide.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.young.animeapp_with_volley_glide.model.Anime
import com.young.animeapp_with_volley_glide.R
import com.young.animeapp_with_volley_glide.adapters.RecyclerViewAdapter
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val JSON_URL = "https://gist.githubusercontent.com/johnny3young/708578ba09a3c54bc20971e18c275357/raw/92e80f1414c5711aad00d808f4907d912c0dcb2f/anime.json\n"
    private var request: JsonArrayRequest? = null
    private var requestQueue: RequestQueue? = null
    private var lstAnime: MutableList<Anime>? = null
    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lstAnime = ArrayList()
        recyclerView = findViewById(R.id.recyclerviewid)

        jsonrequest()

    }

    private fun jsonrequest() {
        request = JsonArrayRequest(JSON_URL, Response.Listener { response ->
            var jsonObject: JSONObject?
            for (i in 0 until response.length()) {
                try {
                    jsonObject = response.getJSONObject(i)
                    var anime = Anime("","","",0,"","","")
                    anime.name = jsonObject.getString("name")
                    anime.description = jsonObject.getString("description")
                    anime.rating = jsonObject.getString("Rating")
                    anime.categorie = jsonObject.getString("categorie")
                    anime.nb_episode = jsonObject.getInt("episode")
                    anime.studio = jsonObject.getString("studio")
                    anime.image_url = jsonObject.getString("img")
                    lstAnime!!.add(anime)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            setuprecyclerview(lstAnime)
        }, Response.ErrorListener { })
        requestQueue = Volley.newRequestQueue(this@MainActivity)
        requestQueue?.add(request)
    }

    private fun setuprecyclerview(lstAnime: List<Anime>?) {
        val myadapter =
            RecyclerViewAdapter(
                this,
                lstAnime!!
            )
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = myadapter
    }

}