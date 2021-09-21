package hu.autsoft.krateexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.autsoft.krateexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpleKrate.setOnClickListener {
            val intent = Intent(this, ExampleActivity::class.java)
                .putExtra(ExampleActivity.KEY_KRATE_TYPE, ExampleActivity.TYPE_SIMPLE)
            startActivity(intent)
        }
        binding.btnCustomKrate.setOnClickListener {
            val intent = Intent(this, ExampleActivity::class.java)
                .putExtra(ExampleActivity.KEY_KRATE_TYPE, ExampleActivity.TYPE_CUSTOM)
            startActivity(intent)
        }
    }

}
