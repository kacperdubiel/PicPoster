<template>
  <div id="post-container">
    <div id="p-image">
        <image-component :filename="post.imagePath" />
    </div>
    <div id="p-right-panel">
        <div id="p-details">
            <div id="p-author-img">
                <image-component :filename="post.poster.profileImagePath" />
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
                <form>
                    <input type="text" class="form-control" id="new-comment" placeholder="Dodaj komentarz...">
                    
                </form>
                <button type="submit" class="btn btn-primary">Dodaj</button>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import ImageComponent from './components/ImageComponent.vue'
import LikesCounterComponent from './components/LikesCounterComponent.vue'
import PostCommentsComponent from './components/PostCommentsComponent.vue'
import axios from "axios"
import moment from 'moment';

export default {
  name: 'PostView',
  components: {
    ImageComponent,
    LikesCounterComponent,
    PostCommentsComponent
  },
  data(){
    return {
      post: {}
    }
  },
  methods:{
    getPost(){
        axios.get('http://localhost:8090/posts/1777e8ae-abbe-4ae1-822b-412f627731fd')
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

#post-container {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    margin: 4% 16%;
    display: flex;
    justify-content: space-between;
    border: 1px solid black;
    border-radius: 10px;
    padding: 10px;
}

#p-image {
    flex-grow: 1;
    max-width: 60%;
    border-radius: 10px;
    margin-right: 1%;
    max-height: 75vh;
}

#p-image > img {
    max-width: 100%;
    max-height: 75vh;
    border: solid 1px black;
    border-radius: 10px;
}

#p-right-panel {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    flex-grow: 2;
    border: solid 1px black;
    border-radius: 10px;
    padding: 10px;
}

#p-details {
    display: flex;
    justify-content: space-between;
}

#p-author-img {
    max-height: 12vh;
    border-radius: 10px;
    margin-right: 2%;
}

#p-author-img > img {
    max-width: 100%;
    max-height: 12vh;
    border: solid 1px black;
    border-radius: 10px;
}

#p-post-info {
    flex-grow: 5;
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
    border: 1px solid black;
    height: 100%;
    overflow-y: scroll;
    margin-top: 10px;
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

#p-add-comment{
    display: flex;
    justify-content: space-between;
    margin-top: 7px;
}

#p-add-comment > form {
    flex-grow: 4;
    margin-right: 10px;
}

#p-add-comment > #form-group {
    margin-bottom: 0px;
}

#p-add-comment > button {
    flex-grow: 1;
    padding: 2px 10px;
    height: 38px;
}

</style>