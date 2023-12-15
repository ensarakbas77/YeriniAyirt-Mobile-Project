package com.ensar.mobilprojem


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.ensar.mobilprojem.databinding.ActivityKayitBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class KayitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKayitBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityKayitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=Firebase.auth

        // Kullanıcı Kayıt Etme Butonu
        binding.kaydetButton.setOnClickListener{
            val kullaniciAdi=binding.kayitKullaniciadiText.text.toString()
            val parola=binding.kayitParolaText.text.toString()

            if(TextUtils.isEmpty(kullaniciAdi)){
                binding.kayitKullaniciadiText.error="Lütfen Boş Bırakmayınız"
            }
            if(TextUtils.isEmpty(parola)){
                binding.kayitParolaText.error="Lütfen Boş Bırakmayınız"
            }else{

                auth.createUserWithEmailAndPassword(kullaniciAdi,parola).addOnCompleteListener {
                       if(it.isSuccessful){
                           intent= Intent(this,GirisActivity::class.java)
                           startActivity(intent)
                           finish()
                           Toast.makeText(this,"Kayıt Başarılı",Toast.LENGTH_LONG).show()
                       }
                }.addOnFailureListener{
                    Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
                }


            }


        }
    }
}