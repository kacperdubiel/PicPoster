<template>
    <div id="wall-post-container">
      <div id="wp-image">
        <image-component :filename="post.imagePath" />
      </div>
      <div id="wp-bottom-bar">
        <div id="wp-bottom-bar-left">
          <div id="wp-poster-image">
            <image-component :filename="post.poster.profileImagePath" />
          </div>
          <div>
            <div>
              <span id="wp-poster">{{ post.poster.login }}</span>
              <span id="wp-desc">{{ post.description }}</span>
            </div>
            <div>
              <span id="wp-date">{{ format_date(post.addedDate) }}</span>
            </div>
          </div>
        </div>
        <div id="wp-bottom-bar-right">
          <span id="wp-likes">{{ likes.length }}</span>
          <i id="wp-likes-icon" class="fas fa-heart"></i>
        </div>
      </div>
    </div>
</template>

<script>
import ImageComponent from './ImageComponent.vue'
import axios from "axios";
import moment from 'moment';

export default {
  name: 'post-component',
  props: {
      post: {
          type: Object
      }
  },
  components: {
    ImageComponent
  },
  data(){
    return {
      likes: []
    }
  },
  methods:{
    getPostLikes(){
      axios.get('http://localhost:8090/likes/post/' + this.post.id)
        .then(data => {this.likes = data.data}).catch(e => alert(e))
    },

    format_date(value){
        if (value) {
          return moment(String(value)).format('DD/MM/YYYY HH:mm')
        }
    },
  },
  mounted(){
    this.getPostLikes()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #wall-post-container {
    border: 1px solid black;
    border-radius: 10px;
    margin-bottom: 20px;
  }

  #wp-image {
    max-height: 80vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  #wp-image > img {
    max-width: 100%;
    max-height: 80vh;
    border-radius: 10px 10px 0 0;
  }

  #wp-bottom-bar{
    display: flex;
    justify-content: space-between;
    align-items: center;
    min-height: 50px;
    padding: 10px 10px;
  }

  #wp-bottom-bar-left {
    display: flex;
    justify-content: flex-start;
    align-items: center;
  }

  #wp-poster-image {
    min-width: 50px;
    max-width: 12%;
    max-height: 20vh;
    margin-right: 2%;
  }
  
  #wp-poster-image > img {
    max-width: 100%;
    max-height: 20vh;
    border: solid 1px black;
    border-radius: 10px;
  }

  #wp-poster {
    font-weight: bold;
    margin-right: 5px;
  }

  #wp-date {
    font-size: 12px;
  }

  #wp-bottom-bar-right {
    display: flex;
    justify-content: flex-end;
    align-items: center;
  }

  #wp-likes {
    font-weight: bold;
    font-size: 20px;
    margin-right: 7px;
  }

  #wp-likes-icon {
    font-size: 34px;
  }
</style>