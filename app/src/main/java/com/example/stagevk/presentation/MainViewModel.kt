package com.example.stagevk.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.stagevk.data.ProductRepositoryImpl
import com.example.stagevk.domain.entities.Category
import com.example.stagevk.domain.entities.ListItems
import com.example.stagevk.domain.usecases.GetAllCategoriesUseCase
import com.example.stagevk.domain.usecases.GetAllProductsUseCase
import com.example.stagevk.domain.usecases.GetOneCategoryUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val LIMIT_ON_PAGE = 20
        private const val ONE = 1
        private const val ZERO = 0
        private const val TWENTY = 20
    }

    private var counter = ZERO

    private val repository = ProductRepositoryImpl
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val getAllProducts = GetAllProductsUseCase(repository)
    private val getOneCategory = GetOneCategoryUseCase(repository)
    private val getAllCategory = GetAllCategoriesUseCase(repository)


    private val listProducts = MutableLiveData<List<ListItems>>()
    val listProductsLD: LiveData<List<ListItems>>
        get() = listProducts

    private val listOneCategory = MutableLiveData<List<ListItems>>()
    val listOneCategoryLD: LiveData<List<ListItems>>
        get() = listOneCategory

    private val listAllCategories = MutableLiveData<List<ListItems>>()
    val listAllCategoriesLD: LiveData<List<ListItems>>
        get() = listAllCategories

    private var progressBar = MutableLiveData<Boolean>()
    val progressBarLD: LiveData<Boolean>
        get() = progressBar

    private var error = MutableLiveData(false)
    val errorLD: LiveData<Boolean>
        get() = error

    private var page = MutableLiveData(ONE)
    val pageLD: LiveData<Int>
        get() = page


    //загружаем все товары на главный экран
    fun loadAllProducts() {
        val disposable = getAllProducts(counter, LIMIT_ON_PAGE)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnSubscribe {
                error.value = false
                progressBar.value = true
            }
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

    //загружаем список всех категорий на главный экран
    fun loadAllCategories() {
        val disposable = getAllCategory()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnSubscribe {
                error.value = false
                progressBar.value = true
            }
            ?.doAfterTerminate {
                progressBar.value = false
            }
            ?.subscribe({
                val array = mutableListOf<ListItems>()
                for (temp in it.listIterator()) {
                    array.add(Category(temp))
                }
                listAllCategories.value = array
            }, {
                error.value = true
            })
        disposable?.let { compositeDisposable.add(it) }
    }

    //по клику отображаем результат запроса по поиску одной категории
    fun loadOneCategoryProducts(category: String) {
        val disposable = getOneCategory(category)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnSubscribe {
                error.value = false
                progressBar.value = true
            }
            ?.doAfterTerminate {
                progressBar.value = false
            }
            ?.subscribe({
                listOneCategory.value = it.products
            }, {
                error.value = true
            })
        disposable?.let { compositeDisposable.add(it) }
    }


    //вспомогательные функции счетчиков
    fun incrementCounter() {
        if (!listProducts.value.isNullOrEmpty()) {
            counter += TWENTY
        }
    }

    fun decrementCounter() {
        if (counter != ZERO) {
            counter -= TWENTY
        }
    }

    fun incrementPage() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!listProducts.value.isNullOrEmpty()) {
                page.postValue(page.value!! + ONE)
            }
        }
    }

    fun decrementPage() {
        viewModelScope.launch(Dispatchers.IO) {
            if (page.value!! > ONE) {
                page.postValue(page.value!! - ONE)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}