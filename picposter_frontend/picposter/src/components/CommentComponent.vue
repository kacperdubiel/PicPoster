<template>
  <div id="comment-container">
    <div id="c-author-img">
        <image-component :filename="comment.commentator.profileImagePath" />
    </div>
    <div id="c-info">
        <div id="c-info-top">
            <div id="c-author">
                {{ comment.commentator.login }}
            </div>
            <div id="c-date">
                {{ format_date(comment.addedDate) }}
            </div>
        </div>
        <div id="c-info-bottom">
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
  name: 'post-component',
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
      user: {}
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
    align-items: center;
    padding: 5px 8px 0px 8px;
  }

  #c-author-img {
    flex-grow: 1;
    max-width: 10%;
    max-height: 8vh;
    min-height: 5vh;
    border-radius: 8px;
    margin-right: 2%;
  }

  #c-author-img > img {
    max-width: 100%;
    max-height: 8vh;
    min-height: 5vh;
    border: solid 1px black;
    border-radius: 8px;
  }

  #c-info {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    flex-grow: 4;
  }

  #c-author{
    font-weight: bold;
  }
  
  #c-date{
    font-size: 10px;
  }

  #c-info-top {
    display: flex;
    justify-content: space-between;
  }
</style>