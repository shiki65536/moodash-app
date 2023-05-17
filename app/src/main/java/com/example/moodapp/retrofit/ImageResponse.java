package com.example.moodapp.retrofit;

import java.util.Date;
import java.util.List;

public class ImageResponse {
    private int total;
    private int totalPages;
    private List<Results> results;

    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getTotalPages() {
        return totalPages;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
    public List<Results> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "total=" + total +
                ", totalPages=" + totalPages +
                ", results=" + results +
                '}';
    }

    public static class Results {

        private String id;
        private String slug;
        private Date createdAt;
        private Date updatedAt;
        private Date promotedAt;
        private int width;
        private int height;
        private String color;
        private String blurHash;
        private String description;
        private String altDescription;
        private Urls urls;
        private Links links;
        private int likes;
        private boolean likedByUser;
        private List<String> currentUserCollections;
        private String sponsorship;
        private TopicSubmissions topicSubmissions;
        private User user;
        private List<Tags> tags;
        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }
        public String getSlug() {
            return slug;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }
        public Date getCreatedAt() {
            return createdAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }
        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setPromotedAt(Date promotedAt) {
            this.promotedAt = promotedAt;
        }
        public Date getPromotedAt() {
            return promotedAt;
        }

        public void setWidth(int width) {
            this.width = width;
        }
        public int getWidth() {
            return width;
        }

        public void setHeight(int height) {
            this.height = height;
        }
        public int getHeight() {
            return height;
        }

        public void setColor(String color) {
            this.color = color;
        }
        public String getColor() {
            return color;
        }

        public void setBlurHash(String blurHash) {
            this.blurHash = blurHash;
        }
        public String getBlurHash() {
            return blurHash;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        public String getDescription() {
            return description;
        }

        public void setAltDescription(String altDescription) {
            this.altDescription = altDescription;
        }
        public String getAltDescription() {
            return altDescription;
        }

        public void setUrls(Urls urls) {
            this.urls = urls;
        }
        public Urls getUrls() {
            return urls;
        }

        public void setLinks(Links links) {
            this.links = links;
        }
        public Links getLinks() {
            return links;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }
        public int getLikes() {
            return likes;
        }

        public void setLikedByUser(boolean likedByUser) {
            this.likedByUser = likedByUser;
        }
        public boolean getLikedByUser() {
            return likedByUser;
        }

        public void setCurrentUserCollections(List<String> currentUserCollections) {
            this.currentUserCollections = currentUserCollections;
        }
        public List<String> getCurrentUserCollections() {
            return currentUserCollections;
        }

        public void setSponsorship(String sponsorship) {
            this.sponsorship = sponsorship;
        }
        public String getSponsorship() {
            return sponsorship;
        }

        public void setTopicSubmissions(TopicSubmissions topicSubmissions) {
            this.topicSubmissions = topicSubmissions;
        }
        public TopicSubmissions getTopicSubmissions() {
            return topicSubmissions;
        }

        public void setUser(User user) {
            this.user = user;
        }
        public User getUser() {
            return user;
        }

        public void setTags(List<Tags> tags) {
            this.tags = tags;
        }
        public List<Tags> getTags() {
            return tags;
        }

        @Override
        public String toString() {
            return "Results{" +
                    "id='" + id + '\'' +
                    ", slug='" + slug + '\'' +
                    ", createdAt=" + createdAt +
                    ", updatedAt=" + updatedAt +
                    ", promotedAt=" + promotedAt +
                    ", width=" + width +
                    ", height=" + height +
                    ", color='" + color + '\'' +
                    ", blurHash='" + blurHash + '\'' +
                    ", description='" + description + '\'' +
                    ", altDescription='" + altDescription + '\'' +
                    ", urls=" + urls +
                    ", links=" + links +
                    ", likes=" + likes +
                    ", likedByUser=" + likedByUser +
                    ", currentUserCollections=" + currentUserCollections +
                    ", sponsorship='" + sponsorship + '\'' +
                    ", topicSubmissions=" + topicSubmissions +
                    ", user=" + user +
                    ", tags=" + tags +
                    '}';
        }
    }

