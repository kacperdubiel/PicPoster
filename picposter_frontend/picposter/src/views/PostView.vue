<template>
    <div id="post-page" class="">
        <div id="post-container" class=""> 
            <div id="p-image" class=""> 
                <image-component :filename="post.imagePath" />
            </div>

            <div id="p-right-panel" class="shadow-box-sm"> 
                <div id="p-details">
                    <div id="p-author-img">
                        <image-component :filename="post.poster.profileImagePath"/>
                    </div>
                    <div id="p-post-info">
                        <div id="p-post-info-top">
                            <div id="p-post-author">
                                {{ post.poster.login }}
                            </div>
                            <div id="p-post-date">
                                {{ format_date(post.addedDate) }}
                            </div>
                        </div>
                        <div id="p-post-info-bottom">
                            {{ post.description }}
                        </div>
                    </div>
                </div>
                <div id="p-comments">
                    <post-comments-component :postId="post.id" />
                </div>
                <div id="p-comments-bottom-panel">
                    <div id="p-likes">
                        <i id="p-likes-icon" class="fas fa-heart"></i>
                        <span id="p-likes">liczba polubień: <likes-counter-component :postId="post.id" /></span>
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
import PostCommentsComponent from '../components/PostCommentsComponent.vue'
import AddCommentComponent from '../components/AddCommentComponent.vue'
import axios from "axios"
import moment from 'moment';

export default {
  name: 'PostView',
  components: {
    ImageComponent,
    LikesCounterComponent,
    PostCommentsComponent,
    AddCommentComponent
  },
  data(){
    return {
      post: {}
    }
  },
  methods:{
    getPost(){
        axios.get('http://localhost:8090/posts/' + this.$route.params.postId)
        .then(data => {this.post = data.data}).catch(e => alert(e))
    },
      
    format_date(value){
        if (value) {
          return moment(String(value)).format('DD/MM/YYYY HH:mm')
        }
    },
  },
  mounted(){
    this.getPost()
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

#p-details {
    display: flex;
    justify-content: space-between;
}

#p-author-img {
    width: 70px;
    height: 80px;
}

#p-author-img > img {
    max-width: 100%;
    max-height: 100%;
    border-radius: 8px;
    -webkit-box-shadow: 1px 1px 4px #888888;
    -moz-box-shadow:    1px 1px 4px #888888;
    box-shadow:         1px 1px 4px #888888; 
}

#p-post-info {
    flex-grow: 5;
    padding-left: 10px;
}

#p-post-info {
    display: flex;
    flex-direction: column;
}

#p-post-info-top {
    display: flex;
    justify-content: space-between;
}

#p-post-author {
    font-weight: bold;
}

#p-post-date {
    font-size: 10px;
}

#p-comments {
    border: 1px solid #cccccc;
    margin-top: 10px;
    height: 45vh;
    overflow-y: scroll;
}

#p-comments-bottom-panel {
    margin-top: 10px;
}

#p-likes-icon {
    color: red;
    font-size: 22px;
    margin-right: 2%;
}

#p-likes {
    font-size: 18px;
}

/* Devices */

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