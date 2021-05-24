<template>
  <div id="wall-container">
    <wall-post-component 
      v-for="post in followedPosts" 
      :key="post.id"
      :post="post"
    />
  </div>
</template>

<script>
import WallPostComponent from './components/WallPostComponent.vue'
import axios from "axios"

export default {
  name: 'WallView',
  components: {
    WallPostComponent
  },
  data(){
    return {
      followedPosts: []
    }
  },
  methods:{
    getFollowedPosts(){
        axios.get('http://localhost:8090/posts/followed/a2661f4c-8a82-4972-811f-817481a20e5a')
        .then(data => {this.followedPosts = data.data}).catch(e => alert(e))
    }
  },
  mounted(){
    this.getFollowedPosts()
  }
}
</script>

<style>
#wall-container {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin: 4% 20%;
}

</style>