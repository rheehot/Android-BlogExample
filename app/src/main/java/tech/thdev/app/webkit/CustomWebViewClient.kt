package tech.thdev.app.webkit

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import android.webkit.*
import tech.thdev.app.webkit.listener.OnWebViewListener

/**
 * Created by Tae-hwan on 8/4/16.
 */
class CustomWebViewClient(private val listener: OnWebViewListener?) : WebViewClient() {

    @TargetApi(Build.VERSION_CODES.M)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        Log.d("TAG", "request : " + request?.url)
        shouldOverrideUrlLoading(view, request?.url?.toString())
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        Log.d("TAG", "request : $url")
        url?.let {
            listener?.onUrlChange(it)
        }
        return super.shouldOverrideUrlLoading(view, url)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        Log.d("TAG", "url : $url")
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        Log.e("TAG", "url $url")
        url?.let {
            listener?.onFinish(it)
        }
        super.onPageFinished(view, url)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        Log.i("TAG", "error number : " + error?.errorCode)
    }

    override fun onReceivedError(
        view: WebView?,
        errorCode: Int,
        description: String?,
        failingUrl: String?
    ) {
        super.onReceivedError(view, errorCode, description, failingUrl)
        Log.i("TAG", "error number : $errorCode")
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse?
    ) {
        super.onReceivedHttpError(view, request, errorResponse)
        Log.d("TAG", "onReceivedHttpError " + errorResponse?.statusCode)
    }
}