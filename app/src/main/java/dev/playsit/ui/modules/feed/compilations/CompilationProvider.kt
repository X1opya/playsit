package dev.playsit.ui.modules.feed.compilations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import dev.playsit.model.Compilation
import dev.playsit.repository.CompilationPaginationSource
import dev.playsit.repository.FeedRepository

class CompilationProvider(compilation: Compilation, repository: FeedRepository) {
    val name = compilation.name
    val description = compilation.description
    val id = compilation.id
    val type = compilation.type

    val lazyFeedItems by lazy {
        Pager(PagingConfig(5)) {
            CompilationPaginationSource(repository, compilation.slug)
        }.flow
    }
}