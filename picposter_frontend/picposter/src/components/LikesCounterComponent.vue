<template>
    <span :key="likesAmount">{{ likesAmount }}</span>
</template>

<script>
import axios from "axios";

export default {
  name: 'likes-counter-component',
  props: {
      postId: {
        type: String
      },
      isLikesAmountChanged: Boolean
      
  },
  data(){
    return {
      likesAmount: 0
    }
  },
  watch: {
    'isLikesAmountChanged': function() {
      this.getPostLikes();
    }
  },
  methods:{
    getPostLikes(){
      axios.get('http://localhost:8090/likes/post/' + this.postId)
        .then(data => {this.likesAmount = data.data.length}).catch(e => alert(e))
    },

  },
  mounted(){
    this.getPostLikes()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>