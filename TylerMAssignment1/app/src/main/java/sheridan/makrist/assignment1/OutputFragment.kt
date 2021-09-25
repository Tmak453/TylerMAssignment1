package sheridan.makrist.assignment1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sheridan.makrist.assignment1.R
import sheridan.makrist.assignment1.databinding.FragmentOutputBinding
import sheridan.makrist.assignment1.model.Results


class OutputFragment : Fragment() {

    interface Listener{
        fun showInput()
    }

    private var listener: Listener? = null

    companion object{
        private const val RESULT = "Result"

        @JvmStatic
        fun newInstance(result: Results?): OutputFragment{
            val fragment = OutputFragment()
            val arguments = Bundle()
            arguments.putSerializable(RESULT, result)
            fragment.arguments = arguments
            return fragment
        }
    }

    private var _binding: FragmentOutputBinding? = null
    private val binding get() = _binding!!

    private var result : Results? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            result = it.getSerializable(RESULT) as Results?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOutputBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener { listener?.showInput() }

        showResult()

        return binding.root
    }

    private fun showResult(){

        result?.apply {

            binding.userChoice.text = userChoice
            binding.computerChoice.text = aiChoice
            binding.theWinner.text = winner

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
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