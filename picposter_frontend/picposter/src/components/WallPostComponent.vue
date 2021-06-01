<template>
  <div id="wall-post-container" class="shadow-box">
    <router-link :to="{ name: 'PostView', params: { postId: post.id } }">
      <div id="wp-image">
        <image-component :filename="post.imagePath" />
      </div>
    </router-link>
    <div id="wp-bottom-bar">
      <div id="wp-bottom-bar-left">
        <div id="wp-poster-image">
          <router-link :to="{ name: 'ProfileView', params: { userId: post.poster.id } }">
            <image-component :filename="post.poster.profileImagePath" />
          </router-link>
        </div>
        <div id="wp-poster-info">
          <div>
            <span id="wp-poster">
              <router-link :to="{ name: 'ProfileView', params: { userId: post.poster.id } }">
                {{ post.poster.login }}
              </router-link>
            </span>
            <span id="wp-desc">{{ post.description }}</span>
          </div>
          <div>
            <span id="wp-date">{{ format_date(post.addedDate) }}</span>
          </div>
        </div>
      </div>
      <div id="wp-bottom-bar-right">
        <span><likes-counter-component :postId="post.id" :isLikesAmountChanged="isLikesAmountChanged"/></span>
        <likes-icon-component :userId="userId" :postId="post.id" @likeIconClicked="likeIconClicked"/>
      </div>
    </div>
  </div>
</template>

<script>
import ImageComponent from './ImageComponent.vue'
import LikesCounterComponent from './LikesCounterComponent.vue'
import LikesIconComponent from './LikesIconComponent.vue'
import moment from 'moment';


export default {
  name: 'post-component',
  components: {
    ImageComponent,
    LikesCounterComponent,
    LikesIconComponent
  },
  props: {
      post: {
          type: Object
      }
  },
  data(){
    return {
      isLikesAmountChanged: false,
      userId: localStorage.getItem('userId')  // TODO: Fix hardcoding after SSO is added
    }
  },
  methods:{
    likeIconClicked(){
      this.isLikesAmountChanged = !this.isLikesAmountChanged
    },

    format_date(value){
        if (value) {
          return moment(String(value)).format('DD/MM/YYYY HH:mm')
        }
    },
  },
  mounted(){
  }
}
</script>

<style scoped>
  #wall-post-container {
    border-radius: 10px;
    margin-bottom: 20px;
    background-color: white;
    width: 100%;
    max-width: 50%;
    /* min-width: 30vw;
    max-width: 70vw; */
  }

  #wp-image {
    max-height: 75vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  #wp-image img {
    max-width: 100%;
    max-height: 75vh;
    border-radius: 10px 10px 0 0;
  }

  #wp-image img:hover {
    opacity: .95;
  }

  #wp-bottom-bar{
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    min-height: 40px;
    padding: 10px 10px;
  }

  #wp-bottom-bar-left {
    display: flex;
    justify-content: flex-start;
  }

  #wp-poster-image {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 55px;
    height: 55px;
    margin-right: 15px;
  }
  
  #wp-poster-image img {
    width: 55px;
    height: 55px;
    object-fit: cover;
    border-radius: 50%;
    border: 2px solid #ffffff;
    -webkit-box-shadow: 1px 1px 4px #888888;
    -moz-box-shadow:    1px 1px 4px #888888;
    box-shadow:         1px 1px 4px #888888; 
  }

  #wp-poster-image img:hover {
    opacity: .85; 
  }

  #wp-poster-info {
    max-width: 100%;
    flex: 1;
  }

  #wp-poster {
    font-weight: bold;
    margin-right: 5px;
  }

  #wp-poster a:hover{
    text-decoration: none;
  }

  #wp-date {
    font-size: 12px;
  }

  #wp-bottom-bar-right {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    padding-top: 10px;
  }

  #wp-bottom-bar-right span {
    font-weight: bold;
    font-size: 20px;
    margin-right: 3px;
    margin-left: 10px;
  }

  #wp-bottom-bar-right i {
    font-size: 32px;
  }

@media (max-width: 1000px) { 
  #wall-post-container {
    max-width: 80%;
  }
}

@media (max-width: 768px) { 
  #wall-post-container {
    max-width: 97%;
  }
}

</style>