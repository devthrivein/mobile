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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.thrivein.ui.component.button.AddPhotoButton
import com.example.thrivein.ui.component.button.CameraButton
import com.example.thrivein.ui.component.button.TrashbinButton
import com.example.thrivein.utils.CameraUIAction
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

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
        lensFacing
    ) { cameraUIAction ->
        when (cameraUIAction) {
            is CameraUIAction.OnCameraClick -> {
//                uncomment this code to use the camera
//                imageCapture.takePicture(context, lensFacing, onImageCaptured, onError)
                navigateToHome()

            }

            is CameraUIAction.OnSwitchCameraClick -> {
                lensFacing =
                    if (lensFacing == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT
                    else
                        CameraSelector.LENS_FACING_BACK
            }

            is CameraUIAction.OnGalleryViewClick -> {
//                uncomment this code to use the gallery
//                galleryLauncher.launch("image/*")
                navigateToScoreAndAdvice("1")
            }
        }
    }
}

@SuppressLint("RestrictedApi")
@Composable
private fun CameraPreviewView(
    modifier: Modifier = Modifier,
    imageCapture: ImageCapture,
    lensFacing: Int = CameraSelector.LENS_FACING_BACK,
    cameraUIAction: (CameraUIAction) -> Unit,
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

        TrashbinButton() {
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