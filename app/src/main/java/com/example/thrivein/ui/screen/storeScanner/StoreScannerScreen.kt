package com.example.thrivein.ui.screen.storeScanner

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.thrivein.R
import com.example.thrivein.ui.component.button.AddPhotoButton
import com.example.thrivein.ui.component.button.CameraButton
import com.example.thrivein.ui.component.button.SwitchButton
import com.example.thrivein.ui.component.button.ThriveInButton
import com.example.thrivein.ui.theme.Primary
import com.example.thrivein.utils.CameraUIAction
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.material.dialog.MaterialAlertDialogBuilder

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun StoreScannerScreen(
    modifier: Modifier = Modifier,
    navigateToScoreAndAdvice: (String) -> Unit,
    navigateToHome: () -> Unit,
) {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    SideEffect {
        if (!cameraPermissionState.hasPermission) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    if (cameraPermissionState.hasPermission) {
        CameraView(
            modifier = modifier,
            onImageCaptured = { uri, fromGallery ->

            },
            onError = { imageCaptureException ->

            },
            navigateToScoreAndAdvice = navigateToScoreAndAdvice,
            navigateToHome = navigateToHome,
        )
    }


}

@Preview(showBackground = true)
@Composable
fun StoreScannerScreenPreview() {
    StoreScannerScreen(
        navigateToHome = {},
        navigateToScoreAndAdvice = {},
    )
}
@Preview(showBackground = true)
@Composable
fun CameraPreviewViewTest() {
    val imageCapture = ImageCapture.Builder().build()
    CameraPreviewView(
        navigateToHome = {},
        cameraUIAction = {},
        lensFacing = 1,
        imageCapture = imageCapture
    )
}
@Composable
fun CameraView(
    modifier: Modifier = Modifier,
    onImageCaptured: (Uri, Boolean) -> Unit,
    onError: (ImageCaptureException) -> Unit,
    navigateToScoreAndAdvice: (String) -> Unit,
    navigateToHome: () -> Unit,
) {

    val context = LocalContext.current
    var lensFacing by remember { mutableIntStateOf(CameraSelector.LENS_FACING_BACK) }
    val imageCapture: ImageCapture = remember {
        ImageCapture.Builder().build()
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) onImageCaptured(uri, true)
    }

    CameraPreviewView(
        modifier = modifier,
        imageCapture,
        lensFacing,
        navigateToHome = navigateToHome,
        cameraUIAction = { cameraUIAction ->
            when (cameraUIAction) {
                is CameraUIAction.OnCameraClick -> {
//                uncomment this code to use the camera
//                imageCapture.takePicture(context, lensFacing, onImageCaptured, onError)
                    navigateToScoreAndAdvice("1")

                }

                is CameraUIAction.OnSwitchCameraClick -> {
                    lensFacing =
                        if (lensFacing == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT
                        else
                            CameraSelector.LENS_FACING_BACK
                }

                is CameraUIAction.OnGalleryViewClick -> {
                    galleryLauncher.launch("image/*")
                }
            }
        }
    )
}

@SuppressLint("RestrictedApi")
@Composable
private fun CameraPreviewView(
    modifier: Modifier = Modifier,
    imageCapture: ImageCapture,
    lensFacing: Int = CameraSelector.LENS_FACING_BACK,
    cameraUIAction: (CameraUIAction) -> Unit,
    navigateToHome: () -> Unit,
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    val preview = androidx.camera.core.Preview.Builder().build()
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()

    val previewView = remember { PreviewView(context) }
    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }


    val transition = rememberInfiniteTransition(label = "")
    val yPosition by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 5000
                0f at 0 with LinearEasing
                1f at 5000 with LinearEasing
            },
            repeatMode = RepeatMode.Reverse
        ),
        label = "",
    )

    var showInfo by remember { mutableStateOf(false) }


    Box(modifier = modifier.fillMaxSize()) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize()) {

        }

        ScanningLaser(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (yPosition * screenHeight).dp),
        )

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row {
                ThriveInButton(
                    modifier = Modifier
                        .offset(y = (-550).dp)
                        .padding(50.dp)
                        .scale(0.8f),
                    onClick = {
                        navigateToHome()
                    },
                    label = stringResource(id = R.string.go_to_home),
                    isOutline = true
                )

                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = stringResource(id = R.string.info),
                    tint = Primary,
                    modifier = Modifier
                        .scale(1f)
                        .clickable {
                            showInfo = true
                        }
                )

                //Dialog
//                if (showInfo) {
//                    LaunchedEffect(showInfo) {
//                        val context = this
//                        MaterialAlertDialogBuilder(
//                            context = this,
////                            onDismissRequest = {
////                                showInfo = false
////                            }
//                        ) {
//                            setTitle(text = "Dialog Title")
//                            message(text = "Dialog Content")
//                            positiveButton(text = "OK")
//                        }.show()
//                    }
//                }
            }
            CameraControls(cameraUIAction)
        }


    }
}

@Composable
fun CameraControls(cameraUIAction: (CameraUIAction) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        SwitchButton() {
            cameraUIAction(CameraUIAction.OnSwitchCameraClick)
        }
        CameraButton() {
            cameraUIAction(CameraUIAction.OnCameraClick)
        }
        AddPhotoButton() {
            cameraUIAction(CameraUIAction.OnGalleryViewClick)
        }

    }
}

@Composable
fun ScanningLaser(
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Red)
            .height(6.dp)
            .shadow(
                elevation = 2.dp,
                ambientColor = Color.Red,
                shape = MaterialTheme.shapes.medium,
                clip = false,
                spotColor = Color.Red,
            )
    ) {

    }
}