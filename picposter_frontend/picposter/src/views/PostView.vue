<template>
    <div id="post-page" class="">
        <div id="post-loading" v-if="fetchingStatus === 'fetching'">
            ŁADOWANIE
        </div>

        <div id="post-not-found" v-else-if="fetchingStatus === 'not-found'">
            <h1>Błąd 404</h1>
            <h3>Taki post nie istnieje.</h3>
        </div>

        <div id="post-container" class="" v-else-if="fetchingStatus === 'found'"> 
            <div id="p-image" class=""> 
                <image-component :filename="post.imagePath" />
            </div>
            <div id="p-right-panel" class="shadow-box-sm"> 
                <div id="p-details">
                    <router-link :to="{ name: 'ProfileView', params: { userId: post.poster.id } }">
                        <div id="p-author-img">
                            <image-component :filename="post.poster.profileImagePath"/>
                        </div>
                    </router-link>
                    <div id="p-post-info">
                        <div id="p-post-info-top">
                            <div id="p-post-author">
                                <router-link :to="{ name: 'ProfileView', params: { userId: post.poster.id } }">
                                    {{ post.poster.login }}
                                </router-link>
                            </div>
                            <div id="p-back-btn-container">
                                <div id="p-back-btn" @click="goBack">
                                    <i class="fas fa-times"></i>
                                </div>
                            </div>
                        </div>
                        <div id="p-post-info-bottom">
                            {{ post.description }}
                        </div>
                        <div id="p-post-date">
                            {{ format_date(post.addedDate) }}
                        </div>
                    </div>
                </div>
                <div id="p-comments">
                    <post-comments-component :postId="post.id" />
                </div>
                <div id="p-comments-bottom-panel">
                    <div id="p-likes">
                        <likes-icon-component :userId="userId" :postId="post.id"/>
                        <span><likes-counter-component :postId="post.id" /></span>
                    </div>
                    <div id="p-add-comment">
                        <add-comment-component />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ImageComponent from '../components/ImageComponent.vue'
import LikesCounterComponent from '../components/LikesCounterComponent.vue'
import LikesIconComponent from '../components/LikesIconComponent.vue'
import PostCommentsComponent from '../components/PostCommentsComponent.vue'
import AddCommentComponent from '../components/AddCommentComponent.vue'
import axios from "axios"
import moment from 'moment';

export default {
  name: 'PostView',
  components: {
    ImageComponent,
    LikesCounterComponent,
    LikesIconComponent,
    PostCommentsComponent,
    AddCommentComponent
  },
  data(){
    return {
        postId: this.$route.params.postId,
        post: { },
        fetchingStatus: 'fetching',
        isLiked: false,
        userId: '463bc735-22eb-4184-a48a-0c506cd4e591'  // TODO: Fix hardcoding after SSO is added
    }
  },
  methods:{
    getPost(){
        axios.get('http://localhost:8090/posts/' + this.postId)
        .then(data => {
            this.post = data.data;
            this.fetchingStatus = 'found';
        })
        .catch(e => {
            console.log(e);
            this.fetchingStatus = 'not-found';
        })
        
    },
      
    format_date(value){
        if (value) {
          return moment(String(value)).format('DD/MM/YYYY HH:mm')
        }
    },

    goBack(){
        if(window.history.length > 1)
            this.$router.go(-1);
        else
            this.$router.push({ name: 'WallView', params: { userId: this.userId } })  // TODO: fix userId hardcoding 
    },
  },
  created(){
    this.getPost();
  }
}
</script>

<style>

#post-page {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 4%;
}

#post-loading {
    font-size: 16px;
    text-align: center;
}

#post-not-found {
    font-size: 20px;
    text-align: center;
}

#post-container {
    display: flex;
    justify-content: center;
    
    width: fit-content;
}

#p-image {
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
}

#p-image > img {
    border-radius: 10px;
    -webkit-box-shadow: 1px 1px 4px #888888;
    -moz-box-shadow:    1px 1px 4px #888888;
    box-shadow:         1px 1px 4px #888888; 
}

#p-right-panel {
    display: flex;
    flex-direction: column;
    border-radius: 10px;
    padding: 10px;
    height: fit-content;
    min-width: 28vw;
}

#p-back-btn-container {
    display: flex;
    justify-content: flex-end;
}

#p-back-btn {
    margin-right: 4px;
    cursor: pointer;
}

#p-back-btn i {
    font-size: 28px;
    color: #585858;
}

#p-back-btn i:hover {
    color: #707070;
}

#p-details {
    display: flex;
    justify-content: space-between;
}

#p-author-img {
    display: flex;
    justify-content: center;
    align-items: flex-start;
    width: 70px;
    height: 80px;
    padding-top: 7px;
}

#p-author-img img {
    max-width: 100%;
    max-height: 100%;
    border-radius: 6px;
    -webkit-box-shadow: 1px 1px 4px #888888;
    -moz-box-shadow:    1px 1px 4px #888888;
    box-shadow:         1px 1px 4px #888888; 
}

#p-author-img img:hover {
    opacity: .85;
}

#p-post-info {
    display: flex;
    flex-direction: column;
    width: 100%;
    padding-left: 10px;
}

#p-post-info-top {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

#p-post-author {
    font-weight: bold;
}

#p-post-author a:hover {
    text-decoration: none;
}

#p-post-date {
    margin-top: 3px;
    font-size: 11px;
}

#p-comments {
    border: 1px solid #cccccc;
    margin-top: 10px;
    height: 40vh;
    overflow-y: scroll;
}

#p-comments-bottom-panel {
    margin-top: 9px;
}

#p-likes {
    display: flex;
    align-items: center;
}

#p-likes i {
    font-size: 28px;
    margin-right: 8px;
    margin-left: 3px;
    margin-bottom: 3px;
}

#p-likes span {
    font-size: 18px;
    font-weight: bold;
}

/* Responsive */

@media (max-width: 768px) { 
    #post-page {
        margin: 0;
    }

    #post-container {
        flex-direction: column;
        width: 100%;
    }

    #p-image {
        margin-bottom: 15px;
    }

    #p-image > img {
        max-width: 100%;
        max-height: 100vh;
    }

    #p-right-panel {
        width: 100%;
    }

}

@media (min-width: 768px) { 
    #post-container {
        max-width: 85%;
    }

    #p-image {
        margin-right: 12px;
    }

    #p-image > img {
        max-height: 90vh;
    }

    #p-right-panel {
        max-width: 30vh;
    }
}

@media (min-width: 2500px) { 
    #p-comments {
        height: 30vh;
    }
}
</style>