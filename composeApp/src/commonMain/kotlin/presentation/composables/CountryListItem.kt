package presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun CountryListItem(
    countryName: String, flagImage: String, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.background(color = MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        KamelImage(
            resource = asyncPainterResource(flagImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(width = 100.dp, height = 60.dp)
        )

        Text(text = countryName, color = MaterialTheme.colorScheme.onPrimary)

    }
}