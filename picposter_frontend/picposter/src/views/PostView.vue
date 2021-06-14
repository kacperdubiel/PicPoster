<template>
<div>
    <navbar-component />

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
                    <div id="p-author-img">
                        <router-link :to="{ name: 'ProfileView', params: { userId: post.poster.id } }">
                            <image-component :filename="post.poster.profileImagePath"/>
                        </router-link>
                    </div>
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
                            {{ format_date(post.addedDate)}}
                        </div>
                    </div>
                </div>
                <div id="p-back-btn-container" v-if="post.poster.id == userId">
                    <button class="btn btn-danger" @click="deletePost">
                            Usuń
                    </button>
                </div>
                <div id="p-comments">
                    <post-comments-component :postId="post.id" :isCommentAdded="isCommentAdded" @comment:actualized="commentActualized"/>
                </div>
                <div id="p-comments-bottom-panel">
                    <div id="p-likes">
                        <likes-icon-component :userId="userId" :postId="post.id" @likeIconClicked="likeIconClicked"/>
                        <span><likes-counter-component :postId="post.id" :isLikesAmountChanged="isLikesAmountChanged"/></span>
                    </div>
                    <div id="p-add-comment">
                        <add-comment-component :post="post" @comment:added="actualizeComments"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import NavbarComponent from '../components/NavbarComponent.vue'
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
    NavbarComponent,
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
        isLikesAmountChanged: false,
        userId: localStorage.getItem('userId'),
        isCommentAdded:false
    }
  },
  methods:{
    getPost(){
        axios.get('http://localhost:8090/posts/' + this.postId, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token')
        }
      })
        .then(data => {
            this.post = data.data;
            this.fetchingStatus = 'found';
        })
        .catch(e => {
            console.log(e);
            this.fetchingStatus = 'not-found';
        })
        
    },

    actualizeComments(){
        this.isCommentAdded = true;
    },

    commentActualized(){
        this.isCommentAdded = false;
    },

    likeIconClicked(){
      this.isLikesAmountChanged = !this.isLikesAmountChanged;
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
            this.$router.push({ name: 'WallView' })
    },

    deletePost(){
        axios.delete('http://localhost:8090/comments/post/' + this.postId, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token')
        }
        })
        .catch(e => {
            console.log(e);
        })
        .then(() => { axios.delete('http://localhost:8090/likes/post/' + this.postId, {
                        headers: {
                             Authorization: 'Bearer ' + localStorage.getItem('token')
                        }
                    })
                    .catch(e => console.log(e))
                    .then(() => {axios.delete('http://localhost:8090/posts/' + this.postId, {
                                    headers: {
                                    Authorization: 'Bearer ' + localStorage.getItem('token')
                                    }
                                })
                                .catch(e => console.log(e))
                                .then(() => {
                                    this.$router.push(this.$route.query.redirect || '/profile/' + this.userId)
                                })
                             }
                        )})
        .catch(e => {
            console.log(e);
        })
    },

    redirectIfLogout(){
      if(!localStorage.getItem('token')){
        this.$router.push(this.$route.query.redirect || '/')
        return true;
      }
      return false;
    }
  },
  created(){
    if(!this.redirectIfLogout()){
        this.getPost();
    }
    
  },
}
</script>

<style>

#post-page {
    display: flex;
    justify-content: center;
    align-items: center;
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
    max-width: 80vw;
}

#p-image {
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 12px;
}

#p-image img {
    border-radius: 10px;
    -webkit-box-shadow: 1px 1px 4px #888888;
    -moz-box-shadow:    1px 1px 4px #888888;
    box-shadow:         1px 1px 4px #888888; 
    max-height: 80vh;
}

#p-right-panel {
    display: flex;
    flex-direction: column;
    border-radius: 10px;
    padding: 10px;
    height: fit-content;
    min-width: 350px;
    width: 25vw;
    max-width: 500px;
    background-color: white;
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
    flex-direction: row;
}

#p-author-img {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 70px;
    height: 70px;
    margin-right: 10px;
}

#p-author-img img {
    width: 65px;
    height: 65px;
    object-fit: cover;
    border-radius: 50%;
    border: 2px solid #ffffff;
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
    flex: 1;
    width: 100%;
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

@media (max-width: 1250px) { 
    #post-container {
        max-width: 100%;
    }
}

@media (max-width: 768px) { 
    #post-page {
        margin: 0;
    }

    #post-container {
        flex-direction: column;
        width: 100%;
    }

    #p-image {
        margin-bottom: 10px;
        margin-right: 0;
    }

    #p-image img {
        max-width: 100%;
        max-height: 90vh;
        border-radius: 0;
    }

    #p-right-panel {
        width: 100%;
        min-width: 100%;
        max-width: 100%;
    }

}

@media (min-width: 2500px) { 
    /* #p-comments {
        height: 25vh;
    } */
/* 
    #p-right-panel {
        
    } */
}
</style>