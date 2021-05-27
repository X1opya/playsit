package dev.playsit.ui.modules.feed.boards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import dev.playsit.model.Game
import dev.playsit.model.OurBord
import dev.playsit.model.UserBoard
import dev.playsit.ui.modules.feed.compilations.CategoryTitle
import dev.playsit.ui.modules.feed.compilations.utils.Title
import dev.playsit.R
import dev.playsit.ui.components.gameCards.GenreCard
import dev.playsit.ui.components.text.*
import dev.playsit.ui.theme.BaseAppDimen
import dev.playsit.ui.theme.UnSelectTabColor

@Composable
fun UserBoardList(boards: List<UserBoard>) {
    Column {
        Spacer(modifier = Modifier.size(40.dp))
        val title = stringResource(id = R.string.userBoardsTitle)
        val subtitle = stringResource(id = R.string.userBoardsSubtitle)
        CategoryTitle(title = object : Title {
            override fun getName() = title
            override fun getSubTitle() = subtitle
        }, modifier = Modifier.padding(start = BaseAppDimen))
        Column(Modifier.padding(start = BaseAppDimen)) {
            boards.forEach { item ->
                UserBoard(board = item)
                Spacer(modifier = Modifier.size(BaseAppDimen))
            }
        }
    }
}

@Composable
fun OurBordList(boards: List<OurBord>) {
    Column {
        val title = stringResource(id = R.string.ourBoardsTitle)
        val subtitle = stringResource(id = R.string.ourBoardsSubtitle)
        Spacer(modifier = Modifier.size(BaseAppDimen))
        CategoryTitle(title = object : Title {
            override fun getName() = title
            override fun getSubTitle() = subtitle
        }, modifier = Modifier.padding(start = BaseAppDimen))
        LazyRow() {
            itemsIndexed(boards) { index, item ->
                if (index == 0) Spacer(modifier = Modifier.padding(start = BaseAppDimen))
                OurImageBord(board = item)
                Spacer(modifier = Modifier.padding(start = 10.dp))
            }
        }
    }
}

@Composable
fun UserBoard(board: UserBoard) {
    Column(Modifier.padding(end = BaseAppDimen)) {
        UserBoardImages(board.items)
        Spacer(modifier = Modifier.size(15.dp))
        DefaultBoldText(text = board.title, fontSize = 16.sp)
        Row(verticalAlignment = Alignment.Bottom) {
            DefaultGrayText(
                buildAnnotatedString {
                    append(stringResource(id = R.string.by_key))
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFC0C0C0)
                        )
                    ) {
                        append(" @" + board.ownerNickname)
                    }
                    append("  · " + board.items?.size + " " + stringResource(id = R.string.game_key))
                },
                modifier = Modifier.weight(1f)
            )
            GenreCard(genre = board.likesCount.toString(), R.drawable.ic_heart_gray)
        }
    }
}
@Composable
private fun OurImageBord(board: OurBord) {
    val width = LocalConfiguration.current.screenWidthDp.dp - 50.dp
    Card(
        Modifier
            .height(170.dp)
            .width(width)
            .clip(RoundedCornerShape(16.dp))) {
        Image(
            painter = rememberCoilPainter(request = board.cover, fadeIn = true),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            Modifier
                .padding(horizontal = 25.dp)
        ) {
            Column {
                Spacer(modifier = Modifier.size(56.dp))
                CategoryTitleText(text = board.title)
                GenreText(text = board.subtitle)
                Spacer(modifier = Modifier.size(19.dp))
                SmallText(text = board.smallInfo ?: "")
            }
        }
    }
}

@Composable
private fun UserBoardImages(items: List<Game?>?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        items?.let {
            BoardImage(items.elementAtOrNull(0)?.cover, 170.dp, Modifier.weight(1f))
            Spacer(modifier = Modifier.size(10.dp))
            Column(Modifier.weight(1f)) {
                BoardImage(items.elementAtOrNull(1)?.cover)
                Spacer(modifier = Modifier.size(10.dp))

                BoardImage(items.elementAtOrNull(2)?.cover)
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column(Modifier.weight(1f)) {
                BoardImage(items.elementAtOrNull(3)?.cover, 110.dp)
                Spacer(modifier = Modifier.size(10.dp))
                BoardImage(items.elementAtOrNull(4)?.cover, 50.dp)
            }
        }
    }
}

@Composable
fun BoardImage(uri: String?, height: Dp = 80.dp, modifier: Modifier = Modifier) {
    Image(
        painter = rememberCoilPainter(
            request = uri,
            fadeIn = true
        ),
        contentDescription = null,
        modifier = Modifier
            .then(modifier)
            .height(height)
            .background(UnSelectTabColor),
        contentScale = ContentScale.Crop,

        )
}