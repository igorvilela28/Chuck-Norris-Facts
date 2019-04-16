//package com.igorvd.chuckfacts.utils
///**
// *
// * @see <a href="https://medium.com/@alexfacciorusso/understanding-dagger-2-multibindings-viewmodel-8418eb372848">
// *     https://medium.com/@alexfacciorusso/understanding-dagger-2-multibindings-viewmodel-8418eb372848</a>
// */
//
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import javax.inject.Inject
//import javax.inject.Provider
//
//@Suppress("UNCHECKED_CAST")
//class ViewModelFactory @Inject
//constructor(
//        private val creators: Map<Class<out ViewModel>,
//                @JvmSuppressWildcards Provider<ViewModel>>
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        var creator: Provider<out ViewModel>? = creators[modelClass]
//        if (creator == null) {
//            for ((key, value) in creators) {
//                if (modelClass.isAssignableFrom(key)) {
//                    creator = value
//                    break
//                }
//            }
//        }
//        if (creator == null) {
//            throw IllegalArgumentException("unknown model class $modelClass")
//        }
//        try {
//            return creator.get() as T
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//
//    }
//}