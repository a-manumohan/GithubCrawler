package com.mn.githubrepository.usecase;

import com.mn.githubrepository.api.GithubApi;
import com.mn.githubrepository.data.DataStore;
import com.mn.githubrepository.data.GithubRepo;

import io.reactivex.Flowable;

public class GetRepos {
    private final GithubApi githubApi;
    private final DataStore<GithubRepo> dataStore;
    private final String username;

    public GetRepos(GithubApi githubApi, DataStore<GithubRepo> dataStore, String username) {
        this.githubApi = githubApi;
        this.dataStore = dataStore;
        this.username = username;
    }

    public Flowable<Object> get(int page, int perPage) {
        //todo persist locally.
        //todo if the call fails and if the page is 0, read from local store.
        return githubApi.getRepos(username, page, perPage);
    }
}
