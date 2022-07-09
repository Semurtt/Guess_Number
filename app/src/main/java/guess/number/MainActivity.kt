package guess.number

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import guess.number.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var game = 1
        var count = 0
        var choice = 100
        var number = Random.nextInt(1, choice + 1)
        binding.textOutput.text = "Загадываем число от 1 до $choice"

        fun newGame() {
            choice = 100
            number = Random.nextInt(1, choice + 1)
            binding.textOutput.text = "Загадываем число от 1 до $choice"
            binding.button.isVisible = true
            count = 0
            game++
            binding.gameNumber.text = "Игра № $game"
            binding.countNumber.text = "Ход № $count"
        }

        binding.button.setOnClickListener {
            var n = binding.inputText.text.toString().toInt()
            count++
            binding.gameNumber.text = "Игра № $game"
            binding.countNumber.text = "Ход № $count"
            if (n > number) binding.textOutput.text =
                "Вы ввели $n. Загаданное число меньше"
            if (n < number) binding.textOutput.text =
                "Вы ввели $n. Загаданное число больше"
            if (n == number) {
                binding.button.isVisible = false
                if (count == 1) {
                    binding.textOutput.text = "Вот это везение!!! Угаданно с первой попытки!"
                } else binding.textOutput.text =
                    "Вы угадали! Загаданное число $number. Отгадано за $count ходов!"
            }
            binding.inputText.text.clear()
        }

        binding.newGame.setOnClickListener {
            newGame()
        }


    }
}