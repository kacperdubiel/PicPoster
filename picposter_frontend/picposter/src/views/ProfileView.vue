<template>
    <div>
     <div id="profile-header">
        <div id="logo-container">
            <h2 id="start-logo">Pic Poster</h2>
        </div>
      </div>
      <div id="user-profile-container">
        <div id="user-info-container">
            <div id="profile-image-section">
                <img id="profile-image" :src="this.image">
            </div>
            <div id="profile-info-section">
              <span>{{this.user.login}}</span>
              <span>Obserwujacy: 1</span>
              <span>{{this.user.description}}</span>
              <div style="margin-top:2%">
                  <button type="button" class="btn btn-light">Obserwuj</button>
              </div>
            </div>
        </div>
      </div>
      <hr/>
      <profile-component :userSoure="user" :postsSource="posts"></profile-component>
  </div>
</template>

<script>

import ProfileComponent from '../components/ProfileComponent.vue'
import axios from 'axios'

export default {
  name: 'ProfileView',
  components: {
      ProfileComponent
  },
  data(){
      return{
        user: {},
        posts: [],
        image: ""
      }
  },
  methods: {
      getUser(){
        axios.get('http://localhost:8090/users/' + this.$route.params.userId)
        .then(data => {this.user = data.data}).catch(e => alert(e))
      },
      getPosts(){
        axios.get('http://localhost:8090/posts/user/' + this.$route.params.userId)
        .then(data => {this.posts = data.data}).catch(e => alert(e))
      },
      getImage(){
            let config = {
                url: 'http://localhost:8090/upload/' + this.user.profileImagePath,
                method: 'GET',
                responseType: 'blob'
            }
            axios(config).then((response) => {
                let reader = new FileReader();
                reader.readAsDataURL(response.data);
                reader.onload = () => {
                    return this.image = reader.result;
                }
             });
        }
  },
  mounted() {
      this.getUser()
      this.getPosts()
      this.getImage()
  }
}
</script>

<style>
    #profile-header{
        display: flex;
        justify-content: left;
    }
    #user-profile-header{
        display: flex;
        justify-content: left;
    }
    #user-profile-container{
        display: flex;
        justify-content: center;
    }
    #profile-image-section{
        display: flex;
        flex-direction: column;
        margin-inline-start: 1%;
    }
    #profile-info-section{
        display: flex;
        flex-direction: column;
        margin-inline-start: 3%;
    }
    #profile-image{
        width: 150px;
        height: 150px;
    }
    #start-logo{
        color: palevioletred;
        text-align: center;
        font-family: fantasy;
        padding: 20px;
    }
    #logo-container{
        margin-top: 1%;
    }
    #user-info-container{
        display: flex;
        justify-content: center;
        margin-block-end: 3%;
    }
</style>