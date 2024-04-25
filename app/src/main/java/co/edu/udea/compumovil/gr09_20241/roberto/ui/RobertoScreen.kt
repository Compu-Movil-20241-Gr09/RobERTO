package co.edu.udea.compumovil.gr09_20241.roberto.ui

import androidx.annotation.StringRes

enum class RobertoScreen(@StringRes val title: Int){
    Login(title = 0),
    Home(title = 1),
    NewTask(title = 2),
    NewRoutine(title = 3),
    NewObjective(title = 4)
}
