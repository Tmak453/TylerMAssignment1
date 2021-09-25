package sheridan.makrist.assignment1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import sheridan.makrist.assignment1.databinding.ActivityMainBinding
import sheridan.makrist.assignment1.model.Results
import sheridan.makrist.assignment1.InputFragment
import sheridan.makrist.assignment1.OutputFragment

class MainActivity : AppCompatActivity(), InputFragment.Listener, OutputFragment.Listener  {

    companion object{
        const val INPUT_TO_OUTPUT = "Input_to_Output"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, InputFragment())
                .commit()
        }
    }

    override fun showOutput(result: Results) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, OutputFragment.newInstance(result))
            .addToBackStack(INPUT_TO_OUTPUT)
            .commit()
    }

    override fun showInput(){
        supportFragmentManager.popBackStack(
            INPUT_TO_OUTPUT, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}