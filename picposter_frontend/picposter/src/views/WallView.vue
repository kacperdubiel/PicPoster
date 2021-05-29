<template>
  <div id="wall-container">
    <div id="wall-loading" v-if="fetchingStatus === 'fetching'">
        ŁADOWANIE
    </div>
    <div id="wall-not-found" v-else-if="fetchingStatus === 'not-found'">
        Nie znaleziono postów.
    </div>
    <div v-else-if="fetchingStatus === 'found'">
      <wall-post-component 
        v-for="post in followedPosts" 
        :key="post.id"
        :post="post"
      />
    </div>
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
      followedPosts: [],
      fetchingStatus: 'fetching'
    }
  },
  methods:{
    getFollowedPosts(){
        axios.get('http://localhost:8090/posts/followed/' + this.$route.params.userId)
        .then(data => {
          this.followedPosts = data.data;
          this.fetchingStatus = "found";
        }).catch(e => {
          console.log(e);
          this.fetchingStatus = "not-found";
        })
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

#wall-loading, #wall-not-found {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

@media (min-width: 768px) { 
    #wall-container {
        margin: 4% 25%;
    }

}

</style>