package com.fueled.technicalchallenge.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.fueled.technicalchallenge.presentation.model.CharacterPresentation

@Composable
fun CharacterDetails(
    characterDomain: CharacterPresentation,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = rememberAsyncImagePainter(characterDomain.defaultImageUrl),
                    contentDescription = "${characterDomain.name} Image",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomEnd = 8.dp))
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Column {
                        Text(
                            style = MaterialTheme.typography
                                .bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(
                                vertical = 4.dp,
                                horizontal = 8.dp
                            ),
                            text = characterDomain.name,
                            maxLines = 1
                        )

                        Text(
                            style = MaterialTheme.typography.bodyMedium
                                .copy(color = MaterialTheme.colorScheme.onPrimary),
                            modifier = Modifier.padding(
                                vertical = 4.dp,
                                horizontal = 8.dp
                            ),
                            text = characterDomain.description
                        )
                    }
                }
            }
        }
    }
}