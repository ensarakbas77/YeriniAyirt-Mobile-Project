package com.ensar.mobilprojem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ensar.mobilprojem.databinding.ActivityBilgilerBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class BilgilerActivity : AppCompatActivity() {
    private lateinit var binding:ActivityBilgilerBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var database:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBilgilerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=Firebase.auth
        database=Firebase.firestore

        // Verileri Çekmek.
        database.collection("İnformation").addSnapshotListener { value, error ->

            if (error!=null){
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_LONG).show()
            }
            else{
                if (value!=null){
                    if (!value.isEmpty){
                        val documents=value.documents
                        for (i in documents){
                            val kullaniciEmail=i.get("Kullanici") as String
                            val restoran=i.get("Restoran") as String
                            val rezAy=i.get("Rezervasyon Ayi") as String
                            val rezGun=i.get("Rezervasyon Gunu") as String
                            val rezSaat=i.get("Rezervasyon Saati") as String
                            val masaNo=i.get("Masa No") as String
                            val tarih=rezGun+" "+rezAy+" 2023"
                            if (kullaniciEmail==auth.currentUser?.email){
                                binding.emailBilgiText.text="E-mail: "+kullaniciEmail
                                binding.restoranText.text="Restoran: "+restoran
                                binding.tarihText.text="Tarih: "+tarih
                                binding.saatText.text="Saat: "+rezSaat
                                binding.masaNoText.text="Masa No: "+masaNo

                            }
                        }
                    }
                }
            }

            // Çıkış Yapma Butonu
            binding.cikisButon.setOnClickListener {
                auth.signOut()
                intent= Intent(this,GirisActivity::class.java)
                startActivity(intent)
                finish()
            }




            }



    }
}