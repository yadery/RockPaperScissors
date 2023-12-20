package com.example.rockpaperscissors

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.rockpaperscissors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);
        binding.btnRock.isEnabled = false;
        binding.btnPaper.isEnabled = false;
        binding.btnScissors.isEnabled = false;
        binding.btnLizard.isEnabled = false;
        binding.btnSpock.isEnabled = false;
    }

    val listOfChoices = listOf("Камень", "Ножницы", "Бумага", "Ящерица", "Спок");
    var userChoice = "";
    var computerChoice = "";
    var gameInProgress = false;

    @SuppressLint("ResourceAsColor")
    fun btnStartGame(view: View) {
        if(gameInProgress){
            computerChoice = GetComputerChoice(listOfChoices);
            HighLightComputerChoice(computerChoice);
            if (IsThisATie(userChoice, computerChoice)){
                binding.textResult.text = "Ничья.";
            }
            else {
                if(PlayerIsAWinner(userChoice, computerChoice)){
                    binding.textResult.text = "Вы победили!";
                    binding.textResult.setTextColor(Color.GREEN);
                }
                else {
                    binding.textResult.text = "Вы проиграли!";
                    binding.textResult.setTextColor(Color.RED);
                }
            }
            binding.textChoice.text = "Ваш выбор: " + userChoice +"\nВыбор компьютера: " + computerChoice;
            gameInProgress = false;
            binding.btnGame.text = "Повторить?"
            binding.btnRock.isEnabled = false;
            binding.btnPaper.isEnabled = false;
            binding.btnScissors.isEnabled = false;
            binding.btnLizard.isEnabled = false;
            binding.btnSpock.isEnabled = false;
        }
        else{
            gameInProgress = true;
            binding.btnGame.isEnabled = false;
            binding.btnGame.text = "Подтвердить?"
            binding.textResult.setTextColor(Color.BLACK);
            binding.textResult.text = "";
            binding.textChoice.text = "Нажмите на картинку, чтобы выбрать";
            binding.btnRock.isEnabled = true;
            binding.btnPaper.isEnabled = true;
            binding.btnScissors.isEnabled = true;
            binding.btnLizard.isEnabled = true;
            binding.btnSpock.isEnabled = true;
            binding.btnRock.setBackgroundResource(R.drawable.rock);
            binding.btnScissors.setBackgroundResource(R.drawable.scissors);
            binding.btnPaper.setBackgroundResource(R.drawable.paper);
            binding.btnLizard.setBackgroundResource(R.drawable.lizard);
            binding.btnSpock.setBackgroundResource(R.drawable.spock);
        }
    }
    fun GetComputerChoice(variants: List<String>): String{
        return variants.random();
    }
    fun GetUserChoiceRock(view: View){
        userChoice = "Камень";
        AfterTheChoice();
    }
    fun GetUserChoicePaper(view: View){
        userChoice = "Бумага";
        AfterTheChoice();
    }
    fun GetUserChoiceScissors(view: View){
        userChoice = "Ножницы";
        AfterTheChoice();
    }
    fun GetUserChoiceLizard(view: View){
        userChoice = "Ящерица";
        AfterTheChoice();
    }
    fun GetUserChoiceSpock(view: View){
        userChoice = "Спок";
        AfterTheChoice();
    }

    fun PlayerIsAWinner (userChoice: String, computerChoice: String): Boolean{
        return (userChoice == "Камень" && computerChoice == "Ножницы") ||
                (userChoice == "Камень" && computerChoice == "Ящерица") ||
                (userChoice == "Бумага" && computerChoice == "Камень") ||
                (userChoice == "Бумага" && computerChoice == "Спок") ||
                (userChoice == "Ножницы" && computerChoice == "Бумага") ||
                (userChoice == "Ножницы" && computerChoice == "Ящерица") ||
                (userChoice == "Ящерица" && computerChoice == "Бумага") ||
                (userChoice == "Ящерица" && computerChoice == "Спок") ||
                (userChoice == "Спок" && computerChoice == "Камень") ||
                (userChoice == "Спок" && computerChoice == "Ножницы")
    }

    fun IsThisATie(userChoice: String, computerChoice: String): Boolean{
        return userChoice == computerChoice;
    }

    fun AfterTheChoice(){
        binding.btnRock.setBackgroundResource(R.drawable.rock);
        binding.btnScissors.setBackgroundResource(R.drawable.scissors);
        binding.btnPaper.setBackgroundResource(R.drawable.paper);
        binding.btnLizard.setBackgroundResource(R.drawable.lizard);
        binding.btnSpock.setBackgroundResource(R.drawable.spock);
        binding.textChoice.text = "Ваш выбор: " + userChoice;
        binding.btnGame.isEnabled = true;
        when (userChoice) {
            "Камень" -> binding.btnRock.setBackgroundResource(R.drawable.rock_player)
            "Ножницы" -> binding.btnScissors.setBackgroundResource(R.drawable.scissors_player);
            "Бумага" -> binding.btnPaper.setBackgroundResource(R.drawable.paper_player);
            "Ящерица" -> binding.btnLizard.setBackgroundResource(R.drawable.lizard_player);
            "Спок" -> binding.btnSpock.setBackgroundResource(R.drawable.spock_player);
        }
    }

    fun HighLightComputerChoice(userChoice: String) {
        when (computerChoice) {
            "Камень" -> binding.btnRock.setBackgroundResource(R.drawable.rock_enemy);
            "Ножницы" -> binding.btnScissors.setBackgroundResource(R.drawable.scissors_enemy);
            "Бумага" -> binding.btnPaper.setBackgroundResource(R.drawable.paper_enemy);
            "Ящерица" -> binding.btnLizard.setBackgroundResource(R.drawable.lizard_enemy);
            "Спок" -> binding.btnSpock.setBackgroundResource(R.drawable.spock_enemy);
        }
    }
}