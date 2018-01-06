package com.nomrasco.roomsimple.features

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.nomrasco.roomsimple.R
import com.nomrasco.roomsimple.ThisApp
import com.nomrasco.roomsimple.libdb.daos.PetDao
import com.nomrasco.roomsimple.libdb.daos.UserDao
import com.nomrasco.roomsimple.libdb.entities.Pet
import com.nomrasco.roomsimple.libdb.entities.User
import kotlinx.android.synthetic.main.activity_initial.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class InitialActivity : AppCompatActivity(), AnkoLogger {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var userDao: UserDao

    @Inject
    lateinit var petDao: PetDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        ThisApp.Injector.injectInto(this)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.btn_simple_test)
    fun action1() {
        // create simple user and pet
        val pet = Pet(name = "Argo", age = 16)
        val user = User(firstName = "Darek", surname = "Something", age = 13)

        doAsync {
            user.id = userDao.insert(user)
            pet.owner = user.id
            pet.id = petDao.insert(pet)

            info(String.format("User.id=%d, Pet.id=%d & Pet.owner=%d", user.id, pet.id, pet.owner))

            uiThread {
                btn_simple_test.text = "Run already"
            }
        }
    }

    @OnClick(R.id.btn_another_test)
    fun anotherAction(view: View) {
        userDao.getAllObservable().observe(this, Observer<List<User>> { newList ->
            doAsync {
                info("Changes found!")
                newList?.forEach {
                    info("Next user is: $it, added at ${it.added ?: "missing"}")
                }
            }
        })
    }
}
