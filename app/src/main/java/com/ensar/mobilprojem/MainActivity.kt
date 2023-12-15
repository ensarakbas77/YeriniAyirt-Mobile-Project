package com.ensar.mobilprojem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ensar.mobilprojem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dukkanList:ArrayList<Dukkan>
    private lateinit var dukkanAdapter: DukkanAdapter
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(this)

        dukkanList= ArrayList()

        //dukkanList'e item ekleme
        dukkanList.add(Dukkan(R.drawable.nurettin,"Köfteci Nurettin"))
        dukkanList.add(Dukkan(R.drawable.bilgicler,"Bilgiçler Köfte"))
        dukkanList.add(Dukkan(R.drawable.kofteci_yusuf,"Köfteci Yusuf"))
        dukkanList.add(Dukkan(R.drawable.kofteci_veli,"Köfteci Veli"))
        dukkanList.add(Dukkan(R.drawable.borak_kebap,"Borak Kebap"))
        dukkanList.add(Dukkan(R.drawable.aka_cag_kebab,"Aka Cağ Kebap"))
        dukkanList.add(Dukkan(R.drawable.pasam_kebap,"Paşam Kebap"))
        dukkanList.add(Dukkan(R.drawable.tadim_kebap,"Tadım Kebap"))



        //DukkanAdapter
        dukkanAdapter= DukkanAdapter(dukkanList)
        recyclerView.adapter=dukkanAdapter

        dukkanAdapter.onItemClick={
            val intent=Intent(this,DetayActivity::class.java)
            intent.putExtra("Dükkan",it)
            startActivity(intent)
        }



    }
}