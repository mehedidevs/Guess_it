package com.mehedi.guess_it.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.mehedi.guess_it.R
import com.mehedi.guess_it.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private val viewmodel: GameViewmodel by viewModels()

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )

        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        updateScoreText()
        updateWordText()
        viewmodel.hasGameFinished.observe(viewLifecycleOwner) {
            if (it) {
                gameFinished()
            }
        }

        return binding.root

    }


    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameToScore(0)
        findNavController(this).navigate(action)
    }


    /** Methods for buttons presses **/
    private fun onSkip() {
        viewmodel.nextWord()
        viewmodel.onSkip()
    }

    private fun onCorrect() {
        viewmodel.nextWord()
        viewmodel.onCorrect()
    }

    /** Methods for updating the UI **/
    private fun updateWordText() {
        viewmodel.word.observe(viewLifecycleOwner) {
            binding.wordText.text = it
        }
    }

    private fun updateScoreText() {
        viewmodel.score.observe(viewLifecycleOwner) {
            binding.scoreText.text = it.toString()
        }

    }
}