    public static class Urls {

        private String raw;
        private String full;
        private String regular;
        private String small;
        private String thumb;
        private String smallS3;
        public void setRaw(String raw) {
            this.raw = raw;
        }
        public String getRaw() {
            return raw;
        }

        public void setFull(String full) {
            this.full = full;
        }
        public String getFull() {
            return full;
        }

        public void setRegular(String regular) {
            this.regular = regular;
        }
        public String getRegular() {
            return regular;
        }

        public void setSmall(String small) {
            this.small = small;
        }
        public String getSmall() {
            return small;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
        public String getThumb() {
            return thumb;
        }

        public void setSmallS3(String smallS3) {
            this.smallS3 = smallS3;
        }
        public String getSmallS3() {
            return smallS3;
        }

        @Override
        public String toString() {
            return "Urls{" +
                    "raw='" + raw + '\'' +
                    ", full='" + full + '\'' +
                    ", regular='" + regular + '\'' +
                    ", small='" + small + '\'' +
                    ", thumb='" + thumb + '\'' +
                    ", smallS3='" + smallS3 + '\'' +
                    '}';
        }
    }

    public static class Links {

        private String self;
        private String html;
        private String download;
        private String downloadLocation;
        public void setSelf(String self) {
            this.self = self;
        }
        public String getSelf() {
            return self;
        }

        public void setHtml(String html) {
            this.html = html;
        }
        public String getHtml() {
            return html;
        }

        public void setDownload(String download) {
            this.download = download;
        }
        public String getDownload() {
            return download;
        }

        public void setDownloadLocation(String downloadLocation) {
            this.downloadLocation = downloadLocation;
        }
        public String getDownloadLocation() {
            return downloadLocation;
        }

        @Override
        public String toString() {
            return "Links{" +
                    "self='" + self + '\'' +
                    ", html='" + html + '\'' +
                    ", download='" + download + '\'' +
                    ", downloadLocation='" + downloadLocation + '\'' +
                    '}';
        }
    }

    public static class TopicSubmissions {

        private BusinessWork businesswork;
        public void setBusinessWork(BusinessWork businesswork) {
            this.businesswork = businesswork;
        }
        public BusinessWork getBusinessWork() {
            return businesswork;
        }

        @Override
        public String toString() {
            return "TopicSubmissions{" +
                    "businesswork=" + businesswork +
                    '}';
        }
    }

    public static class BusinessWork {

        private String status;
        private Date approvedOn;
        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

        public void setApprovedOn(Date approvedOn) {
            this.approvedOn = approvedOn;
        }
        public Date getApprovedOn() {
            return approvedOn;
        }

        @Override
        public String toString() {
            return "BusinessWork{" +
                    "status='" + status + '\'' +
                    ", approvedOn=" + approvedOn +
                    '}';
        }
    }

    public static class User {

