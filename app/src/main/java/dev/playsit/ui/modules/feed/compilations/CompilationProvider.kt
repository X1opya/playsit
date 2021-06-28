package dev.playsit.ui.modules.feed.compilations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import dev.playsit.dto.Compilation
import dev.playsit.repository.CompilationPaginationSource
import dev.playsit.repository.FeedRepository
import dev.playsit.ui.modules.feed.compilations.utils.Title

class CompilationProvider(compilation: Compilation, repository: FeedRepository): Title {
    private val name = compilation.name
    private val description = compilation.description
    val id = compilation.id
    val type = compilation.type

    val lazyFeedItems by lazy {
        Pager(PagingConfig(5)) {
            CompilationPaginationSource(repository, compilation.slug)
        }.flow
    }

    override fun getName() = name

    override fun getSubTitle() = description
}
