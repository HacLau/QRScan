package com.liu.qrscan.util;

import android.content.Context;
import android.graphics.Bitmap;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.filter.GPUImage3x3ConvolutionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImage3x3TextureSamplingFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageAddBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageAlphaBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBilateralBlurFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBoxBlurFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBulgeDistortionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageCGAColorspaceFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageChromaKeyBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorBalanceFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorBurnBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorDodgeBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorInvertFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorMatrixFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageCrosshatchFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDarkenBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDifferenceBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDilationFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDirectionalSobelEdgeDetectionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDissolveBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDivideBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageEmbossFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageExclusionBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageExposureFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFalseColorFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGammaFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGaussianBlurFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGlassSphereFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHalftoneFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHardLightBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHazeFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHighlightShadowFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHueBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHueFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageKuwaharaFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLaplacianFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLevelsFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLightenBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLinearBurnBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLookupFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLuminanceFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLuminanceThresholdFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLuminosityBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageMonochromeFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageMultiplyBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageNonMaximumSuppressionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageNormalBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageOpacityFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageOverlayBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImagePixelationFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImagePosterizeFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageRGBDilationFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageRGBFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSaturationBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageScreenBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSepiaToneFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSharpenFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSketchFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSmoothToonFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSobelEdgeDetectionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSobelThresholdFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSoftLightBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSolarizeFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSourceOverBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSphereRefractionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSubtractBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSwirlFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageThresholdEdgeDetectionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageToneCurveFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageToonFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageTransformFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageVibranceFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageVignetteFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageWeakPixelInclusionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageWhiteBalanceFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageZoomBlurFilter;

public class GPUImageTools {

    private static GPUImageFilter filter;

    //饱和度、亮度等参数指数
    private static int count;

