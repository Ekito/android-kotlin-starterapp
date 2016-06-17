package fr.ekito.mykotlinstarterapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName
    val user = "octocat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view -> onFabClick(view) }
    }

    fun onFabClick(view: View) {
        showBar(view, "start ws ...")

        Injector.githubWS.listRepos(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError { error -> showBar(view, "error : $error") }
                .doOnCompleted { Log.i(TAG,"ws terminated") }
                .subscribe { list ->
                    val msg = "got ${list.size} result(s) !"
                    Log.i(TAG, msg)
                    showBar(view, msg)
                }
    }

    fun showBar(view: View, msg: String) {
        Log.d(TAG,"show bar with text : $msg")
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
