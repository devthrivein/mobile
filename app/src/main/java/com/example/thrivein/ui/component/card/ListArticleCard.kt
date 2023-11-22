package com.example.thrivein.ui.component.card

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R

@Composable
fun ListArticleCard(
    modifier: Modifier = Modifier
) {
    var showFullText by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .padding(bottom = 30.dp)
            .animateContentSize(),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier.background(Color.White)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.ic_result_photo),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 15.dp)
            ) {
                Text(
                    text = "Analysts project 32% upside for",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.clickable{
                        showFullText = !showFullText
                    },
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt........Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt........Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt........Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt........",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    maxLines = if (showFullText) 5 else 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListArticleCardPreview() {
    ListArticleCard()
}