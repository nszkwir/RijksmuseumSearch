package com.nszkwir.rijksmuseumsearch.uicomponents.keyword_search

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.nszkwir.rijksmuseumsearch.R
import com.nszkwir.rijksmuseumsearch.databinding.KeywordSearchViewBinding

import java.util.*

class KeywordSearch : ConstraintLayout {
    private lateinit var binding: KeywordSearchViewBinding

    private lateinit var keywordSearchAttr: KeywordSearchAttr
    private lateinit var configuration: KeywordSearchConfiguration

    private lateinit var onSearchFunction: (String) -> Unit
    private lateinit var onErrorFunction: (String) -> Unit

    private lateinit var adapter: KeywordAdapter

    override fun onRestoreInstanceState(state: Parcelable?) {
        var viewState = state
        if (viewState is Bundle) {
            val keywords = viewState.getStringArrayList("keywords")
            if (!keywords.isNullOrEmpty()) {
                keywords.forEach {
                    adapter.addKeyword(it)
                }
            }
            viewState = viewState.getParcelable("superState")
        }
        super.onRestoreInstanceState(viewState)

        requestLayout()
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putStringArrayList("keywords", adapter.getKeywordsList() as ArrayList<String>)
        bundle.putParcelable("superState", super.onSaveInstanceState())
        return bundle
    }

    constructor(context: Context) : super(context) {
        val attrs = KeywordSearchAttr(
            hintText = DEFAULT_HINT_TEXT,
            drawableColor = DEFAULT_DRAWABLE_COLOR,
            backgroundColor = DEFAULT_BACKGROUND_COLOR
        )
        isSaveEnabled = true
        initAttrs(attrs)
    }

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        isSaveEnabled = true
        initAttrs(attributes)
    }

    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(
        context,
        attributes,
        defStyle
    ) {
        isSaveEnabled = true
        initAttrs(attributes)
    }

    private fun initAttrs(attributes: KeywordSearchAttr) {
        keywordSearchAttr = attributes
        val config = KeywordSearchConfigurationFactory.create(keywordSearchAttr)
        setupComponents(config)
    }

    private fun initAttrs(attributes: AttributeSet?) {
        keywordSearchAttr = KeywordSearchAttrParser.parse(context, attributes)
        val config = KeywordSearchConfigurationFactory.create(keywordSearchAttr)
        setupComponents(config)
    }

    private fun setupComponents(config: KeywordSearchConfiguration) {
        configuration = config
        binding = KeywordSearchViewBinding.inflate(LayoutInflater.from(context), this)

        val drawableColors = intArrayOf(
            configuration.drawableColor,
            configuration.drawableColor,
            configuration.drawableColor
        )
        val backgroundColors = intArrayOf(
            configuration.backgroundColor,
            configuration.backgroundColor,
            configuration.backgroundColor
        )
        val drawableColorStateList = ColorStateList(states, drawableColors)
        val backgroundColorStateList = ColorStateList(states, backgroundColors)

        binding.addKeywordButton.imageTintList = drawableColorStateList
        binding.searchButton.imageTintList = drawableColorStateList
        binding.seachLayout.backgroundTintList = backgroundColorStateList
        binding.searchBackground.backgroundTintList = backgroundColorStateList

        adapter = KeywordAdapter(
            drawableColorStateList,
            backgroundColorStateList
        )
        binding.keywordRecyclerview.adapter = adapter

        binding.addKeywordText.apply {
            hint = configuration.hintText
            setOnKeyListener { _, keyCode, event ->
                // TODO analize if its possible to add other keys or event to trigger this code
                return@setOnKeyListener if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    val keyword = this.text.toString()
                    this.text?.clear()

                    // TODO handle validations and errors in a proper way
                    if (validateKeyword(keyword)) {

                        if (adapter.addKeyword(keyword.trim())) {
                            true
                        } else {
                            onErrorFunction("Cant add more keywords")
                            false
                        }
                    } else {
                        onErrorFunction("Keyword not valid")
                        false
                    }
                } else {
                    false
                }
            }
        }

        binding.searchButton.setOnClickListener {
            onSearchFunction(adapter.getKeywordsString())
        }

        binding.addKeywordButton.setOnClickListener {
            val keyword = binding.addKeywordText.text.toString()
            binding.addKeywordText.text?.clear()

            if (validateKeyword(keyword)) {
                if (!adapter.addKeyword(keyword.trim())) {
                    onErrorFunction("Cant add more keywords")
                }
            } else {
                onErrorFunction("Keyword not valid")
            }
        }
        setViewId()
        requestLayout()
    }

    private fun validateKeyword(keyword: String): Boolean {
        // TODO define consistent rules
        val auxString = keyword.trim()
        return when {
            auxString.isBlank() -> false
            auxString.length < 3 -> false
            auxString.length > 40 -> false
            else -> true
        }
    }


    fun setOnSearchFunction(linkingFunction: (String) -> Unit) {
        onSearchFunction = linkingFunction
    }

    fun setOnErrorMessage(linkingFunction: (String) -> Unit) {
        onErrorFunction = linkingFunction
    }

    private fun setViewId() {
        if (id == NO_ID) {
            id = View.generateViewId()
        }
    }

    companion object {
        const val DEFAULT_HINT_TEXT = "Add keywords to search"
        var DEFAULT_BACKGROUND_COLOR = R.color.black
        var DEFAULT_DRAWABLE_COLOR = R.color.white
        val states = arrayOf(
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_enabled)
        )
    }
}
