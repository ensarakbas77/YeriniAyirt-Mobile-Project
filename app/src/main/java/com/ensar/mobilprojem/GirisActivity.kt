package com.ensar.mobilprojem


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.ensar.mobilprojem.databinding.ActivityGirisBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class GirisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGirisBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGirisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=Firebase.auth

        // Tekrar tekrar giriş yapmamak için;
        val guncelKullanici=auth.currentUser
        if(guncelKullanici!=null){
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Giriş Yapma Butonu
        binding.girisButton.setOnClickListener {
            val kullaniciAdi=binding.girisText.text.toString()
            val parola=binding.parolaText.text.toString()

            if(TextUtils.isEmpty(kullaniciAdi)){
                binding.girisText.error="Lütfen Boş Bırakmayınız"
            }
            if(TextUtils.isEmpty(parola)){
                binding.parolaText.error="Lütfen Boş Bırakmayınız"
            }else{

                auth.signInWithEmailAndPassword(kullaniciAdi,parola).addOnCompleteListener {
                        if(it.isSuccessful){
                            val guncekKullanici=auth.currentUser?.email.toString()
                            Toast.makeText(this,"Hoşgeldin: ${guncekKullanici}",Toast.LENGTH_LONG).show()
                            intent=Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                }.addOnFailureListener {
                    Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
                }


            }
        }


        // Üye oluşturma butonu
        binding.uyelikText.setOnClickListener{
            intent= Intent(applicationContext,KayitActivity::class.java)
            startActivity(intent)
        }
    }
}