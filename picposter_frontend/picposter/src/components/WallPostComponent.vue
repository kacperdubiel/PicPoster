<template>
    <div id="wall-post-container">
      <div id="wp-image">
        <img :src="postImage">
      </div>
      <div id="wp-bottom-bar">
        <div id="wp-bottom-bar-left">
          <div>
            <span id="wp-poster">{{ post.poster.login }}</span>
            <span id="wp-desc">{{ post.description }}</span>
          </div>
          <div>
            <span id="wp-date">{{ format_date(post.addedDate) }}</span>
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
import axios from "axios"
import moment from 'moment';

export default {
  name: 'post-component',
  props: {
      post: {
          type: Object
      }
  },
  data(){
    return {
      likes: [],
      postImage: ""
    }
  },
  methods:{
    getPostLikes(){
      axios.get('http://localhost:8090/likes/post/' + this.post.id)
        .then(data => {this.likes = data.data}).catch(e => alert(e))
    },

    getPostImg(){
      let config = {
          // example url
          url: 'http://localhost:8090/upload/' + this.post.imagePath,
          method: 'GET',
          responseType: 'blob'
      }
      axios(config)
          .then((response) => {
              let reader = new FileReader();
              reader.readAsDataURL(response.data); 
              reader.onload = () => {
                  this.postImage = reader.result;
              }
          });
    },

    format_date(value){
         if (value) {
           return moment(String(value)).format('DD/MM/YYYY hh:mm')
          }
      },
  },
  mounted(){
    this.getPostLikes(),
    this.getPostImg()
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

  #wp-image > img {
    max-width: 100%;
    border-radius: 10px 10px 0 0;
  }

  #wp-bottom-bar{
    display: flex;
    justify-content: space-between;
    align-items: center;
    min-height: 50px;
    padding: 5px 10px;
  }

  #wp-poster {
    font-weight: bold;
    margin-right: 5px;
  }

  #wp-date {
    font-size: 12px;
  }

  #wp-likes {
    font-weight: bold;
    font-size: 20px;
    margin-right: 7px;
  }

  #wp-likes-icon {
    color: red;
    font-size: 34px;
  }
</style>