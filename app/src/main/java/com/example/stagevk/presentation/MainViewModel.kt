package com.example.stagevk.presentation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stagevk.data.ProductRepositoryImpl
import com.example.stagevk.data.retrofit.ApiFactory
import com.example.stagevk.domain.entities.Product
import com.example.stagevk.domain.usecases.GetAllProductsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val LIMIT_ON_PAGE = 20
    }

    private var counter = 0
    private var page = 1

    private val repository = ProductRepositoryImpl
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val getAllProducts = GetAllProductsUseCase(repository)

    private val listProducts = MutableLiveData<List<Product>>()
    val listProductsLD: LiveData<List<Product>>
        get() = listProducts

    private var progressBar = MutableLiveData<Boolean>()
    val progressBarLD: LiveData<Boolean>
        get() = progressBar

    private var error = MutableLiveData<Boolean>(false)
    val errorLD: LiveData<Boolean>
        get() = error

    fun loadAllProducts() {
        val disposable = getAllProducts(counter, LIMIT_ON_PAGE)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnSubscribe {
                error.value = false
                progressBar.value = true }
            ?.doAfterTerminate {
                progressBar.value = false
            }
            ?.subscribe({
                listProducts.value = it.products
            }, {
                error.value = true
            })
        disposable?.let { compositeDisposable.add(it) }
    }

    fun incrementCounter() {
        if (!listProducts.value.isNullOrEmpty()) {
            counter += 20
        }
    }

    fun decrementCounter() {
        if (counter != 0) {
            counter -= 20
        }
    }

    fun incrementPage(): String {
        var result = page
        if (listProducts.value.isNullOrEmpty()) {
            Toast.makeText(getApplication(), "Page is empty", Toast.LENGTH_SHORT).show()
            result = page
        } else {
            result += 1
            page++
        }
        return result.toString()
    }

    fun decrementPage(): String {
        var result = page
        if (page == 1) {
            result = 1
        } else {
            result -= 1
            --page
        }
        return result.toString()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}