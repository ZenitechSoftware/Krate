package hu.autsoft.krateexample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSimpleKrate.setOnClickListener {
            val intent = Intent(this, ExampleActivity::class.java)
                    .putExtra(ExampleActivity.KEY_KRATE_TYPE, ExampleActivity.TYPE_SIMPLE)
            startActivity(intent)
        }
        btnCustomKrate.setOnClickListener {
            val intent = Intent(this, ExampleActivity::class.java)
                    .putExtra(ExampleActivity.KEY_KRATE_TYPE, ExampleActivity.TYPE_CUSTOM)
            startActivity(intent)
        }
    }

}
