<template>
    <i id="likes-icon" class="fas fa-heart"
        :key="isLiked"
        v-bind:class="{ 'p-not-liked': !isLiked, 'p-liked': isLiked }"
        @click="likePost"
    ></i>
</template>

<script>
import axios from "axios";

export default {
  name: 'likes-icon-component',
  props: {
      postId: String,
      userId: String
  },
  data(){
    return {
      isLiked: false
    }
  },
  methods:{
    isLikedColorChange(){
        axios.get('http://localhost:8090/likes/user/' + this.userId + '/post/' + this.postId, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token')
        }
      })
        .then((response) => {
          if(response.status == 200)
            this.isLiked = true
        }).catch((err) => {console.log(err)});
    },

    likePost(){
      axios.get('http://localhost:8090/likes/user/' + this.userId + '/post/' + this.postId, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token')
        }
      })
      .then((response) => {
          if(response.status == 200){
            // Like already exist - Delete old Like
            axios.delete("http://localhost:8090/likes/" + response.data.id, {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
              }
            })
              .then((res) => {
                if(res.status == 200){
                  this.isLiked = false;
                  this.$emit('likeIconClicked')
                }
              }).catch(err => console.log(err))
            
          } else if(response.status == 204){
            // User and Post is correct and there is no Like - Add new Like
            const newLike = { liker: { id: this.userId }, post: { id: this.postId } };
            axios.post("http://localhost:8090/likes", newLike, {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
              }
            })
            .then((res) => {
                if(res.status == 201){
                  this.isLiked = true;
                  this.$emit('likeIconClicked')
                }
              }).catch(err => console.log(err))
          }
        }).catch((err) => {console.log(err)});
    }
  },
  mounted(){
    this.isLikedColorChange()
  }
}
</script>

<style scoped>

#likes-icon {
  cursor: pointer;
}

#likes-icon:hover {
  color: rgb(255, 25, 71);
}

.p-not-liked {
    color: #888888;
}

.p-liked {
    color: rgb(236, 0, 47);
}

</style>