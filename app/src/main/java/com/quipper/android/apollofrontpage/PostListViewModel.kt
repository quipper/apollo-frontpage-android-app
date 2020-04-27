package com.quipper.android.apollofrontpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toFlow
import com.quipper.android.apollofrontpage.type.Episode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostListViewModel : ViewModel() {

    private val client = ApolloClient.builder()
        .serverUrl("http://10.0.2.2:3000/graphql")
        .build()

    @ExperimentalCoroutinesApi
    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            client.query(
                HeroForEpisodeQuery(Episode.EMPIRE)
            ).toFlow()
                .collect {
                    val data = it.data()?.hero

                    data ?: return@collect

                    if (data.asDroid != null) {
                        data.asDroid.primaryFunction
                    } else if (data.asHuman != null) {
                        data.asHuman.homePlanet
                    }
                }
        }
    }
}
