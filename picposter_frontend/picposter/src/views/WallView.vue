<template>
  <div>
    <navbar-component />

    <div id="wall-container">
      <div id="wall-loading" v-if="fetchingStatus === 'fetching'">
          ŁADOWANIE
      </div>
      <div id="wall-not-found" v-else-if="fetchingStatus === 'not-found'">
          Nie znaleziono postów.
      </div>
      <div id="wall-posts-container" v-else-if="fetchingStatus === 'found'">
        <wall-post-component 
          v-for="post in followedPosts" 
          :key="post.id"
          :post="post"
        />
      </div>
    </div>
  </div>
</template>

<script>
import NavbarComponent from '../components/NavbarComponent.vue'
import WallPostComponent from '../components/WallPostComponent.vue'
import axios from "axios"
//axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')

export default {
  name: 'WallView',
  components: {
    NavbarComponent,
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
        axios.get('http://localhost:8090/posts/followed/' + localStorage.getItem('userId'),{
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token')
          }
        }
      )
        .then(data => {
          this.followedPosts = data.data;
          this.fetchingStatus = "found";
        }).catch(e => {
          console.log(e);
          this.fetchingStatus = "not-found";
        })
    },
    
    redirectIfLogout(){
      if(!localStorage.getItem('token')){
        this.$router.push(this.$route.query.redirect || '/')
        return true;
      }
      return false;
    }
  },
  created(){
    if(!this.redirectIfLogout()){
      this.getFollowedPosts()
    }
  }
}
</script>

<style>
#wall-container {
  
}

#wall-loading, #wall-not-found {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

#wall-posts-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-bottom: 40px;
}


</style>