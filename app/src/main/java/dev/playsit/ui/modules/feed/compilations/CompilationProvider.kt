package dev.playsit.ui.modules.feed.compilations

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dev.playsit.dto.Compilation
import dev.playsit.repository.CompilationPaginationSource
import dev.playsit.repository.FeedRepository
import dev.playsit.ui.modules.feed.DiscoverViewModel
import dev.playsit.ui.modules.feed.compilations.utils.Title
import kotlinx.coroutines.launch

class CompilationProvider(
    compilation: Compilation,
    repository: FeedRepository,
    private val viewModel: DiscoverViewModel
) : Title {
    private val name = compilation.name
    private val description = compilation.description
    val id = compilation.id
    val type = compilation.type
    val slug = compilation.slug
    var shitCatch = false
    var loading: Boolean = true
        set(value) {
            viewModel.viewModelScope.launch {
                if(!value) {
                    viewModel._isLoading.emit(value)
                }
            }
        }

    val lazyFeedItems =
        Pager(PagingConfig(5, enablePlaceholders = true)) {
            CompilationPaginationSource(repository, compilation.slug)
        }.flow

    override fun getName() = name

    override fun getSubTitle() = description
}
