package brain.function;

import android.net.Uri;

/**
 * Copyright 2016(c) Comet Corporation.
 * Created by asus1 on 2016/1/22.
 * 书的简化类
 */
public class SplitBook {

    private Uri bookUri;

    public SplitBook(Uri bookUri) {
        this.bookUri = bookUri;
    }

    public SplitBook() {
    }

    public void setBookUri(Uri bookUri) {
        this.bookUri = bookUri;
    }
}
