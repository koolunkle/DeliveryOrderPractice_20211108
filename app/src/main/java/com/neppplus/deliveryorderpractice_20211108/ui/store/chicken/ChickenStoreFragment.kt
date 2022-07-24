package com.neppplus.deliveryorderpractice_20211108.ui.store.chicken

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.neppplus.deliveryorderpractice_20211108.model.DataProvider
import com.neppplus.deliveryorderpractice_20211108.model.StoreData
import com.neppplus.deliveryorderpractice_20211108.ui.store.info.StoreInfoActivity
import com.skydoves.landscapist.glide.GlideImage

class ChickenStoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppCompatTheme {
                    ChickenStorePreview()
                }
            }
        }
    }

    @Composable
    fun ChickenStoreDraw() {
        val chickenStoreList = rememberSaveable { DataProvider.chickenStoreList }

        LazyColumn(contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp)) {
            itemsIndexed(chickenStoreList) { index, item ->
                Box(Modifier.clickable {
                    val clickedStore = chickenStoreList[index]
                    val intent = Intent(requireContext(), StoreInfoActivity::class.java)

                    intent.putExtra("store", clickedStore)
                    startActivity(intent)
                }) {
                    ChickenStoreListItem(store = item)
                }
            }
        }
    }

    @Composable
    fun ChickenStoreListItem(store: StoreData) {
        Card(
            shape = RoundedCornerShape(corner = CornerSize(5.dp)),
            backgroundColor = Color.White,
            elevation = 3.dp,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                ChickenStoreImage(store)
                Text(text = store.name)
            }
        }
    }

    @Composable
    fun ChickenStoreImage(store: StoreData) {
        GlideImage(
            imageModel = store.logoURL,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(10.dp)
                .size(40.dp)
                .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
        )
    }

    @Preview
    @Composable
    fun ChickenStorePreview() {
        AppCompatTheme {
            ChickenStoreDraw()
        }
    }

}