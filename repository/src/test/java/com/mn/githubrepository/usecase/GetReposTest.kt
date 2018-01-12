package com.mn.githubrepository.usecase

import com.mn.githubrepository.api.GithubApi
import com.mn.githubrepository.data.DataStore
import com.mn.githubrepository.data.GithubRepo
import com.mn.githubrepository.thread.ThreadTransformer
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetReposTest {


    @Mock
    lateinit var githubApi: GithubApi

    @Mock
    lateinit var dataStore: DataStore

    private val username: String = "JakeWharton"

    @Mock
    lateinit var threadTransformer: ThreadTransformer

    @Before
    fun setUp() {
        `when`(threadTransformer.apply<List<GithubRepo>>()).thenReturn(FlowableTransformer { o -> o })
    }

    @Test
    fun `fetch shouldn't emit when local store is empty and network error is thrown`() {
        val error = IllegalStateException()
        `when`(githubApi.getRepos(username, 1, 15))
                .thenReturn(Flowable.error(error))
        `when`(dataStore.getItems()).thenReturn(listOf())
        val getRepos = GetRepos(githubApi, dataStore, username, threadTransformer)

        val testSubscriber = TestSubscriber<List<GithubRepo>>()

        getRepos.fetch(1, 15)
                .subscribe(testSubscriber)

        testSubscriber.assertError(IllegalStateException::class.java)
    }

    @Test
    fun `fetch should emit when local store is not empty and network error is thrown`() {
        val error = IllegalStateException()
        `when`(githubApi.getRepos(username, 1, 15))
                .thenReturn(Flowable.error(error))

        val dummyItems = localDummyItems()
        `when`(dataStore.getItems()).thenReturn(dummyItems)

        val getRepos = GetRepos(githubApi, dataStore, username, threadTransformer)

        val testSubscriber = TestSubscriber<List<GithubRepo>>()

        getRepos.fetch(1, 15)
                .subscribe(testSubscriber)

        testSubscriber.assertValueCount(1)
        testSubscriber.assertValues(dummyItems)
        testSubscriber.assertError(IllegalStateException::class.java)
    }

    @Test
    fun `fetch should emit local store for page 1`() {
        val localDummyItems = localDummyItems()
        val remoteDummyItems = remoteDummyItems()

        `when`(githubApi.getRepos(username, 1, 15))
                .thenReturn(Flowable.just(remoteDummyItems))
        `when`(dataStore.getItems()).thenReturn(localDummyItems)
        val getRepos = GetRepos(githubApi, dataStore, username, threadTransformer)

        val testSubscriber = TestSubscriber<List<GithubRepo>>()

        getRepos.fetch(1, 15)
                .subscribe(testSubscriber)

        testSubscriber.assertValueCount(2)
        testSubscriber.assertValues(localDummyItems, remoteDummyItems)
        testSubscriber.assertNoErrors()
    }

    @Test
    fun `fetch should not emit local store for any page except 1`() {
        val localDummyItems = localDummyItems()
        val remoteDummyItems = remoteDummyItems()

        `when`(githubApi.getRepos(username, 2, 15))
                .thenReturn(Flowable.just(remoteDummyItems))
        `when`(dataStore.getItems()).thenReturn(localDummyItems)
        val getRepos = GetRepos(githubApi, dataStore, username, threadTransformer)

        val testSubscriber = TestSubscriber<List<GithubRepo>>()

        getRepos.fetch(2, 15)
                .subscribe(testSubscriber)

        testSubscriber.assertValueCount(1)
        testSubscriber.assertValues(remoteDummyItems)
        testSubscriber.assertNoErrors()
    }

    @Test
    fun `fetch should persist remote data to local storage`() {
        val localDummyItems = localDummyItems()
        val remoteDummyItems = remoteDummyItems()

        `when`(githubApi.getRepos(username, 1, 15))
                .thenReturn(Flowable.just(remoteDummyItems))
        `when`(dataStore.getItems()).thenReturn(localDummyItems)
        val getRepos = GetRepos(githubApi, dataStore, username, threadTransformer)
        val testSubscriber = TestSubscriber<List<GithubRepo>>()

        getRepos.fetch(1, 15)
                .subscribe(testSubscriber)

        verify(dataStore, times(remoteDummyItems.size)).addItem(any())
    }

    private fun localDummyItems(): List<GithubRepo> {
        return listOf(
                GithubRepo("name1", "fullname1", "url1"),
                GithubRepo("name2", "fullname2", "url2"),
                GithubRepo("name3", "fullname3", "url3"),
                GithubRepo("name4", "fullname4", "url4"),
                GithubRepo("name5", "fullname5", "url5")
        )
    }

    private fun remoteDummyItems(): List<GithubRepo> {
        return listOf(
                GithubRepo("name1", "fullname1", "url1"),
                GithubRepo("name2", "fullname2", "url2"),
                GithubRepo("name3", "fullname3", "url3"),
                GithubRepo("name4", "fullname4", "url4")
        )
    }

    //utility methods to tackle kotlin null problems with mockito
    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T
}