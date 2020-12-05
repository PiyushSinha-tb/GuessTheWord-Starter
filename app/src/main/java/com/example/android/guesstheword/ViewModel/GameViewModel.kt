package com.example.android.guesstheword.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    // The current score
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word
    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>
    init {
        _word.value = ""
        _score.value = 0
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
    fun onGameFinish() {
        _eventGameFinish.value = true
    }
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    /**
     * Moves to the next word in the list
     */
    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
    fun nextWord() {
        if (wordList.isEmpty()) {
            //Select and remove a word from the list
            onGameFinish()

        }
        else{
            _word.value = wordList.removeAt(0)
        }

    }


    /** Methods for updating the UI **/


}