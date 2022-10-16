package diplomado.ejemplos.osmar.esprimo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial
import diplomado.ejemplos.osmar.esprimo.databinding.ActivityMainBinding
import diplomado.ejemplos.osmar.esprimo.databinding.SwitchItemBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var bindingSwitch: SwitchItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        bindingSwitch = SwitchItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.tvHello.text = "Hi"
        binding.txt2.text = "Warning"
        binding.btn1.text = "Action"
        binding.nuevoBtn.text = "Action 2"*/

        //another way to use binding
        /*with(binding){
            tvHello.text = "Hello"
            txt2.text = "Osmar"
            btn1.text = "ACTION"
        }*/

        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        if(menu!=null){
            //Way 1:
            //bindingSwitch.switchDarkmode

            //Way 2 without using parameter bindingSwitch, because we only use it only once
            val item = menu.findItem(R.id.opc_darkmode)
            val mySwitch = item.actionView.findViewById<SwitchMaterial>(R.id.switch_darkmode)

            //if OS is already in dark mode, change image background and switch
            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                mySwitch.toggle()
                //
                binding.cl.setBackgroundResource(R.drawable.back2dark)
            }else{
                binding.cl.setBackgroundResource(R.drawable.back2)
            }

            mySwitch.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }

            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.opc_darkmode -> {
                true
            } else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    fun clickButton(view: View) {
        with(binding) {
            if (!editTextNumero.text.isEmpty()) {
                val numero = editTextNumero.text.toString().toInt()
                //resources.getString() is the same to use getString in latest versions of IDE
                if (esPrimo(numero)) txtResult.text = resources.getString(R.string.si_primo, numero, "!")
                else txtResult.text = getString(R.string.no_primo, numero)
            } else {
                Toast.makeText(this@MainActivity, getString(R.string.ingresa_valor), Toast.LENGTH_SHORT).show()
                binding.editTextNumero.error = getString(R.string.valor_requerido)
            }
        }
    }

    fun esPrimo(numero: Int): Boolean {
        if(numero<=1) return false
        else{
            for(i in 2 until numero){
                if(numero % i == 0 ) return false
            }
            return true
        }
        return true
    }
}
