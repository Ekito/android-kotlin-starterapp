package fr.ekito.mykotlinstarterapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import fr.ekito.mykotlinstarterapp.ws.Github
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            onSnackbarClick(view)
        }
    }

    fun onSnackbarClick(view: View) {

        Injector.githubWS.listRepos("octocat").enqueue(object : Callback<List<Github>> {
            override fun onResponse(call: Call<List<Github>>, response: Response<List<Github>>) {
                showBar(view, "Ok : ${call.request().body()}")
            }

            override fun onFailure(call: Call<List<Github>>, t: Throwable) {
                showBar(view, "failed : $t")
            }
        })
    }

    private fun showBar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
