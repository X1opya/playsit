package dev.playsit.ui.modules.feed.boards

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import dev.playsit.ui.modules.feed.DiscoverViewModel

@Composable
fun BoardFragment(discoverViewModel: DiscoverViewModel, modifier: Modifier = Modifier) {
    val ourBord = discoverViewModel.ourBoards.observeAsState()
    val userBoard = discoverViewModel.userBoards.observeAsState()
    Column(Modifier.then(modifier)) {
        ourBord.value?.let { OurBordList(boards = it) }
        userBoard.value?.let { UserBoardList(boards = it) }
    }
}