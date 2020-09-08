package com.luobo.tree.repository;

import java.util.List;

public class Photo {

    /**
     * total : 28373
     * totalHits : 500
     * hits : [{"id":3063284,"pageURL":"https://pixabay.com/photos/rose-flower-petal-floral-noble-3063284/","type":"photo","tags":"rose, flower, petal","previewURL":"https://cdn.pixabay.com/photo/2018/01/05/16/24/rose-3063284_150.jpg","previewWidth":150,"previewHeight":99,"largeImageURL":"https://pixabay.com/get/55e0d340485aa814f6da8c7dda793676143cd6e253526c48702672d4974bcc51bb_1280.jpg","imageWidth":6000,"imageHeight":4000,"favorites":1026,"likes":1179}]
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
         * largeImageURL : https://pixabay.com/get/55e0d340485aa814f6da8c7dda793676143cd6e253526c48702672d4974bcc51bb_1280.jpg
         * imageWidth : 6000
         * imageHeight : 4000
         * favorites : 1026
         * likes : 1179
         */

        private int id;
        private String pageURL;
        private String type;
        private String tags;
        private String previewURL;
        private int previewWidth;
        private int previewHeight;
        private String largeImageURL;
        private int imageWidth;
        private int imageHeight;
        private int favorites;
        private int likes;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
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

        public String getLargeImageURL() {
            return largeImageURL;
        }

        public void setLargeImageURL(String largeImageURL) {
            this.largeImageURL = largeImageURL;
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
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
    }
}