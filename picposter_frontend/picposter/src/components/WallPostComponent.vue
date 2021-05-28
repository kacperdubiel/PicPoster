<template>
    <div id="wall-post-container" class="shadow-box">
      <div id="wp-image">
        <image-component :filename="post.imagePath" />
      </div>
      <div id="wp-bottom-bar">
        <div id="wp-bottom-bar-left">
          <div id="wp-poster-image">
            <image-component :filename="post.poster.profileImagePath" />
          </div>
          <div id="wp-poster-info">
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
          <span><likes-counter-component :postId="post.id" /></span>
          <likes-icon-component :userId="userId" :postId="post.id"/>
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
      userId: '463bc735-22eb-4184-a48a-0c506cd4e591'  // TODO: Fix hardcoding after SSO is added
    }
  },
  methods:{
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
  }

  #wp-image {
    max-height: 75vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  #wp-image > img {
    max-width: 100%;
    max-height: 75vh;
    border-radius: 10px 10px 0 0;
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
    width: 50px;
    height: 50px;
    margin-right: 10px;
    display: flex;
    justify-content: center;
    align-items: flex-start;
    padding-top: 5px;
  }
  
  #wp-poster-image > img {
    max-width: 100%;
    max-height: 100%;
    border-radius: 7px;
    -webkit-box-shadow: 1px 1px 4px #888888;
    -moz-box-shadow:    1px 1px 4px #888888;
    box-shadow:         1px 1px 4px #888888; 
  }

  #wp-poster-info {
    width:100%;
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
</style>