package com.example.bme

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var refreshButton:Button = findViewById(R.id.refreshbtn)

        refreshButton.setOnClickListener{
            readData()
        }


    }
    private fun readData(){
        database = FirebaseDatabase.getInstance("https://bmeproject-3a651-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("456")
        database.child("temp").get().addOnSuccessListener {
            if(it.exists()){
                print("database")
                var temp:TextView = findViewById(R.id.temp)
                val temperature = it.value
                temp.text = temperature.toString()
            }
            else{
                //TODO toast
            }
        }.addOnFailureListener{
            it.printStackTrace()
        }
    }
}