    /**
     * 获取过滤器
     * @param GPUFlag
     * @return 滤镜类型
     */
    public static GPUImageFilter getFilter(int GPUFlag){
        switch (GPUFlag){
            case 1:
                filter = new GPUImage3x3ConvolutionFilter();
                break;
            case 2:
                filter = new GPUImage3x3TextureSamplingFilter();
                break;
            case 3:
                filter = new GPUImageAddBlendFilter();
                break;
            case 4:
                filter = new GPUImageAlphaBlendFilter();
                break;
            case 5:
                filter = new GPUImageBilateralBlurFilter();
                break;
            case 6:
                filter = new GPUImageBoxBlurFilter();
                break;
            case 7:
                filter = new GPUImageBrightnessFilter();
                break;
            case 8:
                filter = new GPUImageBulgeDistortionFilter();
                break;
            case 9:
                filter = new GPUImageCGAColorspaceFilter();
                break;
            case 10:
                filter = new GPUImageChromaKeyBlendFilter();
                break;
            case 11:
                filter = new GPUImageColorBalanceFilter();
                break;
            case 12:
                filter = new GPUImageColorBlendFilter();
                break;
            case 13:
                filter = new GPUImageColorBurnBlendFilter();
                break;
            case 14:
                filter = new GPUImageColorDodgeBlendFilter();
                break;
            case 15:
                filter = new GPUImageColorInvertFilter();
                break;
            case 16:
                filter = new GPUImageColorMatrixFilter();
                break;
            case 17:
                filter = new GPUImageContrastFilter();
                break;
            case 18:
                filter = new GPUImageCrosshatchFilter();
                break;
            case 19:
                filter = new GPUImageDarkenBlendFilter();
                break;
            case 20:
                filter = new GPUImageDifferenceBlendFilter();
                break;
            case 21:
                filter = new GPUImageDilationFilter();
                break;
            case 22:
                filter = new GPUImageDirectionalSobelEdgeDetectionFilter();
                break;
            case 23:
                filter = new GPUImageDissolveBlendFilter();
                break;
            case 24:
                filter = new GPUImageDivideBlendFilter();
                break;
            case 25:
                filter = new GPUImageEmbossFilter();
                break;
            case 26:
                filter = new GPUImageExclusionBlendFilter();
                break;
            case 27:
                filter = new GPUImageExposureFilter();
                break;
            case 28:
                filter = new GPUImageFalseColorFilter();
                break;
            case 29:
                filter = new GPUImageFilter();
                break;
            case 30:
                filter = new GPUImageGammaFilter();
                break;
            case 31:
                filter = new GPUImageGaussianBlurFilter();
                break;
            case 32:
                filter = new GPUImageGlassSphereFilter();
                break;
//            case 33:
//                filter = new GPUImageGrayscaleFilter();
//                break;
            case 34:
                filter = new GPUImageHalftoneFilter();
                break;
            case 35:
                filter = new GPUImageHardLightBlendFilter();
                break;
            case 36:
                filter = new GPUImageHazeFilter();
                break;
            case 37:
                filter = new GPUImageHighlightShadowFilter();
                break;
            case 38:
                filter = new GPUImageHueBlendFilter();
                break;
            case 39:
                filter = new GPUImageHueFilter();
                break;
            case 40:
                filter = new GPUImageKuwaharaFilter();
                break;
            case 41:
                filter = new GPUImageLaplacianFilter();
                break;
            case 42:
                filter = new GPUImageLevelsFilter();
                break;
            case 43:
                filter = new GPUImageLightenBlendFilter();
                break;
            case 44:
                filter = new GPUImageLinearBurnBlendFilter();
                break;
            case 45:
                filter = new GPUImageLookupFilter();
                break;
            case 46:
                filter = new GPUImageLuminanceFilter();
                break;
            case 47:
                filter = new GPUImageLuminanceThresholdFilter();
                break;
            case 48:
                filter = new GPUImageLuminosityBlendFilter();
                break;
//            case 49:
//                filter = new GPUImageMixBlendFilter();
//                break;
            case 50:
                filter = new GPUImageMonochromeFilter();
                break;
            case 51:
                filter = new GPUImageMultiplyBlendFilter();
                break;
            case 52:
                filter = new GPUImageNonMaximumSuppressionFilter();
                break;
            case 53:
                filter = new GPUImageNormalBlendFilter();
                break;
            case 54:
                filter = new GPUImageOpacityFilter();
                break;
            case 55:
                filter = new GPUImageOverlayBlendFilter();
                break;
            case 56:
                filter = new GPUImagePixelationFilter();
                break;
            case 57:
                filter = new GPUImagePosterizeFilter();
                break;
            case 58:
                filter = new GPUImageRGBDilationFilter();
                break;
            case 59:
                filter = new GPUImageRGBFilter();
                break;
            case 60:
                filter = new GPUImageSaturationBlendFilter();
                break;
            case 61:
                filter = new GPUImageSaturationFilter();
                break;
            case 62:
                filter = new GPUImageScreenBlendFilter();
                break;
            case 63:
                filter = new GPUImageSepiaToneFilter();
                break;
            case 64:
                filter = new GPUImageSharpenFilter();
                break;
            case 65:
                filter = new GPUImageSketchFilter();
                break;
            case 66:
                filter = new GPUImageSmoothToonFilter();
                break;
            case 67:
                filter = new GPUImageSobelEdgeDetectionFilter();
                break;
            case 68:
                filter = new GPUImageSobelThresholdFilter();
                break;
            case 69:
                filter = new GPUImageSoftLightBlendFilter();
                break;
            case 70:
                filter = new GPUImageSolarizeFilter();
                break;
            case 71:
                filter = new GPUImageSourceOverBlendFilter();
                break;
            case 72:
                filter = new GPUImageSphereRefractionFilter();
                break;
            case 73:
                filter = new GPUImageSubtractBlendFilter();
                break;
            case 74:
                filter = new GPUImageSwirlFilter();
                break;
            case 75:
                filter = new GPUImageThresholdEdgeDetectionFilter();
                break;
            case 76:
                filter = new GPUImageToneCurveFilter();
                break;
            case 77:
                filter = new GPUImageToonFilter();
                break;
            case 78:
                filter = new GPUImageTransformFilter();
                break;
//            case 79:
//                filter = new GPUImageTwoInputFilter();
//                break;
//            case 80:
//                filter = new GPUImageTwoPassFilter();
//                break;
//            case 81:
//                filter = new GPUImageTwoPassTextureSamplingFilter();
//                break;
            case 82:
                filter = new GPUImageVibranceFilter();
                break;
            case 83:
                filter = new GPUImageVignetteFilter();
                break;
            case 84:
                filter = new GPUImageWeakPixelInclusionFilter();
                break;
            case 85:
                filter = new GPUImageWhiteBalanceFilter();
                break;
            case 86:
                filter = new GPUImageZoomBlurFilter();
                break;
            default:
                filter = new GPUImageGrayscaleFilter();
        }
        return filter;
    }

    public static Bitmap getGPUImage(Context context,Bitmap srcBitmap, int position){
        if (srcBitmap == null)
            return null;
        // 使用GPUImage处理图像
        GPUImage gpuImage = new GPUImage(context);
        gpuImage.setImage(srcBitmap);
        gpuImage.setFilter(getFilter(position));
        srcBitmap = gpuImage.getBitmapWithFilterApplied();
        return srcBitmap;
    }

    //调整饱和度、亮度等
    public static void changeSaturation(int curCount){
        GPUImageTools.count = curCount;
    }
}