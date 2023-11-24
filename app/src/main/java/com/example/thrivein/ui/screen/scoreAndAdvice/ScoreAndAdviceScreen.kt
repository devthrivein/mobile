package com.example.thrivein.ui.screen.scoreAndAdvice

//import androidx.compose.foundation.layout.RowScopeInstance.align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary


@Composable
fun ScoreAndAdviceScreen(
    id: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Primary),
    ) {
        Image(
            modifier = Modifier.fillMaxHeight(),
            painter = painterResource(
                id = R.drawable.bg_score_advice
            ),
            contentDescription = "",
            alignment = Alignment.TopStart
        )
        //Score
        Column(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.TopCenter)
                .offset(y = 60.dp)
                .fillMaxWidth(),// Adjust the offset value as needed,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.thrive_in),
            )
            Image(
                painter = painterResource(id = R.drawable.ic_result_photo),
                contentDescription = stringResource(id = R.string.photo)
            )
            Text(
                text = stringResource(id = R.string.your_photo),
                textAlign = TextAlign.Center,
            )
        }

        //Advice
        Box(
            modifier = Modifier
                .height(500.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(color = Background)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Advice",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Lorem ipsum dolor sit ametjjj jjjjjjjjjjj jjjjjjjjjjjj jjjjjj jjjjjjjjjjjjjjjjjjjj\n" +
                            "Lorem ipsum dolor sit amet\n" +
                            "Lorem ipsum dolor sit amet\n" +
                            "Lorem ipsum dolor sit amet",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                )
            }

        }
    }
}

@Preview
@Composable
fun ScoreAndAdviceScreenPreview() {
    ScoreAndAdviceScreen("1")
}

