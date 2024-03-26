package co.nes.parceltestkmp.feature.place.horizontalList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.feature.place.model.Place
import co.nes.parceltestkmp.ui.theme.AspenTheme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import parceltestkmp.composeapp.generated.resources.AspenSplashScreen
import parceltestkmp.composeapp.generated.resources.Res

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlaceHorizontalList(
    popular: List<Place>,
    onPopularItemClicked: (place: Place) -> Unit,
    recommended: List<Place>,
    onRecommendedItemClicked: (place: Place) -> Unit,
) {
    Spacer(modifier = Modifier.height(16.dp))

    Text("Popular", style = AspenTheme.typography.displayMedium)

    LazyRow {
        items(popular.size, key = { index -> popular[index].name }) { index ->
            val place = popular[index]
            Card(modifier = Modifier.padding(4.dp), onClick = {
                onPopularItemClicked(place)
            }) {
                Column(modifier = Modifier.padding(16.dp)) {
                    PlaceImageComposable(url = place.image)
                    Text(text = place.name)
                    Text(text = "Rating: ${place.rating}")
                }
            }
        }
    }

    Text("Places", style = AspenTheme.typography.displaySmall)
    LazyRow {
        items(recommended.size, key = { index -> recommended[index].name }) { index ->
            val place = recommended[index]
            Card(modifier = Modifier.padding(4.dp), onClick = {
                onRecommendedItemClicked(place)
            }) {
                Column(modifier = Modifier.padding(16.dp)) {
                    PlaceImageComposable(url = place.image, false)
                    Text(
                        text = place.name,
                    )
                    Text(
                        text = "Rating: ${place.rating}",
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PlaceImageComposable(url: String, isPopular: Boolean = true) {
    Image(
        painter = painterResource(Res.drawable.AspenSplashScreen),
        contentDescription = "Splash Screen Aspen",
        modifier = if (isPopular) {
            Modifier.fillMaxWidth().width(200.dp).height(300.dp)
        } else {
            Modifier.fillMaxWidth().width(170.dp).height(150.dp)
        },
        contentScale = ContentScale.FillBounds,
    )
}