package com.rafagnin.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rafagnin.composeplayground.R
import com.rafagnin.playground.ui.theme.*

class C6Bank : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val paymentMethodsList = listOf(
            Pair("Ver Extrato", R.drawable.ic_receipt),
            Pair("Pagar", R.drawable.ic_pay),
            Pair("Transferir", R.drawable.ic_transfer),
            Pair("Depositar", R.drawable.ic_receive),
            Pair("Todos", R.drawable.ic_more)
        )

        val pixList = listOf(
            Pair("Pagar QR Code", R.drawable.ic_receipt),
            Pair("Transferir", R.drawable.ic_pay),
            Pair("Receber", R.drawable.ic_transfer),
            Pair("Saque e Troco", R.drawable.ic_receive)
        )

        setContent {
            ComposePlaygroundTheme {
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        AppBar(name = "Lucas")
                        Balances()
                        Spacer(Modifier.height(40.dp))
                        Squares(paymentMethodsList)
                        Spacer(Modifier.height(24.dp))
                        CardMedium()
                        Header(text = "Pix", icon = R.drawable.ic_pix)
                        SquareGroup(pixList)
                        Header(text = "C6 Invest")
                        CardBig()
                    }
                }
            }
        }
    }
}

@Composable
fun AppBar(name: String) {
    Row(
        Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Column {
            Text(
                text = "Boa tarde,",
                style = C6Typo.smallSecondary
            )
            Text(
                text = name,
                style = C6Typo.normalPrimary
            )
        }
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_message),
                "",
                Modifier.padding(horizontal = 4.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_eye),
                "",
                Modifier.padding(horizontal = 4.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_person),
                "",
                Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}

@Composable
fun Balances() {
    LazyRow(
        Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = 2) { BalanceCard() }
    }
}

@Composable
fun BalanceCard() {
    val cardWidth = LocalConfiguration.current.screenWidthDp.dp * .85F

    Card(
        Modifier.width(cardWidth),
        colors = CardDefaults.cardColors(containerColor = Gray)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Saldo em Conta Corrente",
                Modifier.padding(horizontal = 16.dp),
                style = C6Typo.normalSecondary
            )
            Text(
                text = "R$ *****",
                Modifier.padding(horizontal = 16.dp),
                style = C6Typo.normalSecondary
            )
            Row(
                Modifier
                    .background(Gray2)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Exibir Extrato".uppercase(),
                    style = C6Typo.normalSecondary,
                    color = Gray4
                )
                Spacer(Modifier.weight(1F))
                Icon(
                    painterResource(id = R.drawable.ic_right),
                    "",
                    modifier = Modifier.width(20.dp)
                )
            }
        }
    }
}

@Composable
fun SquareGroup(list: List<Pair<String, Int>>) {
    Box(
        Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(
            Modifier.fillMaxWidth()
                .background(Gray)
        ) {
            Squares(list)
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.background(Gray2)
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = SpaceEvenly,
                verticalAlignment = CenterVertically
            ) {
                Text(
                    "Minhas Chaves",
                    style = C6Typo.smallPrimary
                )
                Divider(
                    Modifier.width(1.dp)
                        .fillMaxHeight(),
                    thickness = 1.dp,
                    color = Gray3
                )
                Text(
                    "Meus Limites Pix",
                    style = C6Typo.smallPrimary
                )
            }
        }
    }
}

@Composable
fun Squares(list: List<Pair<String, Int>>) {
    LazyRow(
        Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = SpaceBetween
    ) {
        list.map {
            item { Square(it.first, it.second) }
        }
    }
}

@Composable
fun Square(text: String, icon: Int) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        Box(
            Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Gray),
            contentAlignment = Center
        ) {
            Icon(
                painterResource(id = icon),
                "",
                modifier = Modifier
                    .width(24.dp)
                    .align(Center)
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = text,
            style = C6Typo.smallPrimary
        )
    }
}

@Composable
fun CardMedium() {
    Card(
        Modifier
            .height(88.dp)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Gray)
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = CenterVertically
        ) {
            Icon(
                painterResource(id = R.drawable.ic_creditcard),
                "",
                modifier = Modifier.padding(start = 16.dp, end = 12.dp)
            )
            Text(
                text = "Entrega do meu cartão",
                style = C6Typo.normalPrimary
            )
            Spacer(Modifier.weight(1F))
            Icon(
                painterResource(id = R.drawable.ic_right_yellow),
                "",
                modifier = Modifier.padding(end = 16.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun CardBig() {
    Card(
        Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Gray)
    ) {
        Column(
            Modifier.fillMaxSize()
                .padding(top = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Já conhece nossa plataforma de investimentos?",
                style = C6Typo.normalPrimary
            )
            Spacer(Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "De renda fixa a variável, você encontra opções para todos os perfis.",
                style = C6Typo.normalSecondary
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Investir",
                modifier = Modifier.fillMaxWidth()
                    .height(40.dp)
                    .background(Gray2)
                    .wrapContentHeight(),
                style = C6Typo.smallSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Header(text: String, icon: Int? = null) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
        horizontalArrangement = SpaceBetween
    ) {
        Row(verticalAlignment = CenterVertically) {
            icon?.let {
                Icon(
                    painter = painterResource(id = icon),
                    "",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Text(
                text = text,
                style = C6Typo.bigPrimary
            )
        }
    }
}
