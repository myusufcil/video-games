package com.base.games.ui.pages.detailgames

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.lifecycle.Observer
import com.base.component.GamesRecyclerviewAdapter
import com.base.component.ui.detailgames.DetailGamesDTO
import com.base.core.extensions.setup
import com.base.core.networking.DataFetchResult
import com.base.data.database.model.FavGamesDTO
import com.base.data.response.GamesDetailResponse
import com.base.games.ui.base.fragment.BaseDataFetchFragment
import com.base.games.R
import com.base.games.ui.pages.detailgames.viewmodel.DetailGamesFragmentViewModel
import kotlinx.android.synthetic.main.fragment_detail_games.*
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject


class DetailGamesFragment : BaseDataFetchFragment<DetailGamesFragmentViewModel>() {

    @Inject
    lateinit var adapter: GamesRecyclerviewAdapter

    override val viewModelClass: Class<DetailGamesFragmentViewModel> =
        DetailGamesFragmentViewModel::class.java

    override val layoutViewRes: Int = R.layout.fragment_detail_games
    var gameId = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcGamesDetail.setup(adapter = adapter.getAdapter())
        var count = 1
        adapter.getAdapter().itemClickListener = { _item, _position ->
            when (_item) {
                is DetailGamesDTO -> {
                    if (count == 1) {
                        viewModel.insertFav(
                            FavGamesDTO(
                                id = 0,
                                releasedDate = _item.releasedDate,
                                gameId = _item.gameId,
                                description = _item.description,
                                metacriticRate = _item.metacriticRate,
                                photoPath = _item.imagePath,
                                gameName = _item.nameOfGame
                            )
                        )
                        Toast.makeText(context, "Favoriye Eklendi", Toast.LENGTH_LONG).show()
                        count = 0
                    } /*else if (count == 0) {
                        viewModel.deleteById(_item.gameId?.toInt())
                        Toast.makeText(context, "Favoriyeden Çıkarıldı.", Toast.LENGTH_LONG).show()
                        count = 1
                    }*/
                }
            }
        }
        bindObserver()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setupNewsDetailWebView(htmlData: String) {
        val webView = WebView(requireActivity())
        webView.loadUrl("file:///android_asset/index.html")
        webView.addJavascriptInterface(WebAppInterface(), "AndroidInterface")
        val webSettings = webView.settings
        webSettings.domStorageEnabled = true
        webSettings.javaScriptEnabled = true
        webView.webViewClient = NewDetailWebViewClient(htmlData)
        webView.webChromeClient = NewsDetailWebChromeClient()
    }

    inner class WebAppInterface {
        @JavascriptInterface
        fun htmlResult(result: String?) {
            activity!!.runOnUiThread {
                Timber.d(result.toString())
                viewModel.parseHtmlData(result)
            }
        }
    }

    private class NewDetailWebViewClient(var htmlData: String) : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            view.loadUrl("javascript:alert(htmlParseAndroid('$htmlData'))")
        }
    }

    private class NewsDetailWebChromeClient : WebChromeClient() {
        override fun onJsAlert(
            view: WebView?, url: String?, message: String?, result: JsResult?
        ): Boolean {
            Timber.d(message)
            result?.confirm()
            return true
        }
    }


    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        args.let {
            gameId = it?.getInt("idGameDetail")!!
        }
    }

    private fun bindObserver() {
        viewModel.getDetail(gameId)
        viewModel.gameDetailPublishSubject.subscribe {
            adapter.getAdapter().updateAllItems(it)
        }
        viewModel.getGameDetailResult.observe(
            viewLifecycleOwner,
            Observer<DataFetchResult<GamesDetailResponse>> {
                when (it) {
                    is DataFetchResult.Progress -> {
                    }
                    is DataFetchResult.Failure -> {
                    }
                    is DataFetchResult.Success -> {
                        it.data.let {
                            val jsonData = JSONObject().apply {
                                put("HtmlText", it.description)
                                put("ContentId", it.id)
                            }

                            val base64Data = Base64.encodeToString(
                                jsonData.toString().toByteArray(),
                                Base64.DEFAULT
                            )
                            setupNewsDetailWebView(base64Data)
                        }
                    }
                }
            })
    }

    companion object {

        @JvmStatic
        fun newInstance(bundle: Bundle) =
            DetailGamesFragment().apply {
                arguments = bundle
            }
    }
}