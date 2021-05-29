<template>
  <div id="comment-container">
    <router-link :to="{ name: 'ProfileView', params: { userId: comment.commentator.id } }">
      <div id="c-author-img">
          <image-component :filename="comment.commentator.profileImagePath" />
      </div>
    </router-link>
    <div id="c-info" class="pl-2">
        <div id="c-info-top">
            <div id="c-author">
              <span>
                <router-link :to="{ name: 'ProfileView', params: { userId: comment.commentator.id } }">
                  {{ comment.commentator.login }}
                </router-link>
              </span>
            </div>
            <div id="c-date">
                {{ format_date(comment.addedDate) }}
            </div>
        </div>
        <div id="c-comment">
            {{ comment.comment }}
        </div>
    </div>
  </div>
</template>

<script>
import ImageComponent from './ImageComponent.vue'
// import axios from "axios"
import moment from 'moment';

export default {
  name: 'comment-component',
  components: {
    ImageComponent
  },
  props: {
      comment: {
          type: Object
      }
  },
  data(){
    return {
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #comment-container {
    display: flex;
    align-items: stretch;
    padding: 8px 8px;
  }

  #c-author-img {
    display: flex;
    justify-content: center;
    align-items: flex-start;
    width: 50px;
    height: 50px;
    margin-top: 5px;
  }

  #c-author-img > img {
    max-width: 100%;
    max-height: 100%;
    border-radius: 6px;
    -webkit-box-shadow: 1px 1px 3px #888888;
    -moz-box-shadow:    1px 1px 3px #888888;
    box-shadow:         1px 1px 3px #888888; 
  }

  #c-author-img > img:hover {
    opacity: .85;
  }

  #c-info {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    width:100%;
  }

  #c-info-top {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  #c-author {
    font-weight: bold;
  }

  #c-author span a:hover {
    text-decoration: none;
  }
  
  #c-date{
    font-size: 10px;
  }

  
</style>