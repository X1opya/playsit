package dev.playsit.ui.modules.feed.compilations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import dev.playsit.model.Compilation
import dev.playsit.repository.CompilationSource
import dev.playsit.repository.FeedRepository

class CompilationProvider(compilation: Compilation, repository: FeedRepository) {
    val name = compilation.name
    val description = compilation.description
    val id = compilation.id

    val lazyFeedItems by lazy {
        Pager(PagingConfig(10)) {
            CompilationSource(repository, compilation.slug)
        }.flow
    }
}