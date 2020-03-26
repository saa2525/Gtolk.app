package com.saample.gtolkapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.widget.AdapterView
import android.widget.Toast
import android.widget.ArrayAdapter
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat.getActionView
import android.widget.Spinner
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.annotation.NonNull
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val spinnerItems1 = arrayOf("iPhone", "Android", "Apple", "Windows")
    private val spinnerItems2 = arrayOf("Japan", "America", "China", "Canada")


    private lateinit var mToolbar: Toolbar
    //private var mGenre =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)

        //ログイン有無
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { _ ->
            // ログイン済みのユーザーを取得する
            val user = FirebaseAuth.getInstance().currentUser

            // ログインしていなければログイン画面に遷移させる
            if (user == null) {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        // ナビゲーションドロワーの設定
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawer, mToolbar, R.string.app_name, R.string.app_name)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        //〇スピナー
        val sp1 = navigationView.menu.findItem(R.id.nav_bgkb).actionView as Spinner
        val sp2 = navigationView.menu.findItem(R.id.nav_rgkb).actionView as Spinner
        //スピナーにアダプターをセット
        sp1.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spinnerItems1)
        sp2.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spinnerItems2)

        //リスナーを登録
        sp1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            //アイテムが選択されたとき
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@MainActivity,spinnerItems1[position], Toast.LENGTH_SHORT).show()

            }

            //アイテムが選択されなかったとき
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        sp2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            //アイテムが選択されたとき
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@MainActivity,spinnerItems2[position], Toast.LENGTH_SHORT).show()

            }

            //アイテムが選択されなかったとき
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // ボトムナビゲーションを読み込む
        val bottomavigation = findViewById(R.id.bottom_navigation) as BottomNavigationView
        // BottomNavigationViewHelperでアイテムのサイズ、アニメーションを調整
        BottomNavigationViewHelper.disableShiftMode(bottomavigation)
        // BottomNavigationViewを選択したときのリスナー
        bottomavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            // 各選択したときの処理
            when (item.itemId) {
                R.id.nav_home ->

                    return@OnNavigationItemSelectedListener true
                R.id.nav_search ->

                    return@OnNavigationItemSelectedListener true
                R.id.nav_box ->

                    return@OnNavigationItemSelectedListener true
                R.id.nav_new ->

                    return@OnNavigationItemSelectedListener true
            }
            false
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}