        private String id;
        private Date updatedAt;
        private String username;
        private String name;
        private String firstName;
        private String lastName;
        private String twitterUsername;
        private String portfolioUrl;
        private String bio;
        private String location;
        private Links links;
        private ProfileImage profileImage;
        private String instagramUsername;
        private int totalCollections;
        private int totalLikes;
        private int totalPhotos;
        private boolean acceptedTos;
        private boolean forHire;
        private Social social;
        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }
        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setUsername(String username) {
            this.username = username;
        }
        public String getUsername() {
            return username;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public String getFirstName() {
            return firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public String getLastName() {
            return lastName;
        }

        public void setTwitterUsername(String twitterUsername) {
            this.twitterUsername = twitterUsername;
        }
        public String getTwitterUsername() {
            return twitterUsername;
        }

        public void setPortfolioUrl(String portfolioUrl) {
            this.portfolioUrl = portfolioUrl;
        }
        public String getPortfolioUrl() {
            return portfolioUrl;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }
        public String getBio() {
            return bio;
        }

        public void setLocation(String location) {
            this.location = location;
        }
        public String getLocation() {
            return location;
        }

        public void setLinks(Links links) {
            this.links = links;
        }
        public Links getLinks() {
            return links;
        }

        public void setProfileImage(ProfileImage profileImage) {
            this.profileImage = profileImage;
        }
        public ProfileImage getProfileImage() {
            return profileImage;
        }

        public void setInstagramUsername(String instagramUsername) {
            this.instagramUsername = instagramUsername;
        }
        public String getInstagramUsername() {
            return instagramUsername;
        }

        public void setTotalCollections(int totalCollections) {
            this.totalCollections = totalCollections;
        }
        public int getTotalCollections() {
            return totalCollections;
        }

        public void setTotalLikes(int totalLikes) {
            this.totalLikes = totalLikes;
        }
        public int getTotalLikes() {
            return totalLikes;
        }

        public void setTotalPhotos(int totalPhotos) {
            this.totalPhotos = totalPhotos;
        }
        public int getTotalPhotos() {
            return totalPhotos;
        }

        public void setAcceptedTos(boolean acceptedTos) {
            this.acceptedTos = acceptedTos;
        }
        public boolean getAcceptedTos() {
            return acceptedTos;
        }

        public void setForHire(boolean forHire) {
            this.forHire = forHire;
        }
        public boolean getForHire() {
            return forHire;
        }

        public void setSocial(Social social) {
            this.social = social;
        }
        public Social getSocial() {
            return social;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", updatedAt=" + updatedAt +
                    ", username='" + username + '\'' +
                    ", name='" + name + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", twitterUsername='" + twitterUsername + '\'' +
                    ", portfolioUrl='" + portfolioUrl + '\'' +
                    ", bio='" + bio + '\'' +
                    ", location='" + location + '\'' +
                    ", links=" + links +
                    ", profileImage=" + profileImage +
                    ", instagramUsername='" + instagramUsername + '\'' +
                    ", totalCollections=" + totalCollections +
                    ", totalLikes=" + totalLikes +
                    ", totalPhotos=" + totalPhotos +
                    ", acceptedTos=" + acceptedTos +
                    ", forHire=" + forHire +
                    ", social=" + social +
                    '}';
        }
    }

    public static class ProfileImage {

        private String small;
        private String medium;
        private String large;
        public void setSmall(String small) {
            this.small = small;
        }
        public String getSmall() {
            return small;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
        public String getMedium() {
            return medium;
        }

        public void setLarge(String large) {
            this.large = large;
        }
        public String getLarge() {
            return large;
        }

        @Override
        public String toString() {
            return "ProfileImage{" +
                    "small='" + small + '\'' +
                    ", medium='" + medium + '\'' +
                    ", large='" + large + '\'' +
                    '}';
        }
    }

    public static class Social {
        private String instagramUsername;
        private String portfolioUrl;
        private String twitterUsername;
        private String paypalEmail;
        public void setInstagramUsername(String instagramUsername) {
            this.instagramUsername = instagramUsername;
        }
        public String getInstagramUsername() {
            return instagramUsername;
        }

        public void setPortfolioUrl(String portfolioUrl) {
            this.portfolioUrl = portfolioUrl;
        }
        public String getPortfolioUrl() {
            return portfolioUrl;
        }

        public void setTwitterUsername(String twitterUsername) {
            this.twitterUsername = twitterUsername;
        }
        public String getTwitterUsername() {
            return twitterUsername;
        }

        public void setPaypalEmail(String paypalEmail) {
            this.paypalEmail = paypalEmail;
        }
        public String getPaypalEmail() {
            return paypalEmail;
        }

        @Override
        public String toString() {
            return "Social{" +
                    "instagramUsername='" + instagramUsername + '\'' +
                    ", portfolioUrl='" + portfolioUrl + '\'' +
                    ", twitterUsername='" + twitterUsername + '\'' +
                    ", paypalEmail='" + paypalEmail + '\'' +
                    '}';
        }
    }

    public static class Tags {

        private String type;
        private String title;
        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "Tags{" +
                    "type='" + type + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

}

