package com.example.myjetpackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myjetpackapp.ui.theme.MyJetpackAppTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJetpackAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}
@Composable
fun MyApp(){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if(shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    }
    else {
        Greetings()
    }
}
@Composable
fun Greetings(names: List<String> = listOf("World", "Compose") ){
    Surface(color=MaterialTheme.colors.background){
        Column() {
            for(name in names){
                Greetting(name)
            }
        }
    }
}

@Composable
fun Greetting(name: String) {
    var expanded by rememberSaveable{mutableStateOf(false)}
    val extraPadding by animateDpAsState(
        targetValue = if(expanded) 48.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 100
        )
    )
    Surface(color=MaterialTheme.colors.primary,
    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        Row(modifier=Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello $name!",
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
            OutlinedButton(onClick = { expanded=!expanded}) {
                Text(if(expanded) "Show less" else "Show more")
            }
        }


    }
}
@Composable
fun OnboardingScreen(onContinueClicked: ()-> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text("This is my first app using jetpack compose")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick =  onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    MyJetpackAppTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}
@Preview(showBackground = true, )
@Composable
fun DefaultPreview() {
    MyJetpackAppTheme {
        MyApp()
    }
}