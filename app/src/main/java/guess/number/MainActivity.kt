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
        var min = 1
        var max = choice
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

        fun hints(n: Int) {
            if (n > number) max = n
            if (n < number) min = n
            binding.hint.text = "Загаданное число находится между $min и $max"
            if (n == number) binding.hint.text = "Вы отгадали загаданное число!"
        }

        binding.button.setOnClickListener {
            if (binding.inputTextEdit.text?.isEmpty() == true) {
                binding.textOutput.text = "Необходимо ввести число!"
            } else {
                val n = binding.inputTextEdit.text.toString().toInt()
                hints(n)
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
                        binding.textOutput.text = "Вот это везение!!!\nЗагаданное число $number.\nУгаданно с первой попытки!"
                    } else binding.textOutput.text =
                        "Вы угадали!\nЗагаданное число $number.\nОтгадано за $count ходов!"
                }
                binding.inputTextEdit.text?.clear()
            }
        }

        binding.newGame.setOnClickListener {
            newGame()
        }


    }
}