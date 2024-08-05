package com.example.wednesday1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var  username:EditText
    lateinit var password:EditText
  // lateinit var phone:EditText

    lateinit var button:Button
    lateinit var firebaseauth:FirebaseAuth

    lateinit var firebaseDatabase:FirebaseDatabase
    lateinit var databaseref:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        username=findViewById(R.id.username)
        password=findViewById(R.id.pass)
       // phone=findViewById(R.id.mobile)

        button=findViewById(R.id.button)


        val hashMap=HashMap<String,String>();
        firebaseDatabase=FirebaseDatabase.getInstance()//method which return object
        databaseref=firebaseDatabase.getReference().child("Users")//url tak pochega aur child node is created


        button.setOnClickListener {

            firebaseauth=FirebaseAuth.getInstance()
            val email:String=username.text.toString().trim()
            val pwd:String=password.text.toString().trim()
            // val mob:String=phone.text.toString().trim()

            hashMap.put("name",email)//python mai dictonary
            hashMap.put("pass",pwd)

            databaseref.push().setValue(hashMap).addOnSuccessListener {//url tak pochayega, data ko push karega in json format kyuki hash map use hua hai
                Toast.makeText(this@MainActivity,"data entered",Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,"Error Occured",Toast.LENGTH_LONG).show()
            }

            firebaseauth.createUserWithEmailAndPassword(email,pwd).addOnSuccessListener {
                Toast.makeText(this@MainActivity,"user register",Toast.LENGTH_LONG).show()

            }.addOnFailureListener {
                Toast.makeText(this@MainActivity," error",Toast.LENGTH_LONG).show()
            }

        }

    }
}
