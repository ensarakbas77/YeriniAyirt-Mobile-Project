package com.ensar.mobilprojem

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ensar.mobilprojem.databinding.ActivityDetayBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class DetayActivity : AppCompatActivity() {
        private lateinit var binding: ActivityDetayBinding
        private lateinit var db:FirebaseFirestore
        private lateinit var auth: FirebaseAuth
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=Firebase.auth
        db=Firebase.firestore

        val dukkan=intent.getParcelableExtra<Dukkan>("Dükkan")
        if (dukkan!=null) {
            val textView: TextView = findViewById(R.id.detayText)
            val imageView: ImageView = findViewById(R.id.detayImageView)

            textView.text = dukkan.name
            imageView.setImageResource(dukkan.image)
        }

            // Gün Spinner
            val day=arrayOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27
                ,28,29,30)
            val spinner=findViewById<Spinner>(R.id.spinner)
            val arrayAdapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,day)
            spinner.adapter=arrayAdapter

            spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent:AdapterView<*>?,view:View?,position:Int,id:Long) {
                    binding.dayText.text=day[position].toString()
                }

                override fun onNothingSelected(position: AdapterView<*>?) {

                }

            }
            // ******************************************************************* //


            // Ay Spinner
            val month = arrayOf("Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos"
            ,"Eylül","Ekim","Kasım","Aralık")
            val spinner1=findViewById<Spinner>(R.id.spinner2)
            val arrayAdapter1=ArrayAdapter(this,android.R.layout.simple_spinner_item,month)
            spinner1.adapter=arrayAdapter1

            spinner1.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent:AdapterView<*>?,view:View?,position:Int,id:Long) {
                    binding.monthText.text=month[position].toString()
                }

                override fun onNothingSelected(position: AdapterView<*>?) {

                }

            }
            // ******************************************************************* //


            // Saat Spinner
            val clock= arrayOf("9.00","10.00","11.00","12.00","13.00","14.00","15.00","16.00","17.00")
            val spinner2=findViewById<Spinner>(R.id.spinner3)
            val arrayAdapter2=ArrayAdapter(this,android.R.layout.simple_spinner_item,clock)
            spinner2.adapter=arrayAdapter2

            spinner2.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent:AdapterView<*>?,view:View?,position:Int,id:Long) {
                    binding.clockText.text=clock[position].toString()
                }

                override fun onNothingSelected(position: AdapterView<*>?) {

                }

            }
            // ******************************************************************* //


            // Masa Spinner
            val table= arrayOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)
            val spinner3=findViewById<Spinner>(R.id.spinner4)
            val arrayAdapter3=ArrayAdapter(this,android.R.layout.simple_spinner_item,table)
            spinner3.adapter=arrayAdapter3

            spinner3.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent:AdapterView<*>?,view:View?,position:Int,id:Long) {
                    binding.tableText.text=day[position].toString()
                }

                override fun onNothingSelected(position: AdapterView<*>?) {

                }

            }
            // ******************************************************************* //

            // Rezervasyon Yapma Butonu
            binding.btnKaydet.setOnClickListener {
                val restoran=binding.detayText.text.toString()
                val gun=binding.dayText.text.toString()
                val ay=binding.monthText.text.toString()
                val saat=binding.clockText.text.toString()
                val masaNo=binding.tableText.text.toString()
                val guncelKullaniciEmaili=auth.currentUser!!.email.toString()


                // Alert Dialog uyarı mesajı
                val uyariMesaji=AlertDialog.Builder(this@DetayActivity)
                uyariMesaji.setTitle("Uyarı")
                uyariMesaji.setMessage("Rezervasyonu oluşturmak istediğinize emin misiniz?")
                uyariMesaji.setPositiveButton("Evet",DialogInterface.OnClickListener{dialogInterface, i ->

                    //Firebase Veri kaydetme İşlemi
                    val postMap= hashMapOf<String,Any>()
                    postMap.put("Restoran",restoran)
                    postMap.put("Rezervasyon Ayi",ay)
                    postMap.put("Rezervasyon Gunu",gun)
                    postMap.put("Rezervasyon Saati",saat)
                    postMap.put("Masa No",masaNo)
                    postMap.put("Kullanici",guncelKullaniciEmaili)

                    db.collection("İnformation").add(postMap).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(this,"Rezervasyonunuz yapıldı",Toast.LENGTH_LONG).show()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
                    }


                })
                uyariMesaji.setNegativeButton("Hayır"){ dialogInterface,i->
                    Toast.makeText(this,"Rezervasyonunuz yapılamadı!",Toast.LENGTH_LONG).show()
                }
                uyariMesaji.show()

            }


            // Bilgileri Gösterme Butonu
            binding.btnGoster.setOnClickListener {
                intent= Intent(this,BilgilerActivity::class.java)
                startActivity(intent)
            }


    }
}
