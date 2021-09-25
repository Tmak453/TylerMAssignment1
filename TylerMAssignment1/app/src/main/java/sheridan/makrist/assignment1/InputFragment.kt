package sheridan.makrist.assignment1

import android.os.Bundle
import android.content.Context
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sheridan.makrist.assignment1.model.Results
import sheridan.makrist.assignment1.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    interface Listener {
        fun showOutput(results: Results)
    }

   private var listener: Listener? = null

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputBinding.inflate(inflater, container, false)

        binding.sendButton.setOnClickListener { showOutput() }

        return binding.root
    }

    private fun showOutput(){

        // get the selected message text
        val userChoice = when (binding.messageGroup.checkedRadioButtonId) {
            R.id.rock_button -> getString(R.string.rock)
            R.id.paper_button -> getString(R.string.paper)
            R.id.scissors_button -> getString(R.string.scissors)
            else -> getString(R.string.undefined)
        }

        val num = (0..3).random()
        var aiChoice = ""
        var winner = ""



        if (num == 0)
        {
            aiChoice = getString(R.string.rock)
        }
        else if(num == 1)
        {
            aiChoice = getString(R.string.paper)
        }
        else
        {
            aiChoice = getString(R.string.scissors)
        }

        if (userChoice.equals("Rock") && aiChoice.equals("Scissors"))
        {
            winner = "User Wins!"
        }
        else if (userChoice.equals("Paper") && aiChoice.equals("Rock"))
        {
            winner = "User Wins!"
        }
        else if (userChoice.equals("Scissors") && aiChoice.equals("Paper"))
        {
            winner = "User Wins!"
        }
        else if (userChoice.equals("Scissors") && aiChoice.equals("Rock"))
        {
            winner = "Computer Wins!"
        }
        else if (userChoice.equals("Rock") && aiChoice.equals("Paper"))
        {
            winner = "Computer Wins!"
        }
        else if (userChoice.equals("Paper") && aiChoice.equals("Scissors"))
        {
            winner = "Computer Wins!"
        }
        else if (userChoice.equals(aiChoice))
        {
            winner = "Tie!"
        }


        listener?.showOutput(Results(userChoice, aiChoice, winner))
    }

override fun onAttach(context: Context) {
    super.onAttach(context)
    listener = context as Listener?
}

override fun onDetach() {
    super.onDetach()
    listener = null
}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}

}