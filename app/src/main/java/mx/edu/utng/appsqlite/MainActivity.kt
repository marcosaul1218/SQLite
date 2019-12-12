package mx.edu.utng.appsqlite

import mx.edu.utng.appsqlite.db.DatabaseHandler
import mx.edu.utng.appsqlite.model.Users
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init db
        dbHandler = DatabaseHandler(this)

        //on Click Save button
        button_save.setOnClickListener(View.OnClickListener {
            // checking input text should not be null
            if (validation()){
                val user: Users = Users()
                var success: Boolean = false
                user.firstName = editText_firstName.text.toString()
                user.lastName = editText_lastName.text.toString()

                success = dbHandler!!.addUser(user)

                if (success){
                    val toast = Toast.makeText(this,"Saved Successfully", Toast.LENGTH_LONG).show()
                }
            }

        })

        //on Click Save button
        button_Delete.setOnClickListener(View.OnClickListener {
            // checking input text should not be null

                val user: Users = Users()
                user.firstName = editText_Delete.text.toString()
               var success = dbHandler!!.dellUser(user)




        })

        //on Click show button
        button_show.setOnClickListener(View.OnClickListener {
            var user = dbHandler!!.getAllUsers()
            textView_show.setText(user)
        })
        button_search.setOnClickListener(View.OnClickListener {

            val user: Users = Users()
            user.firstName = editText_Search.text.toString()
            var success = dbHandler!!.getUser(user)
            textView_show.setText(success)
        })

    }
    fun validation(): Boolean{
        var validate = false

        if (!editText_firstName.text.toString().equals("") &&
            !editText_lastName.text.toString().equals("")){
            validate = true
        }else{
            validate = false
            val toast = Toast.makeText(this,"Fill all details", Toast.LENGTH_LONG).show()
        }

        return validate
    }
 
}
