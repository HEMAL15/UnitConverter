package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter() {

    var input by remember {mutableStateOf("")}
    var output by remember {mutableStateOf("")}
    var inun by remember {mutableStateOf("Meter")}
    var outun by remember {mutableStateOf("Meter")}
     var iexp by remember { mutableStateOf(false) }//both these used for opening and closing the drop down menu
    var oexp by remember { mutableStateOf(false) }
    val conversionfactor = remember { mutableStateOf(1.00) }
    val oconversionfactor = remember { mutableStateOf(1.00) }


    fun converter()
    {
        var inputtodouble=input.toDoubleOrNull() ?: 0.0
        val op = (inputtodouble * conversionfactor.value * 100.0 / oconversionfactor.value).roundToInt() / 100.0
        output=op.toString()
    }


    Column (modifier=Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("UNIT CONVERETER",style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier=Modifier.height(16.dp))
        OutlinedTextField(value = input, onValueChange = {
            input= it
            converter()
        }, label = {Text("Input your value")})
        Spacer(modifier=Modifier.height(16.dp))
        Row {
                 //input box
            Box {
                Button(onClick = { iexp=true }) {
                    Text(inun)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "ARROW DOWN")
                }
                DropdownMenu(expanded = iexp, onDismissRequest = { iexp=false }) {
                    DropdownMenuItem(text = { Text("Centimeter") }, onClick = {
                        iexp=false
                        inun="Centimeter"
                        conversionfactor.value=0.1
                        converter()
                    })
                    DropdownMenuItem(text = { Text("Meter") }, onClick = {
                        iexp=false
                        inun="Meter"
                        conversionfactor.value=1.0
                        converter()})
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        iexp=false
                    inun="Feet"
                        conversionfactor.value=0.3048
                        converter()})
                    DropdownMenuItem(text = { Text("Inch") }, onClick = {
                        iexp=false
                    inun="Inches"
                        conversionfactor.value=0.001
                        converter()})
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            //output box
                Box {
                    Button(onClick = { oexp=true }) {
                        Text(outun)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "ARROW DOWN")

                    }
                    DropdownMenu(expanded = oexp, onDismissRequest = { oexp=false }) {
                        DropdownMenuItem(text = { Text("Centimeter") }, onClick = { oexp=false
                            outun="Centimeter"
                            oconversionfactor.value=0.01
                            converter()

                        })
                        DropdownMenuItem(text = { Text("Meter") }, onClick = { oexp=false
                        outun="Meter"
                            oconversionfactor.value=1.0
                            converter()})
                        DropdownMenuItem(text = { Text("Feet") }, onClick = { oexp=false
                        outun="Feet"
                            oconversionfactor.value=0.3048
                            converter()})
                        DropdownMenuItem(text = { Text("Inch") }, onClick = { oexp=false
                        outun="Inch"
                            oconversionfactor.value=0.001
                            converter()})
                    }
                }


        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $output", style = MaterialTheme.typography.headlineMedium)
    }


}
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}

