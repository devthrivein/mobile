package com.example.thrivein.ui.component.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThriveInInputText(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    placeholder: String = "",
    onChange: (String) -> Unit,
    isObsecure: Boolean? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
) {

    var isShowPassword by remember { mutableStateOf(false) }

    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            placeholder = {
                Text(
                    placeholder,
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray)
                )
            },
            trailingIcon = {
                if (isObsecure != null && isObsecure) {
                    IconButton(onClick = {
                        isShowPassword = !isShowPassword
                    }) {
                        Icon(
                            painter = painterResource(id = if (!isShowPassword) R.drawable.visible else R.drawable.visible_off),
                            contentDescription = "Toggle Password Visibility"
                        )
                    }
                }

            },
            visualTransformation = if (isObsecure != null && isObsecure) {
                if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation()
            } else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = Primary,
                focusedLabelColor = Primary,
                cursorColor = Primary
            )
        )

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ThriveInInputTextPreview() {
    ThriveInInputText(label = "Test", value = "", onChange = {

    }, isObsecure = true)
}