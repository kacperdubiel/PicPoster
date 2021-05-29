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
import WallPostComponent from '../components/WallPostComponent.vue'
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
        axios.get('http://localhost:8090/posts/followed/' + this.$route.params.userId)
        .then(data => {this.followedPosts = data.data}).catch(e => alert(e))
    }
  },
  created(){
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
}

@media (min-width: 768px) { 
    #wall-container {
        margin: 4% 25%;
    }

}

</style>