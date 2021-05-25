<template>
    <span>{{ likes.length }}</span>
</template>

<script>
import axios from "axios";

export default {
  name: 'image-component',
  props: {
      postId: {
        type: String
      }
  },
  data(){
    return {
      likes: []
    }
  },
  methods:{
    getPostLikes(){
      axios.get('http://localhost:8090/likes/post/' + this.postId)
        .then(data => {this.likes = data.data}).catch(e => alert(e))
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