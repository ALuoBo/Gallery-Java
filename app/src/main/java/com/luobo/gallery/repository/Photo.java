package com.luobo.gallery.repository;

import java.util.List;

public class Photo {
    /**
     * total : 28417
     * totalHits : 500
     * hits : [{"id":3063284,"pageURL":"https://pixabay.com/photos/rose-flower-petal-floral-noble-3063284/","type":"photo","tags":"rose, flower, petal","previewURL":"https://cdn.pixabay.com/photo/2018/01/05/16/24/rose-3063284_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/55e0d340485aa814f1dc846096293f7f1637d9e5504c704c7c267ed09e45c15f_640.jpg","webformatWidth":640,"webformatHeight":426,"largeImageURL":"https://pixabay.com/get/55e0d340485aa814f6da8c7dda793676143cd6e253526c48702672d19344cd5cbf_1280.jpg","imageWidth":6000,"imageHeight":4000,"imageSize":3574625,"views":778318,"downloads":491662,"favorites":1029,"likes":1182,"comments":267,"user_id":1564471,"user":"anncapictures","userImageURL":"https://cdn.pixabay.com/user/2015/11/27/06-58-54-609_250x250.jpg"}]
     */

    private int total;
    private int totalHits;
    private List<HitsBean> hits;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<HitsBean> getHits() {
        return hits;
    }

    public void setHits(List<HitsBean> hits) {
        this.hits = hits;
    }

    public static class HitsBean {
        /**
         * id : 3063284
         * pageURL : https://pixabay.com/photos/rose-flower-petal-floral-noble-3063284/
         * type : photo
         * tags : rose, flower, petal
         * previewURL : https://cdn.pixabay.com/photo/2018/01/05/16/24/rose-3063284_150.jpg
         * previewWidth : 150
         * previewHeight : 99
         * webformatURL : https://pixabay.com/get/55e0d340485aa814f1dc846096293f7f1637d9e5504c704c7c267ed09e45c15f_640.jpg
         * webformatWidth : 640
         * webformatHeight : 426
         * largeImageURL : https://pixabay.com/get/55e0d340485aa814f6da8c7dda793676143cd6e253526c48702672d19344cd5cbf_1280.jpg
         * imageWidth : 6000
         * imageHeight : 4000
         * imageSize : 3574625
         * views : 778318
         * downloads : 491662
         * favorites : 1029
         * likes : 1182
         * comments : 267
         * user_id : 1564471
         * user : anncapictures
         * userImageURL : https://cdn.pixabay.com/user/2015/11/27/06-58-54-609_250x250.jpg
         */

        private int id;
        private String pageURL;
        private String previewURL;
        private int previewWidth;
        private int previewHeight;
        private String webformatURL;
        private String largeImageURL;
        private int views;
        private int downloads;
        private int favorites;
        private int likes;
        private int comments;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPageURL() {
            return pageURL;
        }

        public void setPageURL(String pageURL) {
            this.pageURL = pageURL;
        }

        public String getPreviewURL() {
            return previewURL;
        }

        public void setPreviewURL(String previewURL) {
            this.previewURL = previewURL;
        }

        public int getPreviewWidth() {
            return previewWidth;
        }

        public void setPreviewWidth(int previewWidth) {
            this.previewWidth = previewWidth;
        }

        public int getPreviewHeight() {
            return previewHeight;
        }

        public void setPreviewHeight(int previewHeight) {
            this.previewHeight = previewHeight;
        }

        public String getWebformatURL() {
            return webformatURL;
        }

        public void setWebformatURL(String webformatURL) {
            this.webformatURL = webformatURL;
        }


        public String getLargeImageURL() {
            return largeImageURL;
        }

        public void setLargeImageURL(String largeImageURL) {
            this.largeImageURL = largeImageURL;
        }


        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getDownloads() {
            return downloads;
        }

        public void setDownloads(int downloads) {
            this.downloads = downloads;
        }

        public int getFavorites() {
            return favorites;
        }

        public void setFavorites(int favorites) {
            this.favorites = favorites;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

    }
}