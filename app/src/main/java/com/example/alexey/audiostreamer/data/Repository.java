package com.example.alexey.audiostreamer.data;

import com.example.alexey.audiostreamer.data.local.LocalRepository;
import com.example.alexey.audiostreamer.data.remote.RemoteRepository;

public class Repository implements DataSource {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    public Repository(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }
}
